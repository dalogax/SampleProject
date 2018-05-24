package com.dalogax.sample.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.dalogax.sample.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserRepository
{
    @Autowired
    private JdbcTemplate jdbcTemplate;
 
    @Transactional(readOnly=true)
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT ID, NAME, EMAIL FROM USER", 
                new UserRowMapper());
    }
 
    @Transactional(readOnly=true)
    public User findUserById(int id) {
        return jdbcTemplate.queryForObject(
            "SELECT ID, NAME, EMAIL FROM USER WHERE ID=?",
            new Object[]{id}, new UserRowMapper());
    }
 
    public User create(final User user) 
    {
        final String sql = "INSERT INTO USER(NAME,EMAIL) VALUES(?,?)";
 
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getName());
                ps.setString(2, user.getEmail());
                return ps;
            }
        }, holder);
 
        int newUserId = holder.getKey().intValue();
        user.setId(newUserId);
        return user;
    }
}
 
class UserRowMapper implements RowMapper<User>
{
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("ID"));
        user.setName(rs.getString("NAME"));
        user.setEmail(rs.getString("EMAIL"));
        return user;
    }
}