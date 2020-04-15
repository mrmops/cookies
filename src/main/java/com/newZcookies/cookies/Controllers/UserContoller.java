package com.newZcookies.cookies.Controllers;

import com.newZcookies.cookies.Role;
import com.newZcookies.cookies.User;
import com.newZcookies.cookies.repository.RoleRepository;
import com.newZcookies.cookies.servises.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class UserContoller {

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/users/{id}")
    public String userDetails(Model model, @PathVariable(value="id") Long id, Principal currentlyUser){
        User user = userService.findUserById(id);
        model.addAttribute("user",user);
        model.addAttribute("id_role", (Long)1L);
        model.addAttribute("currentlyUser", currentlyUser.getName());
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("listRoles", roles);
        return "userDetails";
    }
    @PostMapping("/users/{id}")
    public String addRoleToUser(@PathVariable(value = "id") Long user_id, @ModelAttribute("id_role") Long id_role) {
        User user = userService.findUserById(user_id);
        user.getRoles().add(roleRepository.findById(id_role).get());
        userService.updateUser(user);
        return "redirect:/admin/users";
    }
}
