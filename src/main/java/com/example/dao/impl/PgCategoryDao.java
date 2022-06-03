package com.example.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.dao.CategoryDao;
import com.example.entity.Category;



@Repository
public class PgCategoryDao implements CategoryDao {

	private static final String SELECT_ALL = "SELECT * FROM categories ORDER BY id";
	
    @Autowired
    private JdbcTemplate jdbcTemplate;

    
    public ArrayList<Category> SelectAll() {
    	String sql = SELECT_ALL;
    	
        List<Category> categoryList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Category>(Category.class));

        return categoryList.isEmpty() ? null : (ArrayList<Category>) categoryList;
    
    }

}

