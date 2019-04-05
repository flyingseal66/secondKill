package org.secondKill.dao;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.apache.mahout.cf.taste.model.DataModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.secondKill.entity.Seckill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

/**
 * 配置spring和jUnit整合，使jUnit启动时加载SpringIOC容器
 * spring-test,junit
 */

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    @Autowired
    private SeckillDao seckillDao = null;

    @Test
    public void queryById() {
        long id = 1000;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }
    @Test
    public void reduceNumber() {
        Date killTime = new Date();
        int updateDount = seckillDao.reduceNumber(1000L, killTime);
        System.out.println("updateDount:" + updateDount);
    }

    @Test
    public void queryAll() {
        // Java没有保存形参的记录， queryAll(int offset, int limit) -> queryAll(arg0, arg1)
        //
        List<Seckill> seckills = seckillDao.queryAll(0, 100);
        for (Seckill seckill : seckills) {
            System.out.println(seckill);
        }
    }



}