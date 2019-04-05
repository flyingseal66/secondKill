package org.secondKill.java_learning.ThreadPool;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPoolEx1 {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newScheduledThreadPool(4);
        Executors.newScheduledThreadPool(10);
        for (int i = 0; i < 10; i++) {
            Runnable worker = new WorkerThread("" + i);
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");
        ReentrantLock lock = new ReentrantLock(true);

    }
}
