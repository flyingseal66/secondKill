package org.secondKill.java_learning.ThreadPool.lu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;

@Component
public class SendTask implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(SendTask.class);

    @Override
    public void run() {
        LinkedBlockingQueue<Integer> sendTaskQueue = AppConfig.getAppConfig().getSycnTaskQueue();

        for (;;) {
            logger.info("enter SendTask, queue size:" + sendTaskQueue.size());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                int i = sendTaskQueue.take();
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
            logger.info("after loop SendTask, queue size:" + sendTaskQueue.size());
        }



    }
}