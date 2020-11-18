package thursday.homework02.javaconfig;

import thursday.homework02.autoconfig.CompactDisc;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author 19041663
 * @Date: 2020/11/18 16:10
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ClassicalMusic implements CompactDisc {
    @Override
    public void play() {
        System.out.println("播放流行音乐");
    }
}
