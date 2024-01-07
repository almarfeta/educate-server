package com.example.educate.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    private String id;
    private String name;
    private String content;
    private String author;
    private String tags;
    private LocalDateTime createdAt;

    public Course(String name, String content, String author, String tags, LocalDateTime createdAt) {
        this.name = name;
        this.content = content;
        this.author = author;
        this.tags = tags;
        this.createdAt = createdAt;
    }
}
