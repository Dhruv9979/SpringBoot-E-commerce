package com.Springboot.E_commerce.services;

import com.Springboot.E_commerce.model.Product;
import com.Springboot.E_commerce.repository.IProductRepo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductsService implements IProductsService {

    IProductRepo repo;
    public ProductsService(IProductRepo repo) {
        this.repo = repo;
    }
    @Override
    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    @Override
    public Product getProductById(int id) {
        return repo.findById(id);
    }

    @Override
    public int addProduct(Product product) {
        return repo.save(product);
    }

    @Override
    public int updateProduct(int productId, Product product) {
        product.setId(productId);
        return repo.save(product);
    }

    @Override
    public void deleteProduct(int productId) {
        repo.deleteById(productId);
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        return repo.searchProduct(keyword);
    }
}
