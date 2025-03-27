package com.julioepiske.appLojaOnline.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import org.springframework.stereotype.Service;

import com.julioepiske.appLojaOnline.model.Buy;
import com.julioepiske.appLojaOnline.model.Store;
import com.julioepiske.appLojaOnline.model.User;
import com.julioepiske.appLojaOnline.repository.BuyRepository;
import com.julioepiske.appLojaOnline.repository.StoreRepository;
import com.julioepiske.appLojaOnline.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BuyService {

    private final BuyRepository buyRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    public void createPurchase(List<Long> storeIds){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Usuário Não Encontrado"));

        List<Store> stores = storeRepository.findAllById(storeIds);

        //cria compras para as lojas selecionadas
        for(Store store : stores){
            Buy buy = new Buy();
            buy.setUser(user);
            buy.setStore(store);
            buy.setPurchaseDate(LocalDateTime.now());
            buyRepository.save(buy);
        }
    }
}
