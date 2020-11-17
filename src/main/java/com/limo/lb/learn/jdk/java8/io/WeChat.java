package com.limo.lb.learn.jdk.java8.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class WeChat {
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private final static int PORT = 9703;
    private final static int BYTE_SIZE = 1024;

    public WeChat() {
        try {
            selector = Selector.open();
            serverSocketChannel = serverSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(PORT)).configureBlocking(false).register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("服务器端已启动完毕。。。");
        } catch (IOException e) {
            System.out.println("服务器端已启动失败！！！");
            e.printStackTrace();

        }
    }

    //监听
    public void serverlisten() {
        try {
            while (true) {
                if (selector.select(1000) > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey next = iterator.next();
                        if (next.isAcceptable()) {
                            //注册方法
                            SocketChannel socketChannel = serverSocketChannel.accept();
                            socketChannel.configureBlocking(false).register(selector, SelectionKey.OP_READ);
                            System.out.println("客户端[" + socketChannel.getRemoteAddress() + "] 连接服务器");
                        }
                        if (next.isReadable()) {
                            readClient(next);
                        }
                        if (next.isConnectable()) {

                        }
                        if (next.isWritable()) {

                        }
                        iterator.remove();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void readClient(SelectionKey selectionKey) {
        SocketChannel socketChannel = null;
        try {
            socketChannel = (SocketChannel) selectionKey.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(BYTE_SIZE);
            if (socketChannel.read(byteBuffer)>0){
                byteBuffer.flip();
                System.out.println("客户端[" + socketChannel.getRemoteAddress() + "] : " + new String(byteBuffer.array(),"UTF-8"));
            }
            //消息转发
            sendmsg(byteBuffer.array().toString(), socketChannel);
        } catch (Exception e) {
            try {
                System.out.println("客户端[" + socketChannel.getRemoteAddress() + "] 连接关闭 ");
                socketChannel.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 消息转发
     */
    private void sendmsg(String msg, SocketChannel now) {
        try {
            ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
            for (SelectionKey other : selector.keys()) {
                Channel channel = other.channel();
                if (channel instanceof SocketChannel && !channel.equals(now)) {
                    byteBuffer.flip();
                    ((SocketChannel) channel).write(byteBuffer);
                }
                byteBuffer.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        WeChat weChat = new WeChat();
        weChat.serverlisten();
    }
}
