package com.newZcookies.cookies.repository;

import com.newZcookies.cookies.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    public List<Recipe> findAllTop5ByOrderByRatingAsc();
    public List<Recipe> findByName(String name);
    public Recipe findById();
    public List<Recipe> findAll();
}
