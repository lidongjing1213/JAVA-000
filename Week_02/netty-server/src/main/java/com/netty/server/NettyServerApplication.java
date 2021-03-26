package com.netty.server;



import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class NettyServerApplication {


    public static void main(String[] args) {

        List<String> urlList=new ArrayList<>();
        urlList.add("http://localhost:8803");
        urlList.add("http://localhost:8808");
        NettyServer nettyServer = new NettyServer();
        nettyServer.run("8804", urlList);
    }

}
