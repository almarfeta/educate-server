package com.example.educate.services;

import com.example.educate.dtos.CourseDTO;
import com.example.educate.entities.Course;
import com.example.educate.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public void addCourse(CourseDTO courseDTO) {
        courseRepository.save(new Course(
                courseDTO.getName(),
                courseDTO.getContent(),
                courseDTO.getAuthor(),
                courseDTO.getTags(),
                LocalDateTime.now()
        ));
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(String id) {
        return courseRepository.findById(id).orElseThrow(() -> new IllegalStateException("Course not found"));
    }
}
