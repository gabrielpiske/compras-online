package com.julioepiske.appLojaOnline.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class StoreController {

    @GetMapping("/store")
    public String getStorePage() {
        return "store";
    }
}
