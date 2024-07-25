package com.Springboot.E_commerce.repository;

import com.Springboot.E_commerce.model.Product;
import com.Springboot.E_commerce.helper.ProductMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ProductRepo implements IProductRepo{

    private final JdbcTemplate template;

    public ProductRepo(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Product> searchProduct(String keyword) {
        String sql = "SELECT id, name, desc, brand, price, category, releaseDate, available, quantity " +
                "FROM Product " +
                "WHERE LOWER(name) LIKE LOWER(?) OR " +
                "LOWER(desc) LIKE LOWER(?) OR " +
                "LOWER(brand) LIKE LOWER(?) OR " +
                "LOWER(category) LIKE LOWER(?)";

        String keywordParam = "%" + keyword + "%";
        return template.query(sql, new Object[]{keywordParam, keywordParam, keywordParam, keywordParam}, new ProductMapper());
    }

    @Override
    public List<Product> findAll() {
        String sql = "SELECT * FROM Product";
        return template.query(sql, new ProductMapper());
    }

    @Override
    public Product findById(int id) {
        String sql = "SELECT * FROM Product WHERE id = ?";
        try {
            return template.queryForObject(sql, new Object[]{id}, new ProductMapper());
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public int save(Product product) {
        if(product.getId() == 0) {
            String sql = "INSERT INTO Product (name, desc, brand, price, category, releaseDate, available, quantity)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            template.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, product.getName());
                ps.setString(2, product.getDesc());
                ps.setString(3, product.getBrand());
                ps.setBigDecimal(4, product.getPrice());
                ps.setString(5, product.getCategory());
                ps.setDate(6, product.getReleaseDate());
                ps.setBoolean(7, product.isAvailable());
                ps.setInt(8, product.getQuantity());
                return ps;
            }, keyHolder);
            return keyHolder.getKey() != null ? keyHolder.getKey().intValue() : 0;
        } else {
            String sql = "UPDATE Product SET name = ?, desc = ?, brand = ?, price = ?, category = ?, releaseDate = ?, " +
                    "available = ?, quantity = ? WHERE id = ?";

            return template.update(sql,
                    product.getName(), product.getDesc(), product.getBrand(), product.getPrice(), product.getCategory(),
                    product.getReleaseDate(), product.isAvailable(), product.getQuantity(), product.getId());
        }
    }

    @Override
    public void deleteById(int productId) {
        String sql = "DELETE FROM Product WHERE id = ?";
        template.update(sql, productId);
    }
}
