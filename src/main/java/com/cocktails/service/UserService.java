package com.cocktails.service;

import com.cocktails.entity.User;

import java.util.List;

public interface UserService {

    public List<User> findAll();

    public User findById(Long theId);

    public void saveUser(User user);
}
