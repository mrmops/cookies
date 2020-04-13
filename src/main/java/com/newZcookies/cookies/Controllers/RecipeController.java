package com.newZcookies.cookies.Controllers;

import com.newZcookies.cookies.Recipe;
import com.newZcookies.cookies.User;
import com.newZcookies.cookies.servises.RecipeService;
import com.newZcookies.cookies.servises.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserService userService;

    @GetMapping("/recipe/add")
    public String addRecipePage(Model model){
        return "addRecipePage";
    }

    @PostMapping("/recipe/add")
    public String addRecipe(@RequestParam String name, @RequestParam String description, Model model) {
        User currentlyUser = userService.findUserByUserName(userService.getCurrentUsername());
        if(currentlyUser == null)
            return "error";
        Recipe recipe = new Recipe(name, description, currentlyUser);

        recipeService.saveRecipe(recipe);


        return "redirect:/recipe/" + recipe.getId().toString();
    }

    @GetMapping("/recipe/{id}")
    public String recipePage(@PathVariable(value = "id") Long id, Model model){
        try{
            Recipe recipe = recipeService.findRecipeById(id);
            model.addAttribute("recipe", recipe);
        } catch (NotFoundException e) {
            return "recipeDetails-error";
        }
        return "recipeDetails";
    }




}
