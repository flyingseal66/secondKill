package org.secondKill.jvm;

public class ClassloaderEx2 {

    static {
        System.out.println("super class initialization");
    }

    public static final String HELLO_WORLD = "hello worls";
}

class Notinitialization{

    public static void main(String[] args) {
        System.out.println(ClassloaderEx2.HELLO_WORLD);
        System.out.println(ClassloaderEx2.class);
    }

}
