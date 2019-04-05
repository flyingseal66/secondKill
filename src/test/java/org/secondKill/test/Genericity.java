package org.secondKill.test;

import java.nio.Buffer;
import java.util.*;

public class Genericity {

    private List<Object> list = new ArrayList<>();

    public void add() {
        for (int i = 0; i < 1000; i++) {
            list.add(new Object());
        }
    }


    public static void main(String[] args) throws InterruptedException {

        Genericity genericity = new Genericity();


        Thread thread1 = new Thread( () -> {
            genericity.add();
        });

        Thread thread2 = new Thread( () -> {
            genericity.add();
        });
        thread1.start();
        thread2.start();

        Thread.sleep(1000);
        System.out.println(genericity.list.size());




    }



}
