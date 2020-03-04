package com.example.badmintonapi.controller;

import com.example.badmintonapi.domain.Response;
import com.example.badmintonapi.domain.User;
import com.example.badmintonapi.service.TokenService;
import com.example.badmintonapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/usfer")
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
    public Response select(@RequestBody Map<String, String> params) {
        Response response = new Response();
        Map message = new HashMap();

        String username = params.get("username");
        String password = params.get("password");
        boolean remember = Boolean.parseBoolean(params.get("remember"));
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        User user = new User();
        user.setUsername(username);
        user.setPassword(md5Password);

        boolean result = userService.select(user);
        if(result) {
            String token = tokenService.initToken(username, md5Password, remember);
            tokenService.getUser(token);
            message.put("token", token);
            message.put("result", true);
        }else {
            message.put("result", false);
        }


        response.setCode(0);
        response.setMessage(message);

        return response;
    }
}
