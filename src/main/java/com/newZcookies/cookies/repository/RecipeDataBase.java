package com.newZcookies.cookies.repository;

import com.newZcookies.cookies.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeDataBase extends CrudRepository<Recipe, Long> {
    public List<Recipe> findAllTop5ByOrderByRatingAsc();
}
