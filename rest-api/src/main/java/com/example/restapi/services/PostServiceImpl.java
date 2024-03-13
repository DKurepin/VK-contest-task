package com.example.restapi.services;

import com.example.restapi.dto.PostDto;
import lombok.AllArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;


@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

//    @Override
//    public Comment saveComment(Comment Comment) {
//        return commentRepository.save(Comment);
//    }
    @Autowired
    private final RestTemplate restTemplate;

    private final CacheManager cacheManager;

    @Cacheable("posts")
    @Override
    public PostDto getPostById(Long id) {
        return restTemplate.getForObject(BASE_URL + "/posts/" + id, PostDto.class);
    }

    @Cacheable("posts")
    @Override
    public PostDto savePost(PostDto post) {
        PostDto response = restTemplate.postForObject(BASE_URL + "/posts", post, PostDto.class);
        return response;
    }
    @Cacheable("posts")
    @Override
    public void deleteByPostId(Long id) {
        restTemplate.delete(BASE_URL + "/posts/" + id);
    }

    @Cacheable("posts")
    @Override
    public void updatePost(PostDto post) {
        restTemplate.put(BASE_URL + "/posts/" + post.getId(), post);
    }

    @CacheEvict(value = {"posts"}, allEntries = true)
    public void clearCache() {
        cacheManager.getCache("posts").clear();
    }
}
