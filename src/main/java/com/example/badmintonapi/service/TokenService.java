package com.example.badmintonapi.service;

import com.example.badmintonapi.domain.User;

public interface TokenService {
    public String initToken(String username, String password, boolean remember);

    public boolean verify(String token);

    public User getUser(String token);
}
