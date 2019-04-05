package org.secondKill.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LockObject implements Runnable{

    private static int a = 0;

    private static Object lock = new Object();

    @Override
    public void run() {

        for (int i = 0; i < 1000; i++) {
            a++;
            synchronized (lock) {

            }

        }

        System.out.println(a);
    }


    public static void main(String[] args) {

//        ExecutorService pool = Executors.newFixedThreadPool(10);
//        LockObject lockObject = new LockObject();
//        for (int i = 0; i < 1000; i++) {
//            pool.execute(lockObject);
//        }

        LockObject object1 = new LockObject();
        Thread thread = new Thread(object1);
        Thread thread1 = new Thread(object1);
        Thread thread2 = new Thread(object1);
        Thread thread3 = new Thread(object1);
        Thread thread4 = new Thread(object1);

        thread.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }


}
