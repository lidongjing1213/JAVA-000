package com.netty.client.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.*;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author ldj
 * @Date: 2021/3/24 11:04
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class HttpClientChannelInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline channelPipeline = socketChannel.pipeline();
        channelPipeline.addLast(new HttpServerCodec());
//        channelPipeline.addLast(new HttpRequestDecoder());
//        channelPipeline.addLast(new HttpResponseEncoder());
        channelPipeline.addLast(new HttpObjectAggregator(1024 * 1024*64));
        channelPipeline.addLast(new HttpClientOutBoundHandler());
    }
}
