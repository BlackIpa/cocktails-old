package com.cocktails.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="glass")
@Getter
@Setter
public class Glass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "glass_id")
    private Long glassId;

    @Column(name = "glass")
    private String glass;

    @Column(name = "icon")
    private String icon;

//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "recipe")
//    private Recipe recipe;
}
