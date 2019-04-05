package org.secondKill.thread;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrentLockEx1 {

    private int age;

    private void print() {
        System.out.println("000");
    }

    public static void main(String[] args) throws IOException {

        ReentrantLock lock = new ReentrantLock();

        lock.tryLock();

        ReentrentLockEx1 ex1 = new ReentrentLockEx1();
        ReentrentLockEx1 ex11 = new ReentrentLockEx1(33);
        CountDownLatch latch = new CountDownLatch(9);

        FileReader reader = new FileReader("file");
        reader.read(new char[9]);
    }

    /**
     *
     */
    public ReentrentLockEx1() {
        System.out.println("ddddd");

    }

    public ReentrentLockEx1(int age) {
        this.age = age;
    }
}
