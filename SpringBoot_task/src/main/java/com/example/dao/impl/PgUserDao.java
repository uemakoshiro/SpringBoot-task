package com.example.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.dao.UserDao;
import com.example.entity.User;

@Repository
public class PgUserDao implements UserDao {

	private static final String SELECT_ID_PASS = "SELECT * FROM users WHERE login_id = :id AND password = :pass";
	
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    
    public User loginCheck(String id, String pass) {
    	String sql = SELECT_ID_PASS;
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("id", id);
        param.addValue("pass", pass);
        
        List<User> userInfo = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<User>(User.class));

        return userInfo.isEmpty() ? null : userInfo.get(0);
    
    }

}

