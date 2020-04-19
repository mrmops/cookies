package com.newZcookies.cookies.Controllers;

import com.newZcookies.cookies.Comment;
import com.newZcookies.cookies.Recipe;
import com.newZcookies.cookies.User;
import com.newZcookies.cookies.servises.CommentService;
import com.newZcookies.cookies.servises.RecipeService;
import com.newZcookies.cookies.servises.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @Autowired
    RecipeService recipeService;

    @PostMapping("/recipe/{id}/addComment")
    public String addComment(@PathVariable(value = "id") Long recipe_id, @ModelAttribute("text") String text, Principal currentlyPrincipal) throws NotFoundException {
        User user = userService.findUserByUserName(currentlyPrincipal.getName());
        Recipe recipe = recipeService.findRecipeById(recipe_id);
        commentService.saveComment(new Comment(user, recipe, text));
        return "redirect:/recipe/" + recipe_id;
    }

    @GetMapping("/comment/{id}/delete")
    public String deleteComment(@PathVariable(value = "id") Long comment_id, Principal principal) throws Exception {
        Comment comment = commentService.findCommentById(comment_id);
        if(!comment.getAuthor().getUsername().equals(principal.getName()))
            throw new Exception("Вы не можете удалить этот комментарий!");
        commentService.deleteCommentById(comment_id);
        return "redirect:/recipe/" + comment.getRecipe().getId();
    }
}
