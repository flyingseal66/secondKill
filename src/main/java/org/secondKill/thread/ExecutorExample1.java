package org.secondKill.thread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorExample1 {

    private static final int NTHERADS = 100;

    private static final Executor exec = Executors.newFixedThreadPool(NTHERADS);


    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        List<Future<String>> callableList = new ArrayList<>();


        for (int i = 0; i < 10; i++) {
            cachedThreadPool.submit(new MyCallable());
        }

        Thread.sleep(100);
        for (Future<String> future : callableList) {
            if (future.isDone()) {
                System.out.println(future.get());
            } else {
                System.out.println("future result is not completed");
            }
        }

    }
}

class MyCallable implements Runnable {

    @Override
    public void run() {
        System.out.println("Enter Callable");
    }
}
