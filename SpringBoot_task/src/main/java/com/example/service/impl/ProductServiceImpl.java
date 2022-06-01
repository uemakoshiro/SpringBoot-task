package com.example.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.ProductDao;
import com.example.entity.Product;
import com.example.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    public ArrayList<Product> SelectAll() {
        return productDao.SelectAll();
    }
    
    public Product SelectId(String id) {
    	Product pd = productDao.SelectId(id);
    	System.out.println("service"+pd.getName());
    	return pd;
    }
    
    public ArrayList<Product> Search(String keyword){
    	return productDao.Search(keyword);
    }
    
    public int Insert(Product pd) {
    	return productDao.Insert(pd);
    }
    
    public int Update(Product pd) {
    	return productDao.Update(pd);
    }
    
}

