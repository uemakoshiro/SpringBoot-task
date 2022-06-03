package com.example.dao.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.dao.ProductDao;
import com.example.entity.Product;

@Repository
public class PgProductDao implements ProductDao {

	private static final String SELECT_ALL = "SELECT p.id, product_id, category_id, p.name, price, image_path, description, p.created_at, p.updated_at, c.name AS category FROM products p JOIN categories c ON category_id = c.id ORDER BY product_id";
	private static final String SELECT_ID = "SELECT p.id, p.product_id, p.name AS name, p.price, c.name category, description, p.category_id, p.image_path FROM products p JOIN categories c ON p.category_id = c.id WHERE product_id = :id";
	private static final String SEARCH = "SELECT p.product_id, p.name AS name, p.price, c.name category FROM products p JOIN categories c ON p.category_id = c.id WHERE p.name LIKE :productName OR c.name LIKE :categoryName ";
	private static final String INSERT = "INSERT INTO products(product_id, category_id, name, price, image_path, description, created_at, updated_at) VALUES(:pId, :cId, :name, :price, :path, :description, current_timestamp, current_timestamp)";
	private static final String UPDATE = "UPDATE products SET product_id = :pId, category_id = :cId, price = :price, name = :name, image_path = :img, description = :description, updated_at = current_timestamp WHERE id = :id";
	private static final String DELETE = "DELETE FROM products WHERE product_id = :id";
	
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    
    public ArrayList<Product> SelectAll() {
    	String sql = SELECT_ALL;
        
        ArrayList<Product> productList = (ArrayList<Product>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<Product>(Product.class));

        return productList.isEmpty() ? null : productList;
    
    }
    
    public Product SelectId(String id) {
    	String sql = SELECT_ID;
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("id", Integer.parseInt(id));
    	ArrayList<Product> product = (ArrayList<Product>) jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Product>(Product.class));
    	return product.isEmpty() ? null : product.get(0);
    }
    
    public ArrayList<Product> Search(String keyword,String order) {
    	String sql = SEARCH;
    	if("product_id".equals(order) || "c.name".equals(order) || "price".equals(order) || "price DESC".equals(order) || "p.created_at".equals(order) || "p.created_at DESC".equals(order)) {
    		sql = sql + "ORDER BY " + order;
    	}
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("productName", "%"+keyword+"%");
    	param.addValue("categoryName", "%"+keyword+"%");
    	ArrayList<Product> productList = (ArrayList<Product>) jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Product>(Product.class));

        return productList.isEmpty() ? null : productList;
    }
    
    public int Insert(Product pd) {
    	String sql = INSERT;
    	int result = 0;
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("pId",Integer.parseInt(pd.getProductId()));
    	param.addValue("cId",Integer.parseInt(pd.getCategoryId()));
    	param.addValue("name", pd.getName());
    	param.addValue("price", Integer.parseInt(pd.getPrice()));
    	param.addValue("path", pd.getImagePath());
    	param.addValue("description", pd.getDescription());
    	try {
    		result = jdbcTemplate.update(sql, param);
    	}catch(RuntimeException e){
    		e.printStackTrace();
    	}
    	return result;
    }
    
    public int Update(Product pd) {
    	String sql = UPDATE;
    	int result = 0;
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("pId", Integer.parseInt(pd.getProductId()));
    	param.addValue("cId", Integer.parseInt(pd.getCategoryId()));
    	param.addValue("price", Integer.parseInt(pd.getPrice()));
    	param.addValue("name", pd.getName());
    	param.addValue("img", pd.getImagePath());
    	param.addValue("description", pd.getDescription());
    	param.addValue("id", Integer.parseInt(pd.getId()));
    	try {
    		result = jdbcTemplate.update(sql, param);
    	}catch(RuntimeException e){
    		e.printStackTrace();
    	}
    	return result;
    }
    
    public int Delete(String id) {
    	String sql = DELETE;
    	int result = 0;
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("id", Integer.parseInt(id));
    	try {
    		result = jdbcTemplate.update(sql, param);
    	}catch(RuntimeException e){
    		e.printStackTrace();
    	}
    	return result;
    }

}

