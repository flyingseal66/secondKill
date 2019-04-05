package org.secondKill.java_learning.ThreadPool;

import java.util.concurrent.CountDownLatch;

/**
 * Example about CountDownLatch
 */
public class LatchEx1 {

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch startLatch = new CountDownLatch(1);
        final CountDownLatch endLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            WorkerThread thread = new WorkerThread("" + i);
            Thread t = new Thread(() -> {
                try {
                    startLatch.await();
                    try{
                        thread.run();
                    } finally {
                        endLatch.countDown();
                    }
                } catch (InterruptedException e){
                }
            });
            t.start();
        }

        long start = System.currentTimeMillis();
        startLatch.countDown();
        endLatch.await();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
