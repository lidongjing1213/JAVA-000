package com.netty.server.outbound.http;


import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author ldj
 * @Date: 2021/3/24 12:05
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class HttpOutBoundHandler {

    private String backendUrl;

    private CloseableHttpAsyncClient httpAsyncClient;

    private ExecutorService executorService;


    public HttpOutBoundHandler(String backendUrl) {
        this.backendUrl = backendUrl.endsWith("/") ? backendUrl.substring(0, backendUrl.length() - 1) : backendUrl;
        int corePoolSize = Runtime.getRuntime().availableProcessors() * 2;
        long keepAliveTime = 1000;
        int queueSize = 2048;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(queueSize);
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();
        this.executorService = new ThreadPoolExecutor(corePoolSize, corePoolSize, keepAliveTime, TimeUnit.MILLISECONDS, workQueue, handler);

        IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
                .setConnectTimeout(1000)
                .setSoTimeout(1000)
                .setIoThreadCount(corePoolSize)
                .setRcvBufSize(32 * 1024)
                .build();
        this.httpAsyncClient = HttpAsyncClients.custom()
                .setMaxConnTotal(40)
                .setMaxConnPerRoute(8)
                .setDefaultIOReactorConfig(ioReactorConfig)
                .setKeepAliveStrategy(((httpResponse, httpContext) -> 6000)).build();
        httpAsyncClient.start();
    }

    public void handle(final ChannelHandlerContext ctx, final FullHttpRequest request) {
        final String url = backendUrl + request.uri();
        executorService.submit(() -> fullGet(ctx, request, url));
    }

    private void fullGet(ChannelHandlerContext ctx, FullHttpRequest request, String url) {
        HttpHeaders httpHeadersRequest = request.headers();
        HttpGet httpGet = new HttpGet(url);
        //添加请求头
        for (String key : httpHeadersRequest.names()) {
            String value=httpHeadersRequest.get(key);
            httpGet.addHeader(key,value );
            System.out.println("httpGet header,key:"+key+" value:"+value);
        }
        httpAsyncClient.execute(httpGet, new FutureCallback<org.apache.http.HttpResponse>() {
            @Override
            public void completed(org.apache.http.HttpResponse httpResponse) {
                getFullHttpResponse(ctx, request, httpResponse);
            }

            @Override
            public void failed(Exception e) {
                httpGet.abort();
                System.out.println(e.getMessage());
            }

            @Override
            public void cancelled() {
                httpGet.abort();
            }
        });
    }

    private void getFullHttpResponse(ChannelHandlerContext ctx, FullHttpRequest request, org.apache.http.HttpResponse httpResponse) {
        FullHttpResponse response = null;
        try {

            byte[] body = EntityUtils.toByteArray(httpResponse.getEntity());
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(body));

            response.headers().set("Content-Type", "application/json");
            response.headers().set("Content-Length", Integer.parseInt(httpResponse.getFirstHeader("Content-Length").getValue()));

        } catch (IOException e) {
            e.printStackTrace();
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NO_CONTENT);
            exceptionCaught(ctx, e);
        } finally {
            if (null != request) {
                if (!HttpUtil.isKeepAlive(request)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
                    ctx.write(response);
                }
            }
            ctx.flush();
        }
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
