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

    @Override
    public boolean select(User user) {
        User u = userMapper.select(user);
        if(u == null) {
            return false;
        }else {
            return true;
        }
    }

    @Override
    public User[] getAllUser() {
        return userMapper.getAllUser();
    }

    @Override
    public User[] getUsersByKeywords(String name) {
        return userMapper.getUsersByKeywords(name);
    }

    @Override
    public User[] getUsersByMember(int member, String name) {
        return userMapper.getUsersByMember(member, name);
    }


}
