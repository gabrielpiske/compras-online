package com.julioepiske.appLojaOnline.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.julioepiske.appLojaOnline.model.Store;
import com.julioepiske.appLojaOnline.repository.StoreRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public List<Store> findAll() {
        return storeRepository.findAll();
    }

    public Store save(Store store) {
        return storeRepository.save(store);
    }

    public Store findById(Long id) {
        return storeRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        storeRepository.deleteById(id);
    }
}
