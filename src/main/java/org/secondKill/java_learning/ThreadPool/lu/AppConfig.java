package org.secondKill.java_learning.ThreadPool.lu;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class AppConfig {

    public static final int CAPACITY = 500;
    public static final int CORE_POOL_SIZE = 10;
    public static final int SYNC_TASK_SIZE = 5;
    public static final int INITIAL_DELAY = 0;
    public static final int DELAY = 1000;

    private static AppConfig appConfig;
    private LinkedBlockingQueue<Integer> sycnTaskQueue;
    private ScheduledThreadPoolExecutor threadPool;

    private AppConfig() {
        sycnTaskQueue = new LinkedBlockingQueue<Integer>(CAPACITY);
        threadPool = new ScheduledThreadPoolExecutor(CORE_POOL_SIZE);
    }

    public LinkedBlockingQueue<Integer> getSycnTaskQueue() {
        return sycnTaskQueue;
    }

    public ScheduledThreadPoolExecutor getThreadPool() {
        return threadPool;
    }

    public static AppConfig getAppConfig() {
        if (appConfig == null) {
            appConfig = new AppConfig();
        }

        return appConfig;
    }
}
