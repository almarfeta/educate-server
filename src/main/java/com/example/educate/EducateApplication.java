package com.example.educate;

import com.example.educate.entities.Course;
import com.example.educate.entities.Post;
import com.example.educate.repositories.CourseRepository;
import com.example.educate.repositories.PostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EducateApplication {

	public static void main(String[] args) {
		SpringApplication.run(EducateApplication.class, args);
	}
}
