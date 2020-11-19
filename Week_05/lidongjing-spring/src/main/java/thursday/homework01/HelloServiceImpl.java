package thursday.homework01;

/**
 * 〈一句话功能简述〉<br>
 * 〈被代理类〉
 *
 * @author lidongjing
 * @Date: 2020/11/19 16:08
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello(String name) {
        System.out.println("Hello " + name);
    }

    @Override
    public void sayGoodBye(String name) {
        System.out.println(name + " GoodBye");
    }
}
