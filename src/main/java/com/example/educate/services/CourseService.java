package com.example.educate.services;

import com.example.educate.dtos.CourseRequest;
import com.example.educate.entities.Course;
import com.example.educate.exceptions.NotFoundException;
import com.example.educate.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public void addCourse(CourseRequest courseRequest) {
        courseRepository.save(new Course(
                courseRequest.getName(),
                courseRequest.getContent(),
                courseRequest.getAuthor(),
                courseRequest.getTags(),
                LocalDateTime.now()
        ));
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAllByOrderByCreatedAtDesc();
    }

    public Course getCourseById(String id) {
        return courseRepository.findById(id).orElseThrow(() -> new NotFoundException("Course not found"));
    }

    public void updateCourse(CourseRequest courseRequest) {
        Course course = courseRepository.findById(courseRequest.getId())
                .orElseThrow(() -> new NotFoundException("Course not found"));
        if (courseRequest.getName() != null &&
                !courseRequest.getName().isEmpty() &&
                !courseRequest.getName().equals(course.getName())) {
            course.setName(courseRequest.getName());
        }
        if (courseRequest.getContent() != null &&
                !courseRequest.getContent().isEmpty() &&
                !courseRequest.getContent().equals(course.getContent())) {
            course.setContent(courseRequest.getContent());
        }
        if (courseRequest.getAuthor() != null &&
                !courseRequest.getAuthor().isEmpty() &&
                !courseRequest.getAuthor().equals(course.getAuthor())) {
            course.setAuthor(courseRequest.getAuthor());
        }
        if (courseRequest.getTags() != null &&
                !courseRequest.getTags().isEmpty() &&
                !courseRequest.getTags().equals(course.getTags())) {
            course.setTags(courseRequest.getTags());
        }
        courseRepository.save(course);
    }

    public void deleteCourse(String id) {
        if (courseRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Course not found");
        }
        courseRepository.deleteById(id);
    }
}
