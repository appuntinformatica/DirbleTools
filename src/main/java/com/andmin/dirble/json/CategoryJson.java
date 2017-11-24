package com.andmin.dirble.json;

import lombok.*;

@Getter     
@Setter     
@ToString
public class CategoryJson {
    
    Long id;
    
    String title;
    
    String description;
    
    Long ancestry;
}
