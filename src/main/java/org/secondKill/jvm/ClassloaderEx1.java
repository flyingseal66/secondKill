package org.secondKill.jvm;

public class ClassloaderEx1 {

    public static int value = 123;

    static {
        System.out.println("Super class init");
    }
}

class Subclass extends ClassloaderEx1 {

    static {
        System.out.println("Subclass init");
    }
}

class NotInitialization{
    public static void main(String[] args) {
        System.out.println(Subclass.value);
    }
}
