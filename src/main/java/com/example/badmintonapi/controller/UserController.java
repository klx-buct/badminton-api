package com.example.badmintonapi.controller;

import com.example.badmintonapi.domain.User;
import com.example.badmintonapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("add")
    public String add() {
        User user = new User();
        user.setPassword("kkk");
        user.setUsername("kkk");
        userService.add(user);

        return "add success";
    }

    @GetMapping("select")
    public String select() {
        User user = new User();
        user.setUsername("kkk");
        user.setPassword("kkk");
        if(userService.select(user)) {
            return "success";
        }else {
            return "false";
        }
    }
}
