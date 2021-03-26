package netty;

import com.squareup.okhttp.*;

import java.io.IOException;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author ldj
 * @Date: 2020/10/29 11:56
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class OkHttpClientTest {
    public static void main(String[] args) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("https://www.qq.com/").build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                System.out.println("OkHttpClient" + e.getMessage());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                ResponseBody responseBody = response.body();
                if (null != responseBody) {
                    System.out.println("response" + responseBody.string());
                    responseBody.close();
                }
            }
        });

    }
}
