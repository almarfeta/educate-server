package com.example.educate.services;

import com.example.educate.dtos.PostRequest;
import com.example.educate.entities.Post;
import com.example.educate.exceptions.NotFoundException;
import com.example.educate.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void addPost(PostRequest postRequest) {
        postRepository.save(new Post(
                postRequest.getTitle(),
                postRequest.getContent(),
                postRequest.getAuthor(),
                postRequest.getTags(),
                LocalDateTime.now()
        ));
    }

    public List<Post> getAllPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    public Post getPostById(String id) {
        return postRepository.findById(id).orElseThrow(() -> new NotFoundException("Post not found"));
    }

    public void updatePost(PostRequest postRequest) {
        Post post = postRepository.findById(postRequest.getId())
                .orElseThrow(() -> new NotFoundException("Post not found"));
        if (postRequest.getTitle() != null &&
                !postRequest.getTitle().isEmpty() &&
                !postRequest.getTitle().equals(post.getTitle())) {
            post.setTitle(postRequest.getTitle());
        }
        if (postRequest.getContent() != null &&
                !postRequest.getContent().isEmpty() &&
                !postRequest.getContent().equals(post.getContent())) {
            post.setContent(postRequest.getContent());
        }
        if (postRequest.getAuthor() != null &&
                !postRequest.getAuthor().isEmpty() &&
                !postRequest.getAuthor().equals(post.getAuthor())) {
            post.setAuthor(postRequest.getAuthor());
        }
        if (postRequest.getTags() != null &&
                !postRequest.getTags().isEmpty() &&
                !postRequest.getTags().equals(post.getTags())) {
            post.setTags(postRequest.getTags());
        }
        postRepository.save(post);
    }

    public void deletePost(String id) {
        if (postRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Post not found");
        }
        postRepository.deleteById(id);
    }
}
