package com.newZcookies.cookies.Controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionsController {
    public ModelAndView exceptionOfEverything(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error", ex.getMessage());
        return modelAndView;
    }
}
