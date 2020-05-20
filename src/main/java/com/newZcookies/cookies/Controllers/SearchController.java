package com.newZcookies.cookies.Controllers;

import com.newZcookies.cookies.Comment;
import com.newZcookies.cookies.Recipe;
import com.newZcookies.cookies.Tag;
import com.newZcookies.cookies.User;
import com.newZcookies.cookies.servises.RecipeService;
import com.newZcookies.cookies.servises.TagService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class SearchController {
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/search")
    public String searchPage(Model model) {
        List<Recipe> recipes = recipeService.allRecipes();
        List<Tag> tags = tagService.findAllTags();
        model.addAttribute("tags", tags);
        model.addAttribute("recipes", recipes);
        return "searchPage";
    }

    @RequestMapping("/search/find@{text}")
    public String addComment(@PathVariable("text") String text, Model model) throws NotFoundException {
        Set<Tag> tags = new HashSet<Tag>();
        for (String e: text.split(" ")
             ) {
            tags.addAll(tagService.findByNameContains(e));
        }
        Set<Recipe> recipes = recipeService.findByDescriptionContainsOrNameContainsAndTagsContaining(text, text, tags);
        model.addAttribute("recipes", recipes);
        return "addRecipeListFragment::resultList";
    }
}
