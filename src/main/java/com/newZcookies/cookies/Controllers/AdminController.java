package com.newZcookies.cookies.Controllers;

import com.newZcookies.cookies.Role;
import com.newZcookies.cookies.User;
import com.newZcookies.cookies.servises.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String listUsers(Model model){
        model.addAttribute("users", userService.allUsers());
        return "usersPage";
    }

    @RequestMapping(value = "/users/{id}/delete", method = RequestMethod.GET)
    public String handleDeleteUser(@PathVariable Long id) {
        userService.deleteUser(userService.findUserById(id));
        return "redirect:/admin/users";
    }

}
