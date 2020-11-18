package thursday.homework02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import thursday.homework02.javaconfig.ClassicalMusic;
import thursday.homework02.autoconfig.CompactDisc;
import thursday.homework02.javaconfig.JavaConfig;

/**
 * 〈一句话功能简述〉<br>
 * 〈java配置〉
 *
 * @author lidongjing
 * @Date: 2020/11/18 16:14
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class JavaConfigDemoTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext configApplicationContext=new AnnotationConfigApplicationContext(JavaConfig.class);

        CompactDisc compactDisc=(ClassicalMusic)configApplicationContext.getBean("classicalMusic");
        compactDisc.play();
    }
}
