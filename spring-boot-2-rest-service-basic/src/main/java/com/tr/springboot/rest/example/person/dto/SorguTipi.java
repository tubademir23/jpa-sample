package com.tr.springboot.rest.example.person.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
public enum SorguTipi {
    FIRST(1, List.of(1)),
    SECOND(2,List.of(1,2));
    @Getter
    @Setter
    private int val;
    private List<Integer> personType;

}
