package com.example.restapi.controllers;

import com.example.restapi.dto.UserDto;
import com.example.restapi.services.UserService;
import lombok.RequiredArgsConstructor;
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
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{id}")
    @PreAuthorize("hasAuthority('read')")
    public UserDto getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/users")
    @PreAuthorize("hasAuthority('update')")
    public UserDto saveUser(@RequestBody UserDto user) {
        return userService.saveUser(user);
    }

    @PutMapping("/users/{id}")
    @PreAuthorize("hasAuthority('update')")
    public void updateUser(@RequestBody UserDto user) {
        userService.updateUser(user);
    }

    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasAuthority('update')")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
    }
}
