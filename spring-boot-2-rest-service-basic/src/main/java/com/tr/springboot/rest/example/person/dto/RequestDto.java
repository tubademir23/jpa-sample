package com.tr.springboot.rest.example.person.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestDto {
    private String searchTerm;
    private SorguTipi sorguTipi;
   private Integer idFilter=-1;
   private Integer nameFilter=-1;
   private List<Integer> personTip;
   public RequestDto(Integer sorguTipiVal){
       SorguTipi sorguTipi= SorguTipi.of(sorguTipiVal);
       this.setSorguTipi(sorguTipi);
       this.idFilter=sorguTipi.getIdFilter();
       this.nameFilter=sorguTipi.getNameFilter();
       this.personTip=sorguTipi.getPersonType();
   }

}
