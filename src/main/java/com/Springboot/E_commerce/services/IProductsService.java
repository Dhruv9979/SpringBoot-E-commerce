package com.Springboot.E_commerce.services;

import com.Springboot.E_commerce.model.Product;

import java.util.List;

public interface IProductsService {
    List<Product> getAllProducts();

    Product getProductById(int id);

    int addProduct(Product product);

    int updateProduct(int productId, Product product);

    void deleteProduct(int productId);

    List<Product> searchProducts(String keyword);
}
