package com.newZcookies.cookies.Controllers;

import com.newZcookies.cookies.Recipe;
import com.newZcookies.cookies.servises.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private RecipeService recipeService;

    @GetMapping("/")
    public String mainPage(Model model) {
        List<Recipe> recipes;
        recipes = recipeService.findTop10Recipes();
        model.addAttribute("recipes", recipes);
        return "mainPage";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
}

