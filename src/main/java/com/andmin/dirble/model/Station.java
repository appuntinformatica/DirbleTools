package com.andmin.dirble.model;

import com.google.gson.annotations.*;
import javax.persistence.*;
import lombok.*;
import java.util.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Table(name = "station")
@Entity
@Getter     
@Setter     
@ToString
public class Station {
       
    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Expose
    @Column(name = "station_name")
    String name;
    
    @Expose
    @Column(name = "country")
    String country;
    
    @Expose
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "station_image_id", nullable = true)   
    StationImage image;
    
    @Expose
    @Column(name = "slug")
    String slug;
    
    @Expose
    @Column(name = "website")
    String website;
    
    @Expose
    @Column(name = "twitter")
    String twitter;

    @Expose
    @Column(name = "facebook")
    String facebook;
    
    @Expose
    @SerializedName("total_listeners")
    @Column(name = "total_listeners")
    Long totalListeners;
        
    @Expose
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "station_category",
        joinColumns = @JoinColumn(name = "station_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")       
    )
    List<Category> categories;
    
    @Expose
    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    List<StreamInfo> streams;
           
    @Expose
    @SerializedName("created_at")
    @Column(name = "date_created_at")
    String dateCreated;
    
    @Expose
    @SerializedName("updated_at")
    @Column(name = "date_updated")
    String dateUpdated;
}
