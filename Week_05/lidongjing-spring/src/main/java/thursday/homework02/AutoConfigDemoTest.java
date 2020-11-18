package thursday.homework02;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;
import thursday.homework02.autoconfig.CDPlayerConfig;
import thursday.homework02.autoconfig.CompactDisc;

/**
 * 〈一句话功能简述〉<br>
 * 〈自动装配〉
 *
 * @author lidongjing
 * @Date: 2020/11/18 15:53
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CDPlayerConfig.class)
public class AutoConfigDemoTest {

    @Autowired
    private CompactDisc compactDisc;

    @Test
    public  void test(){
        //自动装配
        compactDisc.play();
    }
}
