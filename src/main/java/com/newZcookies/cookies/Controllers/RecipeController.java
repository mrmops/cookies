package com.newZcookies.cookies.Controllers;

import com.newZcookies.cookies.Recipe;
import com.newZcookies.cookies.User;
import com.newZcookies.cookies.repository.RecipeDataBase;
import com.newZcookies.cookies.servises.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private RecipeDataBase recipeDataBase;

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

        recipeDataBase.save(recipe);


        return "redirect:/recipe/" + recipe.getId().toString();
    }

    @GetMapping("/recipe/{id}")
    public String recipePage(@PathVariable(value = "id") Long id, Model model){
        Optional<Recipe> recipe = recipeDataBase.findById(id);
        if(!recipe.isPresent())
            return "error";
        model.addAttribute("recipe", recipe.get());
        return "recipeDetails";
    }




}
