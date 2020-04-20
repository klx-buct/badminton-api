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
    public User select(User user) {
        return userMapper.select(user);
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

    @Override
    public User getUserBySchoolNumber(String schoolNumber) {
        return userMapper.getUserBySchoolNumber(schoolNumber);
    }

    @Override
    public boolean updateJoinMatch(String joinMatch, int id) {
        return userMapper.updateJoinMatch(joinMatch, id);
    }

    @Override
    public boolean insertDetail(User user) {
        int result = userMapper.insertDetail(user);
        return result == 1 ? true : false;
    }

    @Override
    public User getUserByUid(int uid) {
        return userMapper.getUserByUid(uid);
    }

    @Override
    public boolean updateRefereeMatch(String refereeMatch, int id) {
        int result = userMapper.updateRefereeMatch(refereeMatch, id);
        return result == 1 ? true : false;
    }

    @Override
    public boolean updateMessage(User user) {
        int res = userMapper.updateMessage(user);

        return res == 1 ? true : false;
    }


}
