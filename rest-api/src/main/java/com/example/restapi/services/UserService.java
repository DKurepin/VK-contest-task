package com.example.restapi.services;

import com.example.restapi.dto.UserDto;

public interface UserService {
    void deleteUserById(Long id);
    void updateUser(UserDto user);
    UserDto getUserById(Long id);
    UserDto saveUser(UserDto user);
}
