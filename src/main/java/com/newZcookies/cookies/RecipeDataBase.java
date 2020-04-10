package com.newZcookies.cookies;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeDataBase extends CrudRepository<Recipe, Long> {
    public List<Recipe> findAllTop5ByOrderByRatingAsc();
}
