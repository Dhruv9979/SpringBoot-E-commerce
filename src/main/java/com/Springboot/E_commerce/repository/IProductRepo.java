package com.Springboot.E_commerce.repository;

import com.Springboot.E_commerce.model.Product;

import java.util.List;

public interface IProductRepo {
    List<Product> searchProduct(String keyword);
    List<Product> findAll();
    Product findById(int id);
    int save(Product product);
    void deleteById(int productId);
}
