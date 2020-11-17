package com.limo.lb.learn.jdk.java8.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //绑定端口号设置非阻塞模式
        serverSocketChannel.bind(new InetSocketAddress(9900)).configureBlocking(false);
        //建立一个多路复用器
        Selector selector = Selector.open();
        //注册服务端通道
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            if (selector.select(1000) == 0) {
                System.out.println("线程 id= " + Thread.currentThread().getId() + " name= " + Thread.currentThread().getName() + "无事件发生");
                continue;
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            //通过迭代器遍历事件key
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                //事件发生
                SelectionKey selectionKey = iterator.next();
                //监听事件
                if (selectionKey.isAcceptable()) {
                    //获取客户端通道
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //设置客户端连接为非阻塞式
                    socketChannel.configureBlocking(false);
                    //将通道注册到多路复用器中 ，并表明监听事件 （接收缓冲区）
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if (selectionKey.isReadable()) {
                    //通过事件key 获取通道
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    //获取注册时 使用的缓冲区
                    ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                    //从通道中读取数据放入缓冲区
                    socketChannel.read(buffer);
                    System.out.println("客户端数据为 ：" + new String(buffer.array()));
                    //清空缓冲区
                    buffer.clear();
                    //数据传输完成后需要主动关闭客户端连接，不然会一直循环
                    socketChannel.close();
                }
            }
            //删除已操作过的事件key
            iterator.remove();
        }
    }
}

class NIOClient{
    public static void main(String[] args) throws IOException {
        //建立网络传输通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9900));
        //分配指定大小的非直接缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(new Date().toString().getBytes());
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        byteBuffer.clear();

        socketChannel.close();
    }
}
