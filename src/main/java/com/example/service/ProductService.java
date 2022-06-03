package com.example.service;

import java.util.ArrayList;

import com.example.entity.Product;

public interface ProductService {

    public ArrayList<Product> SelectAll();
    
    public Product SelectId(String id);
    
    public ArrayList<Product> Search(String keyword, String order);
    
    public int Insert(Product pd);
    
    public int Update(Product pd);
    
    public int Delete(String id);
}
