package com.Springboot.E_commerce.helper;

import com.Springboot.E_commerce.model.Product;
import com.Springboot.E_commerce.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setDesc(rs.getString("desc"));
        product.setBrand(rs.getString("brand"));
        product.setPrice(rs.getBigDecimal("price"));
        product.setCategory(rs.getString("category"));
        product.setReleaseDate(rs.getDate("releaseDate"));
        product.setAvailable(rs.getBoolean("available"));
        product.setQuantity(rs.getInt("quantity"));
        return product;
    }
}
