package com.example.restapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDto {
    private Long id;
    private String city;
    private String street;
    private String suite;
    private String zipcode;
    private GeoDto geo;
}
