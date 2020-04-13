package com.newZcookies.cookies.servises;

import com.newZcookies.cookies.Recipe;
import com.newZcookies.cookies.Role;
import com.newZcookies.cookies.User;
import com.newZcookies.cookies.repository.RecipeRepository;
import com.newZcookies.cookies.repository.RoleRepository;
import com.newZcookies.cookies.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Service
public class RecipeService{
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
        if (recipeFromDB != null) {
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
}
