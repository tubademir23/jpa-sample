package commons;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

public interface Constants {
    public enum PersonType{
        Employee(1,"Employee"),
        Employer(2,"Employer");
        @Getter
        private Integer val;
        private String valText;

        PersonType(Integer val, String text){
            this.val=val;
            this.valText=text;
        }
        public static PersonType of(Integer val){
            for(PersonType personType:values()){
                if(personType.val.equals(val))
                    return personType;
            }
            return null;
        }
        @JsonCreator
        public static PersonType deserialize(@JsonProperty("val") Integer val){
            return of(val);
        }
    }

}
