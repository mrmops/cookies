package com.newZcookies.cookies.Controllers;

import com.newZcookies.cookies.User;
import com.newZcookies.cookies.servises.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {
    @Autowired
    private UserService userService;


    @GetMapping()
    public String main(Model model) {

        model.addAttribute("formUser", new User());

        return "registration";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("formUser") @Valid User userForm, BindingResult bindingResult, Model model) {
        model.addAttribute("formUser", userForm);
        if (bindingResult.hasErrors() || !userService.saveUser(userForm)) {
            return "registration";
        }
        return "redirect:/";
    }
}
