package com.example.badmintonapi.controller;

import com.example.badmintonapi.domain.User;
import com.example.badmintonapi.service.TokenService;
import com.example.badmintonapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
    public boolean select(@RequestBody Map<String, String> params) {
        String username = params.get("username");
        String password = params.get("password");
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
