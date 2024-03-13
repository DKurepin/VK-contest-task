package com.example.restapi.controllers;


import com.example.restapi.dto.PostDto;
import com.example.restapi.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    @GetMapping("/posts/{id}")
    @PreAuthorize("hasAuthority('read')")
    public PostDto getPostById(@PathVariable("id") Long id) {
        return postService.getPostById(id);
    }

    @PostMapping("/posts")
    @PreAuthorize("hasAuthority('update')")
    public PostDto savePost(@RequestBody PostDto post) {
        return postService.savePost(post);
    }

    @PutMapping("/posts/{id}")
    @PreAuthorize("hasAuthority('update')")
    public void updatePost(@RequestBody PostDto post) {
        postService.updatePost(post);
    }

    @DeleteMapping("/posts/{id}")
    @PreAuthorize("hasAuthority('update')")
    public void deletePost(@PathVariable("id") Long id) {
        postService.deleteByPostId(id);

    }
}
