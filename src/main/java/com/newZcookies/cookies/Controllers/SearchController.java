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
@RequestMapping("/search/")
public class SearchController {
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private TagService tagService;

    @GetMapping()
    public String searchPage(Model model) {
        List<Recipe> recipes = recipeService.allRecipes();
        List<Tag> tags = tagService.findAllTags();
        model.addAttribute("tags", tags);
        model.addAttribute("recipes", recipes);
        return "searchPage";
    }

    @GetMapping("/find+{text}")
    public String searchPageFind(@PathVariable("text") String text, Model model) {
        model.addAttribute("recipes", findRecipesByTagsAndText(text));
        model.addAttribute("searchText", text);
        return "searchPage";
    }

    @RequestMapping("/find@{text}")
    public String helpSearch(@PathVariable("text") String text, Model model) {
        model.addAttribute("recipes", findRecipesByTagsAndText(text));
        return "searchPage::resultList";
    }

    @GetMapping("/find")
    public String redirectSearch(@ModelAttribute("searchInput") String text, Model model) {
        model.addAttribute("recipes", findRecipesByTagsAndText(text));
        return "searchPage";
    }

    private Set<Recipe> findRecipesByTagsAndText(String text){
        Set<Tag> tags = new HashSet<Tag>();
        for (String e: text.split(" ")
        ) {
            tags.addAll(tagService.findByNameContains(e));
        }
        return recipeService.findByDescriptionContainsOrNameContainsAndTagsContaining(text, text, tags);
    }
}
