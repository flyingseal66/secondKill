package org.secondKill.java_learning.ThreadPool.lu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;


public class LuThread {

    private static final Logger logger = LoggerFactory.getLogger(LuThread.class);

    // build thread pool
    private static final int CORE_POOL_SIZE = 10;
    private static final int SYNC_TASK_SIZE = 5;
    private static final int SEND_TASK_SIZE = 5;
    private static final int INITIAL_DELAY = 0;
    private static final int DELAY = 1000;
    private static final int CAPACITY = 500;
    private static final LinkedBlockingQueue<Integer> syncTaskQueue = new LinkedBlockingQueue<Integer>(CAPACITY);
    private static final LinkedBlockingQueue<Integer> sendTaskQueue = new LinkedBlockingQueue<Integer>(CAPACITY);

    public void init() throws InterruptedException {

        //loadTask
        LoadSendTask loadSendTask = new LoadSendTask();
        ScheduledThreadPoolExecutor threadPoolExecutor = new ScheduledThreadPoolExecutor(4);
        threadPoolExecutor.scheduleWithFixedDelay(loadSendTask,INITIAL_DELAY,DELAY, TimeUnit.MILLISECONDS);

        //sendTask
        //SendTask sendTask = new SendTask();
        //threadPoolExecutor.execute(sendTask);

    }

    public static void main(String[] args) throws InterruptedException {
        LuThread thread = new LuThread();
        thread.init();
    }
}



