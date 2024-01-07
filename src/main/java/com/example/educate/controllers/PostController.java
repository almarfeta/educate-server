package com.example.educate.controllers;

import com.example.educate.consts.OnCreate;
import com.example.educate.consts.OnUpdate;
import com.example.educate.dtos.PostRequest;
import com.example.educate.entities.Post;
import com.example.educate.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/post")
public class PostController {

    private final PostService postService;

    @PostMapping("/add")
    public ResponseEntity<String> addPost(@RequestBody @Validated(OnCreate.class) PostRequest postRequest) {
        postService.addPost(postRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Post added successfully");
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<Post> getPost(@PathVariable("id") String id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updatePost(@RequestBody @Validated(OnUpdate.class) PostRequest postRequest) {
        postService.updatePost(postRequest);
        return ResponseEntity.ok("Post updated successfully");
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") String id) {
        postService.deletePost(id);
        return ResponseEntity.ok("Post deleted successfully");
    }
}
