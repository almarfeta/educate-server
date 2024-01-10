package com.example.educate.controllers;

import com.example.educate.dtos.CourseDTO;
import com.example.educate.entities.Course;
import com.example.educate.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/course")
@CrossOrigin
public class CourseController {
    @Autowired
    private final CourseService courseService;

    @PostMapping("/add")
    public void addCourse(@RequestBody CourseDTO courseDTO) {courseService.addCourse(courseDTO);}

    @GetMapping("/get/all")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/get/id/{id}")
    public Course getCourse(@PathVariable("id") String id) {
        return courseService.getCourseById(id);
    }
}
