package com.newZcookies.cookies.servises;

import com.newZcookies.cookies.Recipe;
import com.newZcookies.cookies.Tag;
import com.newZcookies.cookies.User;
import com.newZcookies.cookies.repository.RecipeRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class RecipeService {
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    TagService tagService;


    public List<Recipe> loadRecipeByName(String name) throws NotFoundException {
        List<Recipe> recipes = recipeRepository.findByName(name);

        if (recipes == null) {
            throw new NotFoundException("Recipes not found");
        }

        return recipes;
    }

    public Recipe findRecipeById(Long recipeId) throws NotFoundException {
        Optional<Recipe> recipeFromDataBase = recipeRepository.findById(recipeId);
        if(!recipeFromDataBase.isPresent())
            throw new NotFoundException("Recipes not found");
        return recipeFromDataBase.get();
    }

    public List<Recipe> allRecipes() {
        return recipeRepository.findAll();
    }

    public void saveRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    public boolean deleteRecipe(Long recipeId) {
        if (recipeRepository.findById(recipeId).isPresent()) {
            recipeRepository.deleteById(recipeId);
            return true;
        }
        return false;
    }

    public boolean canManage(Recipe recipe, User user)
    {
        return recipe.getAuthor().equals(user);
    }

    public List<Recipe> findTop10Recipes(){
        return recipeRepository.findTop10ByOrderByRatingDesc();
    }

    public List<Recipe> findAllRecipesByTagName(String name){
        return recipeRepository.findByTags_Name(name);
    }

    public Set<Recipe> findByDescriptionContainsOrNameContainsAndTagsContaining(String text, String name, Set<Tag> currentTags) {
        Set<Recipe> recipes = new HashSet<Recipe>();
        for (Tag e: currentTags
             ) {
            recipes.addAll(recipeRepository.findByTagsContains(e));
        }
        recipes.addAll(recipeRepository.findByDescriptionContainsIgnoreCaseOrNameContainsIgnoreCase(text, name));
        return recipes;
    }
}
