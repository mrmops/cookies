package com.newZcookies.cookies.Controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class ExceptionsController {

    @ExceptionHandler
    public String exceptionOfEverything(Exception ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error";
    }
}