package org.secondKill.service;

import org.secondKill.dto.Exposer;
import org.secondKill.dto.SeckillExecution;
import org.secondKill.entity.Seckill;
import org.secondKill.exception.RepeatKillException;
import org.secondKill.exception.SeckillCloseException;
import org.secondKill.exception.SeckillException;

import java.util.List;

/**
 * 业务接口：站在【使用者】的角度设计接口
 * 三个方面：方法定义力度， 参数（简练直接），返回类型（类型要友好）
 *
 *
 */
public interface SeckillService {

    /**
     * 查询所有秒杀记录
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀接口
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * 秒杀开启时，输出秒杀接口地址。否则，输出系统时间和秒杀时间
     * @param seckillId
     */
    Exposer exportSeckillUrl (long seckillId);


    /**
     * 秒杀执行操作
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
        throws SeckillException, RepeatKillException, SeckillCloseException;

    /**
     * 秒杀执行操作 by 存储过程
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     */
    SeckillExecution executeSeckillProcedure(long seckillId, long userPhone, String md5);
}
