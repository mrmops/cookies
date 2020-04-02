package com.newZcookies.cookies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Control{

    @Autowired
    private UserDataBase db;

    @GetMapping("/addUser")
    public String Register(){
        return "registerPage";
    }
}
