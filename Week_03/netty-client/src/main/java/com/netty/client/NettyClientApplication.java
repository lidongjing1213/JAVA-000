package com.netty.client;

import com.netty.client.http.NettyClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NettyClientApplication {

    public static void main(String[] args) {
        NettyClient nettyClient=new NettyClient();
        nettyClient.connect();
    }

}
