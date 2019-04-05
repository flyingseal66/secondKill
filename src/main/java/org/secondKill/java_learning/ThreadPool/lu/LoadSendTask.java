package org.secondKill.java_learning.ThreadPool.lu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;

@Component
public class LoadSendTask implements Runnable{
    private final Logger logger = LoggerFactory.getLogger(LoadSendTask.class);


    LinkedBlockingQueue<Integer> sendTaskQueue = AppConfig.getAppConfig().getSycnTaskQueue();

    @Override
    public void run() {
        logger.info("进入LoadSendTask, queue size:" + sendTaskQueue.size());
        if (sendTaskQueue.isEmpty()) {
            for (int i = 0; i < 10; i++) {
                try {

                    sendTaskQueue.put(i);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        }
        logger.info("离开LoadSendTask, queue size:" + sendTaskQueue.size());

    }
}
