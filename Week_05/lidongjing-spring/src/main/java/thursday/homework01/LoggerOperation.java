package thursday.homework01;

import java.lang.reflect.Method;

/**
 * 〈一句话功能简述〉<br>
 * 〈代理类〉
 *
 * @author lidongjing
 * @Date: 2020/11/19 16:09
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class LoggerOperation {

    public void start(Method method){
        System.out.println("日志开始Method start....");
    }

    public void end(Method method){
        System.out.println("日志结束Method end....");
    }
}
