package com.andmin.dirble.json;

import com.google.gson.annotations.*;
import lombok.*;

@Getter     
@Setter     
@ToString
public class StreamInfoInput {

    @Expose    
    String stream;

    @Expose
    Long bitrate;
    
    @Expose
    @SerializedName("content_type")
    String contentType;
    
    @Expose
    Long status;
    
    @Expose
    Long listeners;

}