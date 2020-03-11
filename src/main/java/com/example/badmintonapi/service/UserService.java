package com.example.badmintonapi.service;

import com.example.badmintonapi.domain.User;

public interface UserService {
    public int add(User user);

    public User select(User user);

    public User[] getAllUser();

    public User[] getUsersByKeywords(String name);

    public User[] getUsersByMember(int member, String name);

    public User getUserBySchoolNumber(String schoolNumber);

    public boolean updateJoinMatch(String joinMatch, int id);

    public boolean insertDetail(User user);

    public User getUserByUid(int uid);
}
