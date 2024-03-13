package com.example.restapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GeoDto {
    private String lat;
    private String lng;
}