package org.secondKill.thread;

import java.util.Arrays;
import java.util.List;

public class ThreadExample1 implements Cloneable{

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread());
        });

        int[] a = {1,2};
        List list = Arrays.asList(a);
        list.add(2);
    }

}
