package org.secondKill.jvm;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderEx3 {

    public static void main(String[] args) throws ClassNotFoundException {

        ClassLoader myClassLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {

                String fileName = name.substring(name.lastIndexOf(".")) + ".class";
                InputStream inputStream = getClass().getResourceAsStream(fileName);
                if (inputStream == null) {
                    return super.loadClass(name);
                }
                byte[] b = null;
                try {
                    b = new byte[inputStream.available()];
                } catch (IOException e) {
                    System.out.println(e);
                }
                return defineClass(name, b, 0, b.length);
            }
        };
        Object obj = myClassLoader.loadClass("org.secondKill.jvm.ClassLoaderEx3");
        System.out.println(obj.getClass());
        System.out.println(obj instanceof org.secondKill.jvm.ClassLoaderEx3);

    }

}
