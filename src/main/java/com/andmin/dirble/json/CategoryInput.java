package com.andmin.dirble.json;

import com.google.gson.annotations.*;
import lombok.*;

@Getter     
@Setter     
@ToString
public class CategoryInput {

    @Expose
    Long id;
    
    @Expose
    String title;

    @Expose    
    String description;
    
    @Expose    
    String slug;
    
    @Expose
    Long ancestry;
}
