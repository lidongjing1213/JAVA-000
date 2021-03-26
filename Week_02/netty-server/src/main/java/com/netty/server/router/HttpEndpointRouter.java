package com.netty.server.router;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author ldj
 * @Date: 2021/3/24 16:50
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class HttpEndpointRouter {
    private static final Random random = new Random();
    private List<String> endpoints;

    private AtomicInteger num = new AtomicInteger(0);

    public HttpEndpointRouter(List<String> endpoints) {
        this.endpoints = endpoints;
    }

    //随机路由
    public String randomRouter() {
        int index = random.nextInt(endpoints.size());
        System.out.println("router:" + index);
        return endpoints.get(index);
    }

    //顺序取
    public String roundRibbon() {
        int index = num.get();
        if (index == endpoints.size() - 1) {
            num.set(0);
        } else {
            num.incrementAndGet();
        }
        System.out.println("router:" + index);
        return endpoints.get(index);

    }

}
