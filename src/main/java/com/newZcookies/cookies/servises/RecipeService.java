package com.newZcookies.cookies.servises;

import com.newZcookies.cookies.Recipe;
import com.newZcookies.cookies.User;
import com.newZcookies.cookies.repository.RecipeRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {
    @Autowired
    RecipeRepository recipeRepository;


    public List<Recipe> loadRecipeByName(String name) throws NotFoundException {
        List<Recipe> recipes = recipeRepository.findByName(name);

        if (recipes == null) {
            throw new NotFoundException("Recipes not found");
        }

        return recipes;
    }

    public Recipe findRecipeById(Long recipeId) {
        Optional<Recipe> recipeFromDataBase = recipeRepository.findById(recipeId);
        return recipeFromDataBase.orElse(new Recipe());
    }

    public List<Recipe> allRecipes() {
        return recipeRepository.findAll();
    }

    public boolean saveRecipe(Recipe recipe) {
        Optional<Recipe> recipeFromDB = recipeRepository.findById(recipe.getId());
        if (recipeFromDB.isPresent()) {
            return false;
        }
        recipeRepository.save(recipe);
        return true;
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
        return recipe.getAuthor().getId().equals(user.getId());
    }

}
