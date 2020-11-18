package thursday.homework03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 〈一句话功能简述〉<br>
 * 〈实现一个 Spring XML 自定义配置，配置一组 Bean，例如：Student/Klass/School〉
 * @author lidongjing
 * @Date: 2020/11/18 14:15
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class SpringXmlBeanDemoTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student = (Student) context.getBean("student");
        System.out.println(student.toString());

        Klass klass=(Klass)context.getBean("klass");
        System.out.println(klass.toString());

        School school=(School)context.getBean("school");
        System.out.println(school.toString());

    }
}
