package com.example.restapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
//@Builder
public class PostDto {
    private Long userId;
    private Long id;
    private String title;
    private String body;

    public PostDto() {
    }
}
