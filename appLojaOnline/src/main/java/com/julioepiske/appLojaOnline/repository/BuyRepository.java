package com.julioepiske.appLojaOnline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.julioepiske.appLojaOnline.model.Buy;
import com.julioepiske.appLojaOnline.model.User;

public interface BuyRepository extends JpaRepository<Buy, Long> {
    @Query("SELECT b FROM Buy b JOIN FETCH b.store JOIN FETCH b.user WHERE b.user = :user")
    List<Buy> findByUserWithDetails(@Param("user") User user);
}
