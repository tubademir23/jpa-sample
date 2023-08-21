package com.tr.springboot.rest.example.person.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDto {
    private String searchTerm;
    private SorguTipi SorguTipi;

}
