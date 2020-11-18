package com.lidongjing.springboot.homework04;



import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(prefix = "student")
public class Student {
    private int id;
    private String name;

}
