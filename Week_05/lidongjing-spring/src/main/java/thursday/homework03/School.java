package thursday.homework03;

import lombok.Data;


/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * @author lidongjing
 * @Date: 2020/11/18 10:58
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Data
public class School {

    private Klass klass;


    private Student student;

    @Override
    public String toString() {
        return "School{" +
                "klass=" + klass +
                ", student=" + student +
                '}';
    }
}
