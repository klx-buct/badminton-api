package com.example.badmintonapi.controller;

import com.example.badmintonapi.domain.User;
import com.example.badmintonapi.service.TokenService;
import com.example.badmintonapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @GetMapping("add")
    public String add() {
        User user = new User();
        user.setPassword("kkk");
        user.setUsername("kkk");
        userService.add(user);

        return "add success";
    }

    @PostMapping("login")
    public boolean select(String username, String password) {
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        User user = new User();
        user.setUsername(username);
        user.setPassword(md5Password);
        boolean result = userService.select(user);
        if(result) {
            String token = tokenService.initToken(username, md5Password);
            tokenService.getUser(token);
        }else {

        }

        return result;
    }
}
