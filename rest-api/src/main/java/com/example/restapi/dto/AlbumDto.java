package com.example.restapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
//@Builder
public class AlbumDto {
    private Long userId;
    private Long id;
    private String title;

    public AlbumDto() {
    }
}
