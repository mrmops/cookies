package com.newZcookies.cookies.Controllers;

import com.newZcookies.cookies.Recipe;
import com.newZcookies.cookies.RecipeDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Iterator;
import java.util.List;

@Controller
public class RecipeController {
    @Autowired
    private RecipeDataBase recipeDataBase;

    @GetMapping("/recipe/add")
    public String addRecipePage(Model model){
        return "addRecipePage";
    }


}
