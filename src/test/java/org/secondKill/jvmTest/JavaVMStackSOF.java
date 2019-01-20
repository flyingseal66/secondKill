package org.secondKill.jvmTest;

public class JavaVMStackSOF {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF stackSOF = new JavaVMStackSOF();
        try {
            stackSOF.stackLeak();
        } catch (Exception e) {
            System.out.println("stack Length: " + stackSOF.stackLength);
            throw e;
        }

    }

}
