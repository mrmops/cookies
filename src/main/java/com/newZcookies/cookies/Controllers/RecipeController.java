package com.newZcookies.cookies.Controllers;

import com.newZcookies.cookies.Comment;
import com.newZcookies.cookies.Recipe;
import com.newZcookies.cookies.Tag;
import com.newZcookies.cookies.User;
import com.newZcookies.cookies.servises.CommentService;
import com.newZcookies.cookies.servises.RecipeService;
import com.newZcookies.cookies.servises.TagService;
import com.newZcookies.cookies.servises.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private TagService tagService;

    @GetMapping("/recipe/add")
    public String addRecipePage(Model model){
        List<Tag> tags = tagService.findAllTags();
        model.addAttribute("tags", tags);
        return "addRecipePage";
    }

    @PostMapping("/recipe/add")
    public String addRecipe(@RequestParam String name, @RequestParam String description, @RequestParam Set<Long> tagList, Model model, Principal currentlyPrincipal) throws NotFoundException {
        User currentlyUser = userService.findUserByUserName(currentlyPrincipal.getName());
        Set<Tag> tags = new HashSet<Tag>();
        for (Long i: tagList
             ) {
            tags.add(tagService.findTagById(i));
        }
        Recipe recipe = new Recipe(name, description, currentlyUser, tags);
        recipeService.saveRecipe(recipe);
        return "redirect:/recipe/" + recipe.getId().toString();
    }

    @GetMapping("/recipe/{id}")
    public String recipePage(@PathVariable(value = "id") Long id, Model model, Principal currentlyPrincipal) throws NotFoundException {
        Recipe recipe = recipeService.findRecipeById(id);
        User currentlyUser = userService.findUserByUserName(currentlyPrincipal.getName());
        List<Comment> comments = commentService.findAllByRecipeId(recipe.getId());
        model.addAttribute("recipe", recipe);
        model.addAttribute("currentlyUser", currentlyUser);
        model.addAttribute("comments", comments);
        return "recipeDetails";
    }


    @GetMapping("/recipe/{id}/delete")
    public String deleteRecipe(@PathVariable(value = "id") Long id, Model model, Principal currentlyPrincipal) throws NotFoundException {
        Recipe recipe = recipeService.findRecipeById(id);
        User currentlyUser = userService.findUserByUserName(currentlyPrincipal.getName());
        if(recipe.getAuthor().equals(currentlyUser) || currentlyUser.isAdmin())
        {
            recipeService.deleteRecipe(recipe.getId());
            return "redirect:/";
        }
        else
            return "error-page";
    }

    @PostMapping("/recipe/{id}")
    public String addAppraisal(@PathVariable(value = "id") Long recipe_id, @ModelAttribute("appraisal") int appraisal, Principal currentlyPrincipal) throws NotFoundException {
        User user = userService.findUserByUserName(currentlyPrincipal.getName());
        Recipe recipe = recipeService.findRecipeById(recipe_id);
        recipeService.addAppraisals(user, recipe, appraisal);
        recipeService.saveRecipe(recipe);
        return "redirect:/recipe/" + recipe_id;
    }

    @GetMapping("/recipe/search/{name}")
    public String SearchRecipes(@PathVariable(value = "name") String name, Model model){
        List<Recipe> recipes = recipeService.findAllRecipesByTagName(name);
        model.addAttribute("find_recipes", recipes);
        return "search_page";
    }
}
