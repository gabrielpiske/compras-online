package com.julioepiske.appLojaOnline.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("erro", "");
        return "index";
    }

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }
}
