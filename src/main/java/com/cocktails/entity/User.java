package com.cocktails.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="user")
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(name = "password", nullable = false, unique = true, length = 128)
    private String password;

    @ManyToMany
            @JoinTable(
                    name = "favourite",
                    joinColumns = @JoinColumn(name = "user_id"),
                    inverseJoinColumns = @JoinColumn(name = "recipe_id")
            )
    Set<Recipe> favouriteRecipes;
}
