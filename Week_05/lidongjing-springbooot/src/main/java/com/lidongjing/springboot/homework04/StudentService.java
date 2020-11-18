package com.lidongjing.springboot.homework04;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author lidongjing
 * @Date: 2020/11/18 17:52
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class StudentService {
    private Student student;

    public StudentService(Student student) {
        this.student = student;
    }

    public String student() {
        return "id" + student.getId() + ",name" + student.getName();
    }
}
