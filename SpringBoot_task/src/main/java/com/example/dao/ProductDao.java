package com.example.dao;

import java.util.ArrayList;

import com.example.entity.Product;

public interface ProductDao {

	public ArrayList<Product> SelectAll();
	
	public Product SelectId(String id);

	public ArrayList<Product> Search(String keyword);
	
	public int Insert(Product pd);
	
	public int Update(Product pd);
}

