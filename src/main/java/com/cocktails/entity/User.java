package com.cocktails.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(name = "password", nullable = false, unique = true, length = 128)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
            @JoinTable(
                    name = "favourite",
                    joinColumns = @JoinColumn(name = "user_id"),
                    inverseJoinColumns = @JoinColumn(name = "recipe_id")
            )
	private Set<Recipe> favouriteRecipes;

    @OneToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "custom",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "recipe_id")
	)
	private Set<Recipe> customRecipes;

    // getters and setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Recipe> getFavouriteRecipes() {
		return favouriteRecipes;
	}

	public void setFavouriteRecipes(Set<Recipe> favouriteRecipes) {
		this.favouriteRecipes = favouriteRecipes;
	}

	public Set<Recipe> getCustomRecipes() {
		return customRecipes;
	}

	public void setCustomRecipes(Set<Recipe> customRecipes) {
		this.customRecipes = customRecipes;
	}

	// to string

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				'}';
	}

	// convenience methods
    public void toggleRecipeToFavourites(Recipe recipe) {
		System.out.println("We're in User entity, addRecipeToFavourites helper method");

		if (favouriteRecipes.contains(recipe)) {
			favouriteRecipes.remove(recipe);
		}
		else {
			favouriteRecipes.add(recipe);
		}
    }

    public void addToCustom(Recipe recipe) {
		System.out.println("We're in User entity, addToCustom helper method");

		customRecipes.add(recipe);
	}
}
