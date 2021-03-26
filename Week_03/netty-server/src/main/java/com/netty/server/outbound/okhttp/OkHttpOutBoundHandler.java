package com.netty.server.outbound.okhttp;

import com.netty.server.router.HttpEndpointRouter;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author ldj
 * @Date: 2021/3/24 16:35
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class OkHttpOutBoundHandler {

    private OkHttpClient httpClient;

    private ExecutorService executorService;



    public OkHttpOutBoundHandler() {
        int corePoolSize = Runtime.getRuntime().availableProcessors() * 2;
        long keepAliveTime = 1000;
        int queueSize = 2048;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(queueSize);
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();
        this.executorService = new ThreadPoolExecutor(corePoolSize, corePoolSize, keepAliveTime, TimeUnit.MILLISECONDS, workQueue, handler);
        this.httpClient = new OkHttpClient();
    }

    public void handle(final ChannelHandlerContext ctx, final FullHttpRequest request, String backendUrl) {
        final String url = backendUrl.endsWith("/") ? backendUrl.substring(0, backendUrl.length() - 1) : backendUrl +request.uri();
        executorService.submit(() -> fullGet(ctx, request, url));
    }

    private void fullGet(ChannelHandlerContext ctx, FullHttpRequest request, String url) {
        Request.Builder Builder = new Request.Builder().url(url);
        //添加请求头
        HttpHeaders httpHeadersRequest = request.headers();
        for (String key : httpHeadersRequest.names()) {
            String value = httpHeadersRequest.get(key);
            Builder.addHeader(key, value);

        }
        Request request1 = Builder.build();

        httpClient.newCall(request1).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                System.out.println("OkHttpClient" + e.getMessage());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                getFullHttpResponse(ctx, request, response);
            }
        });
    }

    private void getFullHttpResponse(ChannelHandlerContext ctx, FullHttpRequest request, Response response) {
        FullHttpResponse httpResponse = null;
        try {
            byte[] body = response.body().string().getBytes();
            httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(body));
            httpResponse.headers().set("Content-Type", "application/json");
            httpResponse.headers().set("Content-Length", Integer.parseInt(response.header("Content-Length")));
        } catch (IOException e) {
            e.printStackTrace();
            httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NO_CONTENT);
            exceptionCaught(ctx, e);
        } finally {
            if (null != request) {
                if (!HttpUtil.isKeepAlive(request)) {
                    ctx.write(httpResponse).addListener(ChannelFutureListener.CLOSE);
                } else {
                    httpResponse.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
                    ctx.write(httpResponse);
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
