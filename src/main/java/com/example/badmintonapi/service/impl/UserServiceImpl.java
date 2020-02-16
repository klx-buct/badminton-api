package com.example.badmintonapi.service.impl;

import com.example.badmintonapi.domain.User;
import com.example.badmintonapi.mapper.UserMapper;
import com.example.badmintonapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int add(User user) {
        userMapper.insert(user);
        int id = user.getId();
        return id;
    }
}
