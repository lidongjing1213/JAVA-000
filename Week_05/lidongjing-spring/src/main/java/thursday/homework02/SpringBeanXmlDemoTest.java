package thursday.homework02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import thursday.homework02.xmlconfig.Customer;

/**
 * 〈一句话功能简述〉<br>
 * 〈xml配置）〉
 *
 * @author lidongjing
 * @Date: 2020/11/18 14:44
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class SpringBeanXmlDemoTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        //1、xml配置bean
        Customer customer=(Customer)context.getBean("customer");
        System.out.println(customer.toString());




    }
}
