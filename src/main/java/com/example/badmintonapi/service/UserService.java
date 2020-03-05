package com.example.badmintonapi.service;

import com.example.badmintonapi.domain.User;

public interface UserService {
    public int add(User user);

    public boolean select(User user);

    public User[] getAllUser();

    public User[] getUsersByKeywords(String name);

    public User[] getUsersByMember(int member, String name);
}
