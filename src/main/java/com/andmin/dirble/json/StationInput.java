package com.andmin.dirble.json;

import com.google.gson.annotations.*;
import java.util.List;
import lombok.*;

@Getter     
@Setter     
@ToString
public class StationInput {
    
    @Expose
    Long id;
    
    @Expose
    String name;
    
    @Expose
    String country;
    
    @Expose
    StationImageInput image;
    
    @Expose
    String slug;
    
    @Expose
    String website;
    
    @Expose
    String twitter;
    
    @Expose
    String facebook;
    
    @Expose
    @SerializedName("total_listeners")
    Integer totalListeners;
    
    @Expose
    List<CategoryInput> categories;
    
    @Expose
    List<StreamInfoInput> streams;
    
    @Expose
    @SerializedName("created_at")
    String dateCreated;
    
    @Expose
    @SerializedName("updated_at")
    String dateUpdated;
}
