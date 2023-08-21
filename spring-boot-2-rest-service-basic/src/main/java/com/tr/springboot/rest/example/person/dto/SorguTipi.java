package com.tr.springboot.rest.example.person.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter

public enum SorguTipi {
    FIRST(1, "FIRST",List.of(1), 1,-1),
    SECOND(2,"SECOND",List.of(1,2),-1, 1);

    private int val;
    private String valText;
    private List<Integer> personType;
    private int idFilter;
    private int nameFilter;

    public SorguTipi of(String name){
        for(SorguTipi sorguTipi:values()){
            if(sorguTipi.valText.equals(name))
                return sorguTipi;
        }
        return null;
    }
    public static SorguTipi of(Integer val){
        for(SorguTipi sorguTipi:values()){
            if(sorguTipi.val==val)
                return sorguTipi;
        }
        return null;
    }

}
