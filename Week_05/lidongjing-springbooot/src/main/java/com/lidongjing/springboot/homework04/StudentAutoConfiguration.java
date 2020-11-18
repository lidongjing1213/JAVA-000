package com.lidongjing.springboot.homework04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author lidongjing
 * @Date: 2020/11/18 17:54
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@ConditionalOnClass(StudentService.class)
@EnableConfigurationProperties(Student.class)
@Configuration
public class StudentAutoConfiguration {

    @Autowired
    private Student student;

    @Bean
    @ConditionalOnMissingBean(StudentService.class)
    public StudentService getStudentService() {
        return new StudentService(student);
    }
}
