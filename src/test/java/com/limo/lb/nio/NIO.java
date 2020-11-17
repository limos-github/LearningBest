package com.limo.lb.nio;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;

@SpringBootTest
public class NIO {
    @Test
    public void client() throws IOException {
        //建立网络传输通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9988));
        //分配指定大小的非直接缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(new Date().toString().getBytes());
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        byteBuffer.clear();

        socketChannel.close();
    }

    @Test
    public void server() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9988)).configureBlocking(false);
        //建立选择器
        Selector selector = Selector.open();
        //注册选择器 ，监听接收事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //获取选择器上已准备就绪的时间
        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                //判断是什么准备就绪
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) {

                } else if (selectionKey.isConnectable()) {

                } else if (selectionKey.isReadable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.register(selector,SelectionKey.OP_READ);
                    ByteBuffer allocate = ByteBuffer.allocate(1024);
                    while (socketChannel.read(allocate) != -1) {
                        while (allocate.hasRemaining()) {
                            allocate.flip();
                            System.out.println(allocate.toString());
                            allocate.clear();
                        }
                    }
                    socketChannel.close();
                } else if (selectionKey.isWritable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    ByteBuffer allocate = ByteBuffer.allocate(1024);
                    while (socketChannel.read(allocate) != -1) {
                        while (allocate.hasRemaining()) {
                            allocate.flip();
                            System.out.println(allocate.toString());
                            allocate.clear();
                        }
                    }
                    socketChannel.close();
                } else {
                    break;
                }
            }
        }
        selector.close();
        serverSocketChannel.close();
    }
}
