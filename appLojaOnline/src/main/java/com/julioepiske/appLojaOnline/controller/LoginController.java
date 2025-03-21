package com.julioepiske.appLojaOnline.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.julioepiske.appLojaOnline.model.User;
import com.julioepiske.appLojaOnline.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class LoginController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        // Exibe qualquer mensagem de erro, se necessário
        model.addAttribute("erro", ""); // Mensagem vazia inicialmente
        return "index";
    }

    // @PostMapping("/login")
    // public String authenticateUser(@RequestParam String email,
    //                                @RequestParam String senha,
    //                                Model model) {
    //     // Buscar o usuário pelo email
    //     return userService.findByEmail(email).map(user -> {
    //         // Verificar se a senha corresponde
    //         if (passwordEncoder.matches(senha, user.getPassword())) {
    //             model.addAttribute("user", user);
    //             return "redirect:/home";
    //         } else {
    //             model.addAttribute("erro", "Senha incorreta.");
    //             return "index";  // Retorna para a página de login com erro
    //         }
    //     }).orElseGet(() -> {
    //         model.addAttribute("erro", "Usuário não encontrado.");
    //         return "index";  // Retorna para a página de login com erro
    //     });
    // }

    @PostMapping("/register")
    public String registerUser(@Validated @ModelAttribute("user") User user, 
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";  // Se houver erros de validação, retorna para o formulário
        }

        // Verifica se o email já está cadastrado
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            model.addAttribute("error", "Email já cadastrado.");
            return "index";  // Retorna para a página de login com erro
        }

        // Codifica a senha antes de salvar
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);

        model.addAttribute("mensagem", "Cadastro realizado com sucesso!");
        return "redirect:/login";  // Redireciona para o login após o registro
    }

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }
}
