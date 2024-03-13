package com.example.restapi.services;

import com.example.restapi.dto.PostDto;
public interface PostService {

    PostDto savePost(PostDto post);
    void deleteByPostId(Long id);
    void updatePost(PostDto post);
    PostDto getPostById(Long id);

}
