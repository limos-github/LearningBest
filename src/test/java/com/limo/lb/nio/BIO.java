package com.limo.lb.nio;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


@SpringBootTest
public class BIO {
    @Test
    public void client() throws IOException {
        //建立网络传输通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8899));
        //建立文件读取通道
        FileChannel fileChannel = FileChannel.open(Paths.get("D:\\1.jpg"), StandardOpenOption.READ);
        //分配指定大小的非直接缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //文件通道循环读取文件放入缓冲区
        while (fileChannel.read(byteBuffer) != -1) {
            //转换为写模式
            byteBuffer.flip();
            //通过网络通道将缓冲区的内容传输
            socketChannel.write(byteBuffer);
            //清空缓冲区
            byteBuffer.clear();
        }
        socketChannel.shutdownOutput();
        int len = 0;
        while ((len = socketChannel.read(byteBuffer)) != -1) {
            byteBuffer.flip();
            System.out.println(new String(byteBuffer.array(), 0, len));
            byteBuffer.clear();
        }
        //关闭通道
        fileChannel.close();
        socketChannel.close();
    }

    @Test
    public void server() throws IOException {
        //建立服务端通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open().bind(new InetSocketAddress(8899));
        //建立文件传输通道
        FileChannel fileChannel = FileChannel.open(Paths.get("D:\\2.jpg"), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        //分配指定大小的非直接缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //获取客户端连接的网络传输通道
        SocketChannel socketChannel = serverSocketChannel.accept();
        //循环从客户端通道读取内容放入缓冲区
        while (socketChannel.read(byteBuffer) != -1) {
            //转换写模式
            byteBuffer.flip();
            //将缓冲区中的内容写入文件
            fileChannel.write(byteBuffer);
            //清空缓冲区
            byteBuffer.clear();
        }
        byteBuffer.put("接收数据成功".getBytes());
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        //关闭通道
        fileChannel.close();
        socketChannel.close();
        serverSocketChannel.close();
    }
}
