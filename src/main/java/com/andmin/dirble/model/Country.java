package com.andmin.dirble.model;

import com.google.gson.annotations.*;
import javax.persistence.*;
import lombok.*;

@Table(name = "country")
@Entity
@Getter     
@Setter     
@ToString
public class Country {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Expose
    @Column(name = "country_name")
    String name;
    
    @Expose
    @SerializedName("country_code")
    @Column(name = "country_code", unique = true)
    String countryCode;
    
    @Expose
    @Column(name = "country_region")
    String region;
    
    @Expose
    @Column(name = "country_subregion")
    String subregion;
}
