package week02;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author 19041663
 * @Date: 2020/10/29 11:56
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class OkHttpClientTest {
    public static void main(String[] args) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://localhost:8801").build();
        Call call = okHttpClient.newCall(request);
        Response response= null;
        try {
            response = call.execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
