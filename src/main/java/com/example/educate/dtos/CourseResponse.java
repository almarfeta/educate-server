package com.example.educate.dtos;

import com.example.educate.entities.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponse {

    private String id;
    private String name;
    private String author;
    private String tags;
    private LocalDateTime createdAt;
    private byte[] content;

    public CourseResponse(Course course, byte[] content) {
        this.id = course.getId();
        this.name = course.getName();
        this.author = course.getAuthor();
        this.tags = course.getTags();
        this.createdAt = course.getCreatedAt();
        this.content = content;
    }
}
