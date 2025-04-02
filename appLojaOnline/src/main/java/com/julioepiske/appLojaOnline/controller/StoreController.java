package com.julioepiske.appLojaOnline.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.julioepiske.appLojaOnline.model.Store;
import com.julioepiske.appLojaOnline.service.StoreService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/store")
    public String getStorePage(Model model) {
        List<Store> stores = storeService.findAll();
        model.addAttribute("stores", stores);
        return "store";
    }

    @PostMapping("/store")
    public String addStore(@ModelAttribute Store store) {
        // TODO: process POST request
        storeService.save(store);
        return "redirect:/store";
    }

    @DeleteMapping("/store/{id}")
    public String deleteStore(@PathVariable Long id, Model model) {
        try{
            storeService.deleteById(id);
        } catch(Exception e){
            model.addAttribute("error","Não é possivel excluir a loja, pois esta associada a outros registros.");
        }
        
        return "redirect:/store";
    }

    @PostMapping("/store/update")
    public String updateStore(@ModelAttribute Store store) {
        storeService.save(store);
        return "redirect:/store";
    }

}
