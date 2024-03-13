package com.example.restapi.services;

import com.example.restapi.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final RestTemplate restTemplate;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    private final CacheManager cacheManager;

    @Cacheable("users")
    @Override
    public void deleteUserById(Long id) {
        restTemplate.delete(BASE_URL + id);
    }

    @Cacheable("users")
    @Override
    public void updateUser(UserDto user) {
        restTemplate.put(BASE_URL + user.getId(), user);
    }

    @Cacheable("users")
    @Override
    public UserDto getUserById(Long id) {
        return restTemplate.getForObject(BASE_URL + "/users/" + id, UserDto.class);
    }

    @Cacheable("users")
    @Override
    public UserDto saveUser(UserDto user) {
        return restTemplate.postForObject(BASE_URL + "/users", user, UserDto.class);
    }

    @CacheEvict(value = {"users"}, allEntries = true)
    public void clearCache() {
        cacheManager.getCache("users").clear();
    }
}
