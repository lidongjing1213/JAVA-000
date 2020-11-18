package com.lidongjing.springboot;

import com.lidongjing.springboot.homework04.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LdjSpringBootApplication implements CommandLineRunner {
	@Autowired
	private StudentService studentService;


	public static void main(String[] args) {
		SpringApplication.run(LdjSpringBootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(studentService.student());
	}
}
