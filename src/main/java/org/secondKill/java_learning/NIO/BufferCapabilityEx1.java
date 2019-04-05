package org.secondKill.java_learning.NIO;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;

public class BufferCapabilityEx1 {

    public static void main(String[] args) throws IOException {

        RandomAccessFile file = new RandomAccessFile("nio-data.txt", "rw");
        FileChannel channel = file.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1);

        int read = channel.read(buffer);

        while (read != -1) {
            buffer.flip();
            while (buffer.hasRemaining())
            {
                System.out.print((char) buffer.get());
            }
            buffer.compact();
            read = channel.read(buffer);
        }

    }

}
