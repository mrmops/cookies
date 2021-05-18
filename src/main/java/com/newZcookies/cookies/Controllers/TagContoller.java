package com.newZcookies.cookies.Controllers;

import com.newZcookies.cookies.Recipe;
import com.newZcookies.cookies.Tag;
import com.newZcookies.cookies.User;
import com.newZcookies.cookies.servises.CommentService;
import com.newZcookies.cookies.servises.RecipeService;
import com.newZcookies.cookies.servises.TagService;
import com.newZcookies.cookies.servises.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/tags")
public class TagContoller {
    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/add/{name}")
    public String addTagToPage(@PathVariable(value = "name") String name, Model model) {
        tagService.saveTag(new Tag(name));
        List<Tag> tags = tagService.findAllTags();
        model.addAttribute("tags", tags);
        return "addRecipePage :: tagList";
    }
}
