package com.julioepiske.appLojaOnline.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.julioepiske.appLojaOnline.model.Buy;
import com.julioepiske.appLojaOnline.model.Store;
import com.julioepiske.appLojaOnline.model.User;
import com.julioepiske.appLojaOnline.service.AuthService;
import com.julioepiske.appLojaOnline.service.BuyService;
import com.julioepiske.appLojaOnline.service.StoreService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Controller
public class BuyController {

    private final BuyService buyService;
    private final StoreService storeService;
    private final AuthService authService;

    @GetMapping("/buy")
    public String getBuyPage(Model model) {
        User user = authService.getAuthenticatedUser();

        List<Store> stores = storeService.findAll();
        List<Buy> buys = buyService.getPurchasesByUser(user);

        // logs
        System.out.println("Usuário autenticado: " + user.getName());
        System.out.println("Compras encontradas: " + buys.size());

        model.addAttribute("stores", stores);
        model.addAttribute("user", user);
        model.addAttribute("buys", buys);
        return "buy";
    }

    @PostMapping("/buy")
    public String createPurchase(@RequestParam(name = "storeId", required = true) Long storeId) {
        buyService.createPurchase(storeId);

        return "redirect:/buy";
    }

    @PostMapping("/buy/update")
    public String updatePurchase(@RequestParam Long buyId, @RequestParam Long storeId) {
        buyService.updatePurchase(buyId, storeId);
        return "redirect:/buy";
    }

    @DeleteMapping("/buy/{id}")
    public String deleteBuy(@PathVariable Long id) {
        buyService.deletePurchase(id);
        return "redirect:/buy";
    }

}
