package com.julioepiske.appLojaOnline.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.julioepiske.appLojaOnline.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
