package com.Springboot.E_commerce.repository;

import com.Springboot.E_commerce.helper.UserMapper;
import com.Springboot.E_commerce.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepo implements IUserRepo{
    private final JdbcTemplate template;

    public UserRepo(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM \"User\" WHERE email = ?";
        User user = template.queryForObject(sql, new Object[]{email}, new UserMapper());
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM \"User\"";
        return template.query(sql, new UserMapper());
    }

    @Override
    public User findById(int id) {
        String sql = "SELECT * FROM \"User\" WHERE id = ?";
        try {
            return template.queryForObject(sql, new Object[]{id}, new UserMapper());
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public int save(User user) {
        if(user.getId() == 0) {
            String sql = "INSERT INTO \"User\" (firstName, lastName, email, password)" +
                    "VALUES (?, ?, ?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            template.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getPassword());
                return ps;
            }, keyHolder);
            return keyHolder.getKey() != null ? keyHolder.getKey().intValue() : 0;
        } else {
            String sql = "UPDATE \"User\" SET firstName = ?, lastName = ?, email = ?, password = ? " +
                    "WHERE id = ?";

            return template.update(sql, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
        }
    }

    @Override
    public void deleteById(int userId) {
        String sql = "DELETE FROM \"User\" WHERE id = ?";
        template.update(sql, userId);
    }
}
