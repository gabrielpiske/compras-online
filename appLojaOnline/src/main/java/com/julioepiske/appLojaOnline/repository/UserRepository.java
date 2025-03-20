package com.julioepiske.appLojaOnline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.julioepiske.appLojaOnline.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
