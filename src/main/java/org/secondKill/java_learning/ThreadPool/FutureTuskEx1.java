package org.secondKill.java_learning.ThreadPool;

import org.secondKill.entity.Seckill;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class FutureTuskEx1 {

    private final FutureTask<Seckill> futureTask = new FutureTask<Seckill>(new Callable<Seckill>() {
        @Override
        public Seckill call() throws Exception {
            Seckill seckill = new Seckill(100, "xiaomi4");
            return seckill;
        }
    });

    private final Thread thread = new Thread(futureTask);
    public void start() {thread.start();}

    public Seckill get() throws Throwable {
        try {
            return futureTask.get();
        } catch (Exception e) {
            Throwable cause = e.getCause();
            throw cause;
        }
    }

    private  int a;
    public FutureTuskEx1() {
        a = 9;
    }

    public static void main(String[] args) {
        FutureTuskEx1 ex1 = new FutureTuskEx1();
        ex1.start();
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
    }

}
