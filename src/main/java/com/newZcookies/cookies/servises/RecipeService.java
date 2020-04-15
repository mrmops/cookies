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

    public Recipe findRecipeById(Long recipeId) throws NotFoundException {
        Optional<Recipe> recipeFromDataBase = recipeRepository.findById(recipeId);
        if(!recipeFromDataBase.isPresent())
            throw new NotFoundException("Recipes not found");
        return recipeFromDataBase.orElse(new Recipe());
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
        return recipe.getAuthor().getId().equals(user.getId());
    }

    public List<Recipe> findTop5Recipes(){
        return recipeRepository.findTop5ByOrderByRatingAsc();
    }


}
