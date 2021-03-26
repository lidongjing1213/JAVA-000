package com.netty.server.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author ldj
 * @Date: 2021/3/24 16:50
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class HttpRequestFilter {

    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        fullRequest.headers().set("NIO", "ldj");
    }
}
