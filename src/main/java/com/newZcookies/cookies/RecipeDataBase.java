package com.newZcookies.cookies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeDataBase extends CrudRepository<Recipe, Integer> {
    public List<Recipe> findAllByOrderByDescriptionAsc();
}
