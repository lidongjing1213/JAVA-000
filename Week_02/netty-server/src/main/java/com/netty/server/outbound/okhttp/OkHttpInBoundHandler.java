package com.netty.server.outbound.okhttp;

import com.netty.server.filter.HttpRequestFilter;
import com.netty.server.router.HttpEndpointRouter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author 19041663
 * @Date: 2021/3/24 11:55
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class OkHttpInBoundHandler extends ChannelInboundHandlerAdapter {

    private OkHttpOutBoundHandler handler;

    private HttpRequestFilter filter;
    private HttpEndpointRouter router;

    public OkHttpInBoundHandler(List<String> proxyServerList) {
        this.handler = new OkHttpOutBoundHandler();
        this.filter = new HttpRequestFilter();
        this.router=new HttpEndpointRouter(proxyServerList);
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
            //过滤器
            filter.filter(fullHttpRequest, ctx);
           //添加路由
            String backendUrl =  router.roundRibbon();

            handler.handle(ctx, fullHttpRequest,backendUrl);
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
