package thursday.homework03;

import lombok.Data;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * @author lidongjing
 * @Date: 2020/11/18 10:55
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Data
public class Student implements Serializable {
    private static final long serialVersionUID = -4947940610217504263L;
    private int id;
    private String name;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


}
