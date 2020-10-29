package week02;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpStatus;
import java.io.IOException;


/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author 19041663
 * @Date: 2020/10/28 23:42
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class HttpClientTest {
    public static void main(String[] args) {
        HttpClient httpClient = new HttpClient();
        PostMethod httpMethod = new PostMethod("http://localhost:8801");
        try {
            int code = httpClient.executeMethod(httpMethod);
            if (HttpStatus.SC_OK == code) {
                String responseBody = httpMethod.getResponseBodyAsString();
                System.out.println(responseBody);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
