package com.example.restapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyDto {
    private String name;
    private String catchPhrase;
    private String bs;

}
