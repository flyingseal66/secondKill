package org.secondKill.java_learning.NIO;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOEx1 {

    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("src/main/java/org/secondKill/java_learning/NIO/nio-data.txt", "rw");
        FileChannel channel = aFile.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(100);

        int byteRead = channel.read(buffer);

        while (byteRead != -1) {
            System.out.println("read: " +byteRead);
            buffer.flip();

            while (buffer.hasRemaining()) {
                System.out.println((char) buffer.get());
            }

            buffer.clear();
            byteRead = channel.read(buffer);
        }

        aFile.close();

    }
}
