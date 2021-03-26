package netty;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author ldj
 * @Date: 2020/10/28 23:42
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class HttpClientTest {
    public static void main(String[] args) {

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("https://sgrp.suning.com");
        try {
            HttpResponse response = httpClient.execute(httpPost);
            if (null != response) {
                HttpEntity httpEntity = response.getEntity();
                if (null != httpEntity) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(httpEntity.getContent()));
                    String responseStr;
                    while ((responseStr = in.readLine()) != null) {
                        System.out.println(responseStr);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
