package com.limo.lb.learn.jdk.java8.io;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import io.netty.util.NettyRuntime;

import java.util.HashMap;
import java.util.Hashtable;

public class Netty {
    public static void main(String[] args) {
        System.out.println(NettyRuntime.availableProcessors());
    }
}

class SimpleClient {
    public static void main(String[] args) throws InterruptedException {
        //事件循环组
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup(20);
        try {
            //创建客户端启动对象
            Bootstrap bootstrap = new Bootstrap();
            //设置相关参数
            bootstrap.group(eventLoopGroup)//设置事件线程组
                    .channel(NioSocketChannel.class)//设置客户单通道的实现
                    .handler(new ChannelInitializer<SocketChannel>() {//处理器
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new SimpleClientHandler());
                        }
                    });
            System.out.println("客户端。ok");
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 6668);
            channelFuture.channel().closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

}

class SimpleServer {
    public static void main(String[] args) throws InterruptedException {
        //boss只负责接待客户端的accept请求
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //打工人负责监听读写IO
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
        //创建服务器的启动对象
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)//设置两个线程组
                .channel(NioServerSocketChannel.class)//使用 NioServerSocketChannel 作为通道的实现
                .option(ChannelOption.SO_BACKLOG, 128)//设置线程队列的连接个数
                .option(ChannelOption.SO_KEEPALIVE, true)//设置保持活动连接状态
                .childHandler(new ChannelInitializer<SocketChannel>() {//创建一个通道初始对象
                    //给pipline 设置处理器 handler
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
//                        socketChannel.pipeline().addLast(new SimpleServerHandler());//TCP
                        socketChannel.pipeline().addLast(new HttpServerCodec()).addLast(new HttpHandler());//HTTP
                    }
                });
        System.out.println("服务器准备好了");
        //绑定端口 同步处理，返回一个ChannelFuture
        ChannelFuture sync = bootstrap.bind(9977).sync();
        //对关闭通道监听
        sync.channel().closeFuture().sync();
    }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

class SimpleClientHandler extends ChannelInboundHandlerAdapter{
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,server,🐱",CharsetUtil.UTF_8));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //ByteBuf netty中的对象
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("服务端[" + ctx.channel().remoteAddress() + "]的消息内容是 ：" + byteBuf.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}

class SimpleServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 读取客户端发送的消息内容
     * ChannelHandlerContext 上下文对象
     * msg 客户端的消息体对象
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //ByteBuf netty中的对象
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("客户端[" + ctx.channel().remoteAddress() + "]的消息内容是 ：" + byteBuf.toString(CharsetUtil.UTF_8));
    }

    /**
     * 读取消息结束
     * 返回客户端的内容
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //写入数据并刷新，需要对发送时数据进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端", CharsetUtil.UTF_8));
    }

    /**
     * 发生异常后关闭通道
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
class HttpHandler extends SimpleChannelInboundHandler<HttpObject>{
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {
        if (httpObject instanceof HttpRequest){
            System.out.println(httpObject.getClass());
            System.out.println(channelHandlerContext.channel().remoteAddress());

            ByteBuf byteBuf = Unpooled.copiedBuffer("hello ,http server back ,客户端",CharsetUtil.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,byteBuf);
            response.headers()
                    .set(HttpHeaderNames.CONTENT_TYPE,"text/plain;charset=utf-8")
                    .set(HttpHeaderNames.CONTENT_LENGTH,byteBuf.readableBytes());
            channelHandlerContext.writeAndFlush(response);
        }
    }
}
