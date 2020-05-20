package com.newZcookies.cookies.repository;

import com.newZcookies.cookies.Recipe;
import com.newZcookies.cookies.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    public List<Recipe> findByName(String name);
    public Optional<Recipe> findById(Long id);
    public List<Recipe> findAll();
    public List<Recipe> findTop10ByOrderByRatingDesc();
    public List<Recipe> findByTags_Name(String name);
    public List<Recipe> findByDescriptionContainsIgnoreCaseOrNameContainsIgnoreCase(String text, String name);
    public List<Recipe> findByTagsContains(Tag tag);
}
