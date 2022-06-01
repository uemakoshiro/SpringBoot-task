package com.example.dao;

import com.example.entity.User;

public interface UserDao {

	public User loginCheck(String id, String pass);

}

