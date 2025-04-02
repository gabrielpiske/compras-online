package com.julioepiske.appLojaOnline.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.julioepiske.appLojaOnline.model.User;
import com.julioepiske.appLojaOnline.service.AuthService;
import com.julioepiske.appLojaOnline.service.UserService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @GetMapping("/user")
    public String getUserPage(Model model) {
        User user = authService.getAuthenticatedUser();
        List<User> users = userService.listAll();
        model.addAttribute("users", users);
        model.addAttribute("user", user);

        return "user";
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

    /*
     * @PostMapping("/user/edit/{id}")
     * public String updateUser(@PathVariable Long
     * id, @Validated @ModelAttribute("user") User user,
     * BindingResult result, Model model) {
     * // TODO: process POST request
     * if (result.hasErrors()) {
     * return "user";
     * }
     * Optional<User> existingUser = userService.findByEmail(user.getEmail());
     * if (existingUser.isPresent() && !existingUser.get().getId().equals(id)) {
     * model.addAttribute("error", "Email ja cadastrado");
     * return "user";
     * }
     * user.setId(id);
     * userService.save(user);
     * return "redirect:/user";
     * }
     */

    @PostMapping("/user/edit/{id}")
    public ResponseEntity<Map<String, String>> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        try {
            User user = userService.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            Map<String, String> response = new HashMap<>();

            if (updatedUser.getName() != null) {
                user.setName(updatedUser.getName());
            }

            boolean emailUpdated = false;
            if (updatedUser.getEmail() != null && !updatedUser.getEmail().equals(user.getEmail())) {
                user.setEmail(updatedUser.getEmail());
                emailUpdated = true;
            }

            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isBlank()) {
                if (!updatedUser.getPassword().equals(user.getPassword())) { // Verifica se a senha já está encriptada
                    user.setPassword(updatedUser.getPassword()); // Mantém a senha inalterada
                }
            }

            userService.save(user);

            response.put("message", "Dados atualizados com sucesso!");
            response.put("redirect", emailUpdated ? "/login" : "");

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("error", "Erro ao atualizar usuário"));
        }
    }

    @DeleteMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable Long id, Model model) {
        try {
            userService.deleteById(id);
        } catch (Exception e) {
            model.addAttribute("error",
                    "Não é possível excluir o usuário, pois ele está associado a outros registros.");
        }
        return "redirect:/login";
    }
}
