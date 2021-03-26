package com.netty.server.outbound.http;

import com.netty.server.filter.HttpRequestFilter;
import com.netty.server.outbound.http.HttpOutBoundHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author ldj
 * @Date: 2021/3/24 11:55
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class HttpInBoundHandler extends ChannelInboundHandlerAdapter {

    private String proxyServer;

    private HttpOutBoundHandler handler;
    private HttpRequestFilter filter;

    public HttpInBoundHandler(String proxyServer) {
        this.proxyServer = proxyServer;
        this.handler = new HttpOutBoundHandler(proxyServer);
        this.filter = new HttpRequestFilter();
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
            filter.filter(fullHttpRequest, ctx);
            handler.handle(ctx, fullHttpRequest);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        } finally {
            ReferenceCountUtil.release(msg);
        }

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        super.exceptionCaught(ctx, cause);
    }
}
