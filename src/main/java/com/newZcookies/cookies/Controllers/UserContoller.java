package com.newZcookies.cookies.Controllers;

import com.newZcookies.cookies.Role;
import com.newZcookies.cookies.User;
import com.newZcookies.cookies.repository.RoleRepository;
import com.newZcookies.cookies.servises.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserContoller {

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/{id}")
    public String userDetails(Model model, @PathVariable(value="id") Long id, Principal currentlyUser){
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("id_role", (Long)1L);
        model.addAttribute("currentlyUser", currentlyUser.getName());
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("listRoles", roles);
        return "userDetails";
    }

    @PostMapping("/{id}")
    public String addRoleToUser(@PathVariable(value = "id") Long user_id, @ModelAttribute("id_role") Long id_role) {
        User user = userService.findUserById(user_id);
        user.getRoles().add(roleRepository.findById(id_role).orElse(new Role("OTHER")));
        userService.updateUser(user);
        return "redirect:/admin/users";
    }


    @GetMapping("/userName/{name}")
    public String userDetails(Model model, @PathVariable(value="name") String name, Principal currentlyUser){
        User user = userService.findUserByUserName(name);
        return "redirect:/users/" + user.getId();
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(Model model, @PathVariable(value="id") Long id, Principal currentlyUser){
        User user = userService.findUserById(id);
        User cUser = userService.findUserByUserName(currentlyUser.getName());
        if(cUser.isAdmin() || user == cUser)
            userService.deleteUser(user);
        else throw new IllegalArgumentException("У вас нет прав!");
        return "redirect:/search";
    }
}
