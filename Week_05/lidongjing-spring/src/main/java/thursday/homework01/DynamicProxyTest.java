package thursday.homework01;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author lidongjing
 * @Date: 2020/11/19 16:18
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class DynamicProxyTest {
    public static void main(String[] args) {
        HelloService helloService=(HelloService)new HelloDynamicProxy().bind(new HelloServiceImpl(),new LoggerOperation());
        helloService.sayHello("ldj");
        helloService.sayGoodBye("ldj");
    }
}
