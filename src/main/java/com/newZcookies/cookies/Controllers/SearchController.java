package com.newZcookies.cookies.Controllers;

import com.newZcookies.cookies.Comment;
import com.newZcookies.cookies.Recipe;
import com.newZcookies.cookies.User;
import com.newZcookies.cookies.servises.RecipeService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
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

    @PostMapping("/search")
    public String addComment(@ModelAttribute("text") String text, Model model) throws NotFoundException {
        List<Recipe> recipes;
        recipes = recipeService.loadRecipeByName(text);
        model.addAttribute("recipes", recipes);
        return "searchPage";
    }
}
