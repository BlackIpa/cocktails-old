package com.cocktails.service;

import com.cocktails.dao.UserRepository;
import com.cocktails.entity.User;
import com.cocktails.entity.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {

        List<User> users = userRepository.findAll();

        return users;
    }

    @Override
    public User findById(Long theId) {
        System.out.println("We're in UserService findById method");

        User user = userRepository.getById(theId);

        return user;
    }

    @Override
    public void saveUser(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);
    }
}
