package com.limo.lb.nio;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Date;

@SpringBootTest
public class AIO {
    @Test
    public void client() throws IOException {
        //建立网络传输通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",9988));
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

    }
}
