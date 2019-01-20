package org.secondKill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.secondKill.dto.Exposer;
import org.secondKill.dto.SeckillExecution;
import org.secondKill.entity.Seckill;
import org.secondKill.exception.RepeatKillException;
import org.secondKill.exception.SeckillCloseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.annotation.Target;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() {
        List<Seckill> seckills = seckillService.getSeckillList();
        logger.info("list={}", seckills);
    }

    @Test
    public void getById() {
        long id = 1000;
        Seckill seckill = seckillService.getById(id);
        logger.info("seckill = {}", seckill);
    }

    /**
     * 集成测试，涵盖完整代码逻辑
     */
    @Test
    public void exportSeckillLogic() {
        long id = 1001;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if (exposer.isExposed()) {
            logger.info("exposer={}", exposer);
            Long userPhone =3889882728L;
            String md5 = exposer.getMd5();
            try {
                SeckillExecution execution = seckillService.executeSeckill(id, userPhone, md5);
                logger.info("result:{}", execution);
            } catch (SeckillCloseException e) {
                logger.error(e.getMessage());
            } catch (RepeatKillException e) {
                logger.error(e.getMessage());
            }
        } else {
            // 秒杀未开启
            logger.warn("exposer={}", exposer);
        }
    }

    @Test
    public void executeSeckillProcedure() {
        long seckillId = 1000;
        long phone = 19029384990l;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        if (exposer.isExposed()) {
            String md5 = exposer.getMd5();
            SeckillExecution execution = seckillService.executeSeckillProcedure(seckillId,phone, md5);
            logger.info(execution.getStateInfo());
        }
    }
}