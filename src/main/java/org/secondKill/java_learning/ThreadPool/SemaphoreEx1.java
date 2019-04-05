package org.secondKill.java_learning.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreEx1 {

    private static final int THREAD_COUNT = 30;//总共的线程数
    private static ExecutorService threadPool = Executors.newFixedThreadPool(4);
    private static Semaphore semaphore = new Semaphore(6);//可以并发执行的线程数

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            final int j=i;
            threadPool.execute(new Runnable() {
                public void run() {
                    try {
                        semaphore.acquire();//acquire()获取一个许可证
                        /**
                         * 要执行的代码块
                         */
                        Thread.sleep(2000);
                        System.out.println(j+"zl");
                        semaphore.release();//使用完之后调用release()归还许可证

                    } catch (InterruptedException e) {
                    }

                }
            });
        }
        threadPool.shutdown();
    }
}
