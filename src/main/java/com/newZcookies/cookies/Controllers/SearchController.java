package com.newZcookies.cookies.Controllers;

import com.newZcookies.cookies.Recipe;
import com.newZcookies.cookies.servises.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public class SearchController {
    @Autowired
    private RecipeService recipeService;

    @GetMapping("/search")
    public String searchPage(Model model) {
        List<Recipe> recipes;
        recipes = recipeService.allRecipes();
        model.addAttribute("recipes", recipes);
        return "searchPage";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
}
