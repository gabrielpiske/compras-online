package com.julioepiske.appLojaOnline.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.julioepiske.appLojaOnline.model.Store;
import com.julioepiske.appLojaOnline.model.User;
import com.julioepiske.appLojaOnline.repository.UserRepository;
import com.julioepiske.appLojaOnline.service.BuyService;
import com.julioepiske.appLojaOnline.service.StoreService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Controller
public class BuyController {

    private final BuyService buyService;
    private final StoreService storeService;
    private final UserRepository userRepository;

    @GetMapping("/buy")
    public String getBuyPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        List<Store> stores = storeService.findAll();

        model.addAttribute("stores", stores);
        model.addAttribute("user", user);
        return "buy";
    }

    @PostMapping("/buy")
    public String createPurchase(@RequestParam List<Long> storeIds) {
        // TODO: process POST request
        buyService.createPurchase(storeIds);

        return "redirect:/buy";
    }

}
