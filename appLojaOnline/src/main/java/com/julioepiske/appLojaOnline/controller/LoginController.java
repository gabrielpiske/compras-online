package com.julioepiske.appLojaOnline.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.julioepiske.appLojaOnline.model.User;
import com.julioepiske.appLojaOnline.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("erro", "");
        return "index";
    }

    @PostMapping("/register")
    public String registerUser(@Validated @ModelAttribute("user") User user,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register"; // Se houver erros de validação, retorna para o formulário
        }

        // Verifica se o email já está cadastrado
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            model.addAttribute("error", "Email já cadastrado.");
            return "index";
        }

        userService.save(user);

        model.addAttribute("mensagem", "Cadastro realizado com sucesso!");
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }
}
