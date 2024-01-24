package com.example.educate.controllers;

import com.example.educate.consts.OnCreate;
import com.example.educate.consts.OnUpdate;
import com.example.educate.dtos.CourseRequest;
import com.example.educate.entities.Course;
import com.example.educate.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/course")
//@CrossOrigin("http://localhost:3000")
@CrossOrigin
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/add")
    public ResponseEntity<String> addCourse(@RequestBody @Validated(OnCreate.class) CourseRequest courseRequest) {
        courseService.addCourse(courseRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Course added successfully");
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable("id") String id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCourse(@RequestBody @Validated(OnUpdate.class) CourseRequest courseRequest) {
        courseService.updateCourse(courseRequest);
        return ResponseEntity.ok("Course updated successfully");
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable("id") String id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok("Course deleted successfully");
    }
}
