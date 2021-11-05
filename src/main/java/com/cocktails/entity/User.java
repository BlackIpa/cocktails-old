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
    Set<Recipe> favouriteRecipes;

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

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				'}';
	}

	// add a convenience method
    public void addRecipeToFavourites(Recipe recipe) {
		System.out.println("We're in User entity, addRecipeToFavourites helper method");
		System.out.println("Recipe is "  + recipe);
s
		if (favouriteRecipes == null) {
			favouriteRecipes = new HashSet<>();
		}

		favouriteRecipes.add(recipe);
    }


}
