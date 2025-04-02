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

        if (user == null) {
            throw new RuntimeException("Usuário não autenticado");
        }

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Loja não encontrada"));

        // cria compras para as lojas selecionadas
        Buy buy = new Buy();
        buy.setUser(user);
        buy.setStore(store);
        buy.setPurchaseDate(LocalDateTime.now());
        
        buyRepository.save(buy);
    }

    public List<Buy> getAllPurchases(){
        return buyRepository.findAll();
    }

    public Buy getPurchaseById(Long id){
        return buyRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Compra não encontrada"));
    }

    public Buy updatePurchase(Long id, Long storeId){
        Buy existingBuy = buyRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Compra não encontrada"));

        Store store = storeRepository.findById(storeId)
            .orElseThrow(() -> new RuntimeException("Loja não encontrada"));
        
        existingBuy.setStore(store);
        existingBuy.setPurchaseDate(LocalDateTime.now());

        return buyRepository.save(existingBuy);
    }

    public void deletePurchase(Long id){
        Buy buy = buyRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Compra não encontrada"));
        buyRepository.delete(buy);
    }

    public List<Buy> getPurchasesByUser(User user) {
        return buyRepository.findByUserWithDetails(user);
    }
}
