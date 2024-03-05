package com.Spring.SpringTest.controllers;

import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        

@Controller
public class MainController {

   @GetMapping("/")
    public String home( Model model) {
        model.addAttribute("title", "Main Page");
        return "home";
    }
    @GetMapping("/registration")
    public String registration( Model model) {
        model.addAttribute("title", "Registration");
        return "registration";
    }
    @GetMapping("/about")
    public String about( Model model) {
        model.addAttribute("title", "Page about us");
        return "about";
    }
    @GetMapping("/login")
    public String login( Model model) {
        model.addAttribute("title", "Log in");
        return "login";
    }
}
