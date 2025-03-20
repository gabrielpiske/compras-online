package com.julioepiske.appLojaOnline.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.julioepiske.appLojaOnline.service.UserService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

//@AllArgsConstructor
@NoArgsConstructor
@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "index";
    }
}
