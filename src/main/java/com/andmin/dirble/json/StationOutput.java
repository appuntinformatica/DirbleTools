package com.andmin.dirble.json;

import com.google.gson.annotations.*;
import lombok.*;

@Getter     
@Setter     
@ToString
public class StationOutput {
    
    @Expose
    Long id;
    
    @Expose
    String name;
    
    @Expose
    String country;
    
    @Expose
    String streamUrl;
    
    @Expose
    String imageUrl;
    
    @Expose
    Long bitrate;

}
