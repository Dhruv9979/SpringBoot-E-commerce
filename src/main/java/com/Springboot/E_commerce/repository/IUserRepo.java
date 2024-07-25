package com.Springboot.E_commerce.repository;

import com.Springboot.E_commerce.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepo {
    Optional<User> findByEmail(String email);
    List<User> findAll();
    User findById(int id);
    int save(User product);
    void deleteById(int productId);
}
