package com.andmin.dirble.model;

import com.google.gson.annotations.Expose;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Table(name = "category")
@Entity
@Getter     
@Setter     
@ToString
public class Category {
    
    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Expose
    @Column(name = "title")
    String title;

    @Expose
    @Column(name = "description", length = 65535, columnDefinition = "text")
    String description;
    
    @Expose
    @Column(name = "slug")
    String slug;
    
    @Expose
    @Column(name = "ancestry", nullable = true)    
    Long ancestry;

}
