package com.andmin.dirble.json;

import com.google.gson.annotations.Expose;
import lombok.*;

@Getter     
@Setter     
@ToString
public class StationImageInput {
    @Expose
    String url;
    
    @Expose
    StationImageInput thumb;
}
