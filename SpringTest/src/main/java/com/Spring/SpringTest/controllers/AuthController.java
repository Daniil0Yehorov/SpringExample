package com.Spring.SpringTest.controllers;

import com.Spring.SpringTest.Repos.UserRepository;
import com.Spring.SpringTest.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/reg")
     public String RegUpdate(@RequestParam String login,@RequestParam String password,
                             @RequestParam String passwordCheck,Model model){
        User user = userRepository.findByLogin(login);
        boolean loginExists = false;
        if(user!=null){
        // CheckLogin for same
            if (user.getLogin().equals(login)) {
                loginExists = true;
            }
        if (!password.equals(passwordCheck)) {
            model.addAttribute("error", "Passwords do not match");
            return "registration"; // back to registration with error
        }
        if (loginExists) {
            model.addAttribute("error", "Login already exists");
            return "registration";// back to registration with error
        }}
        User newUser = new User(login, password);
        userRepository.save(newUser);
        model.addAttribute("UserName",newUser.getLogin());
        model.addAttribute("loggedIn",true);//True to destroy links for reg and login
        return "home";
    }
    @PostMapping("/log")
    public String Login(@RequestParam String login,@RequestParam String password,Model model)
    {
        User user = userRepository.findByLogin(login);
        // CheckLogin for same
        if (user == null) {
            model.addAttribute("error", "Login doesn't exist");
        } else {
            if (!user.getPassword().equals(password)) {
                model.addAttribute("error", "Password is wrong");
            }
            else {
                model.addAttribute("UserName",user.getLogin());
                model.addAttribute("loggedIn",true);//True to destroy links for reg and login
                return "home";
            }
        }
        return "login";
    }

}
