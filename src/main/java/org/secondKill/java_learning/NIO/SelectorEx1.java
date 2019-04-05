package org.secondKill.java_learning.NIO;

import java.nio.channels.SelectionKey;

public class SelectorEx1 {

    public static void main(String[] args) {
        int a = SelectionKey.OP_ACCEPT;
        System.out.println(a);
    }
}
