package com.newZcookies.cookies.Controllers;

import com.newZcookies.cookies.User;
import com.newZcookies.cookies.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;


    @GetMapping("/registration")
    public String main(Model model) {
        Iterable<User> users = userRepository.findAll();

        model.addAttribute("users", users);

        return "registerPage";
    }

    @PostMapping("/registration")
    public String add(@RequestParam String login, @RequestParam String name, @RequestParam String secondName, Map<String, Object> model) {
        User user = new User(login, name, secondName);

        userRepository.save(user);

        Iterable<User> users = userRepository.findAll();

        model.put("users", users);

        return "redirect:/user/" + user.getId().toString();
    }

    @GetMapping("/user/{id}")
    public String userPage(@PathVariable(value = "id") Long id, Map<String, Object> model){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent())
            return "error";
        model.put("user", user.get());
        return "userDetails";
    }

    @PostMapping("/user/{id}/remove")
    public String userDelete(@PathVariable(value = "id") Long id, Map<String, Object> model){
        userRepository.deleteById(id);
        return "redirect:/registration";
    }
}
