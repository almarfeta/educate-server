package com.example.educate.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
public class Post {

    @Id
    private String id;
    private String title;
    private String content;
    private String author;
    private String tags;
    private LocalDateTime createdAt;

    public Post(String title, String content, String author, String tags) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.tags = tags;
        this.createdAt = LocalDateTime.now();
    }
}
