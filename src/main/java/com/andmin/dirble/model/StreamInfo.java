package com.andmin.dirble.model;

import com.google.gson.annotations.*;
import javax.persistence.*;
import lombok.*;

@Table(name = "stream_info")
@Entity
@Getter     
@Setter     
@ToString
public class StreamInfo {
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "station_id")
    Station station;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    
    @Expose
    @Column(name = "stream")
    String stream;
    
    
    @Expose
    @Column(name = "bitrate")
    Long bitrate;
    
    @Expose
    @SerializedName("content_type")
    @Column(name = "content_type")
    String contentType;
    
    @Expose
    @Column(name = "status")
    Long status;
    
    @Expose
    @Column(name = "listeners")
    Long listeners;
}
