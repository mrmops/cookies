package com.newZcookies.cookies.Controllers;

import com.newZcookies.cookies.Recipe;
import com.newZcookies.cookies.RecipeDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class MainController {
    @Autowired
    private RecipeDataBase recipeDataBase;

    @GetMapping("/")
    public String mainPage(Model model){

        Iterable<Recipe> recipes = recipeDataBase.findAllTop5ByOrderByRatingAsc();

        model.addAttribute("recipes", recipes);
        return "mainPage";
    }
}

