package thursday.homework03;

import lombok.Data;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * @author lidongjing
 * @Date: 2020/11/18 10:57
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Data
public class Klass {
   private List<Student> studentList;

   @Override
   public String toString() {
      return "Klass{" +
              "studentList=" + studentList +
              '}';
   }
}
