package com.andmin.dirble.model;

import com.google.gson.annotations.*;
import javax.persistence.*;
import lombok.*;

@Table(name = "stream_image")
@Entity
@Getter     
@Setter     
@ToString
public class StationImage {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    
    @Expose
    @Column(name = "url", columnDefinition = "text")
    String url;
    
    @Expose
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "thumb_id", nullable = true)            
    StationImage thumb;
}
