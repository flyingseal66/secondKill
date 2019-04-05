package org.secondKill.java_learning.ThreadPool;

import java.util.concurrent.*;

/**
 * User: 杨永生
 * Date: 15:47 2017/8/8
 * Email: kevin@hiibook.com
 */
public class ThreadPoolOnQueueEx2 {

    public static void main(final String[] arguments) throws InterruptedException {
        final ScheduledThreadPoolExecutor scheduler =
                (ScheduledThreadPoolExecutor)Executors.newScheduledThreadPool(1);

        final ScheduledFuture<?> beepHandler =
                scheduler.scheduleAtFixedRate(new BeepTask(), 1, 2, TimeUnit.SECONDS);

        /*scheduler.schedule(new Runnable() {

            @Override
            public void run() {
                beepHandler.cancel(true);
                scheduler.shutdown();
            }
        }, 10, TimeUnit.SECONDS);*/
    }

    static class BeepTask implements Runnable {

        public void run() {
            System.out.println("beep");
        }
    }
}