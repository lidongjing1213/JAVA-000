package week02;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author 19041663
 * @Date: 2020/10/29 11:38
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class HttpServer02 {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8082);
            while (true) {
                final Socket socket = serverSocket.accept();
                new Thread(() -> server(socket)).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void server(Socket socket) {
        try {
            Thread.sleep(20);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            printWriter.println();
            printWriter.println("Hello World 02");
            printWriter.close();
            socket.close();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
