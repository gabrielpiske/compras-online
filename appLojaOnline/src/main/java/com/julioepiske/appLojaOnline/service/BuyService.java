package com.julioepiske.appLojaOnline.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.julioepiske.appLojaOnline.model.Buy;
import com.julioepiske.appLojaOnline.model.Store;
import com.julioepiske.appLojaOnline.model.User;
import com.julioepiske.appLojaOnline.repository.BuyRepository;
import com.julioepiske.appLojaOnline.repository.StoreRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BuyService {

    private final BuyRepository buyRepository;
    private final AuthService authService;
    private final StoreRepository storeRepository;

    public void createPurchase(Long storeId) {
        User user = authService.getAuthenticatedUser();

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Loja não encontrada"));

        // cria compras para as lojas selecionadas
        Buy buy = new Buy();
        buy.setUser(user);
        buy.setStore(store);
        buy.setPurchaseDate(LocalDateTime.now());
        
        buyRepository.save(buy);
    }
}
