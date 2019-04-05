package org.secondKill.java_learning.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;
import java.util.Set;

public class TypicalNIO {

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.bind(new InetSocketAddress("localhost",8888));
        channel.configureBlocking(false);

        SelectionKey selectionKey = channel.register(selector,SelectionKey.OP_ACCEPT);

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        CharBuffer charBuffer = CharBuffer.allocate(1024);
        CharsetDecoder decoder = Charset.defaultCharset().newDecoder();

        while (true){
            int readyNum = selector.select();
            if (readyNum <= 0){
                continue;
            }
            Set<SelectionKey> readyKey = selector.selectedKeys();
            for (SelectionKey tempKey : readyKey){
                if (tempKey.isAcceptable()){
                    ServerSocketChannel tempChannel = (ServerSocketChannel) tempKey.channel();
                    SocketChannel clientChannel = tempChannel.accept();
                    if (null != clientChannel){
                        System.out.println("one connection:" + clientChannel.getRemoteAddress());
                        clientChannel.configureBlocking(false);
                        clientChannel.register(selector,SelectionKey.OP_READ);
                    }
                }

                if(tempKey.isReadable()){
                    SocketChannel tempChannel = (SocketChannel) tempKey.channel();
                    tempChannel.read(buffer);
                    buffer.flip();
                    decoder.decode(buffer,charBuffer,false);
                    charBuffer.flip();
                    String getData = new String(charBuffer.array(),0,charBuffer.limit());
                    System.out.println(tempChannel.getRemoteAddress() + ":" + getData);
                    buffer.clear();
                    charBuffer.clear();
                    tempChannel.write(ByteBuffer.allocate(0));
                    if (getData.equalsIgnoreCase("exit")){
                        tempChannel.close();
                    }
                }

                if (tempKey.isWritable()){
                    SocketChannel tempChannel = (SocketChannel) tempKey.channel();
//                    System.out.println(tempChannel.getRemoteAddress() + ": read");
                }
                readyKey.remove(tempKey);
            }
        }
    }

    /*public static void main(String[] args) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024); // initialize a buffer with capability of 1024
        Selector selector = Selector.open(); // new a selector
        ServerSocketChannel ssc = ServerSocketChannel.open(); // new a channel
        ssc.configureBlocking(false); //channel设置非阻塞
        ssc.socket().bind(new InetSocketAddress(8080));
        ssc.register(selector, SelectionKey.OP_ACCEPT); //注册监听事件

        System.out.println(1 << 4);
        while (true) {
            Set selectedKeys = selector.selectedKeys(); //
            selector.select();
            Iterator it = selectedKeys.iterator();
            while (it.hasNext()) {
                SelectionKey key = (SelectionKey) it.next();
                if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
                    ServerSocketChannel ssChannel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = ssChannel.accept(); // 接受到服务端的请求
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                    it.remove();
                } else if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
                    SocketChannel sc = (SocketChannel) key.channel();
                    while (true) {
                        buffer.clear();
                        int n = sc.read(buffer); //读数据
                        if (n <= 0) {
                            break;
                        }
                        buffer.flip();

                    }
                    it.remove();
                }
            }
        }*/
}
