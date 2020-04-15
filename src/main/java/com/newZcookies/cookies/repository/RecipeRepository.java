package com.newZcookies.cookies.repository;

import com.newZcookies.cookies.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    public List<Recipe> findTop5ByOrderByRatingAsc();
    public List<Recipe> findByName(String name);
    public Optional<Recipe> findById(Long id);
    public List<Recipe> findAll();
}
