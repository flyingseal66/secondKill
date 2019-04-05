package org.secondKill.java_learning.IO;

import java.io.FileReader;
import java.io.IOException;

public class IOEX1 {

    public static void main(String[] args) throws IOException {
        StringBuffer str = new StringBuffer();
        char[] buf = new char[1024];
        FileReader reader = new FileReader("nio-data.txt");
        while (reader.read(buf)>0) {
            str.append(buf);
        }

        str.toString();
        System.out.println(str);
    }


}
