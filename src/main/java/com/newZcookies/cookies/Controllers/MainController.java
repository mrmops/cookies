package com.newZcookies.cookies.Controllers;

import com.newZcookies.cookies.Recipe;
import com.newZcookies.cookies.User;
import com.newZcookies.cookies.servises.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class MainController {
    @Autowired
    private RecipeService recipeService;

    @GetMapping("/")
    public String mainPage(Model model){

        Iterable<Recipe> recipes = recipeService.findTop5Recipes();

        model.addAttribute("recipes", recipes);
        return "mainPage";
    }
}

