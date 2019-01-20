package org.secondKill.service.impl;

import org.apache.commons.collections.MapUtils;
import org.secondKill.dao.SeckillDao;
import org.secondKill.dao.SuccessKilledDao;
import org.secondKill.dao.cache.RedisDao;
import org.secondKill.dto.Exposer;
import org.secondKill.dto.SeckillExecution;
import org.secondKill.entity.Seckill;
import org.secondKill.entity.SuccessKilled;
import org.secondKill.enums.SeckillStateEnum;
import org.secondKill.exception.RepeatKillException;
import org.secondKill.exception.SeckillCloseException;
import org.secondKill.exception.SeckillException;
import org.secondKill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SeckillServiceImpl implements SeckillService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SuccessKilledDao successKilledDao;

    @Autowired
    private RedisDao redisDao;

    // md5盐值字符串，用于混淆md5
    private final String salt = "erqw3242@#$%#@^#$ddDdddvD";

    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 4);
    }

    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {

        // 希望通过redis缓存起来，缓解数据库压力: 在超时的基础上维护一致性
        /**
         * 1. 访问redis,
         *
         */
        Seckill seckill = redisDao.getSeckill(seckillId);
        if (seckill == null) {
            seckill = seckillDao.queryById(seckillId);
            if (seckill == null) {
                return new Exposer(false, seckillId);
            } else {
                redisDao.putSeckill(seckill);
            }
        }



        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        // 系统当前时间
        Date nowTime = new Date();
        if (nowTime.getTime() < startTime.getTime()
            || nowTime.getTime() > endTime.getTime()) {
            return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }
        //转化特定字符串过程,不可逆
        String md5 = null;
        return new Exposer(true, getMD5(seckillId), seckillId);
    }

    private String getMD5(long seckillId) {
        String base = seckillId + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    @Transactional
    /**
     * 使用注解控制事务方法的有点：
     * 1：开发团队达成一致约定，明确标注事务方法的编程风格
     * 2：保证事务方法的执行时间尽可能短，不要穿插其他网络操作，RPC/HTTP,或者剥离到事务方法外。
     * 3: 不是所有的方法都需要事务
     *
     */
    @Override
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException {

        if (md5 == null || !md5.equals(getMD5(seckillId))) {
            throw new SeckillException("seckill data rewrite");
        }
        try {
            // 记录购买行为
            int insertCount = successKilledDao.insertSuccessKilled(seckillId,userPhone);

            if (insertCount <= 0) {
                throw new RepeatKillException("seckill repeated");
            } else {
                // 减库存：热点商品竞争
                Date nowtime = new Date();
                int updateCount = seckillDao.reduceNumber(seckillId, nowtime);
                if (updateCount <= 0) {
                    // 没有更新到记录,秒杀结束
                    throw new SeckillCloseException("seckill is closed");
                } else {
                    // 秒杀成功
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
                }
            }
        } catch (SeckillCloseException e1) {
            throw e1;
        } catch (RepeatKillException e2) {
            throw e2;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new SeckillException(" seckill inner error: {}", e);
        }

    }

    @Override
    public SeckillExecution executeSeckillProcedure(long seckillId, long userPhone, String md5) {
        if (md5 == null || !md5.equals(getMD5(seckillId))) {
            return new SeckillExecution(seckillId, SeckillStateEnum.DATA_REWRITE);
        }
        Date killTime = new Date();
        Map<String, Object> map = new HashMap<>();
        map.put("seckillId", seckillId);
        map.put("phone", userPhone);
        map.put("killTime", killTime);
        // 执行存储过程， result被复制
        try{
            seckillDao.killByProcedure(map);
            //获取result
            int result = MapUtils.getInteger(map,"result", -2);
            if (result == 1) {
                SuccessKilled sk = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, sk);
            } else {
                return new SeckillExecution(seckillId, SeckillStateEnum.stateOf(result));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new SeckillExecution(seckillId, SeckillStateEnum.INNER_ERROR);
        }
    }
}
