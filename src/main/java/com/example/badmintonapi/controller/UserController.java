package com.example.badmintonapi.controller;

import com.example.badmintonapi.domain.Response;
import com.example.badmintonapi.domain.User;
import com.example.badmintonapi.service.TokenService;
import com.example.badmintonapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("register")
    public boolean add(@RequestBody User user) {
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        int userid = userService.add(user);
        user.setUid(userid);
        return this.userService.insertDetail(user);
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

        User res = userService.select(user);
        if(res != null) {
            String token = tokenService.initToken(username, md5Password, remember);
            tokenService.getUser(token);
            message.put("token", token);
            message.put("result", true);
            message.put("uid", res.getId());
        }else {
            message.put("result", false);
        }


        response.setCode(0);
        response.setMessage(message);

        return response;
    }

    @GetMapping("list")
    public Response getAllUser() {
        Response response = new Response();
        User[] users = this.userService.getAllUser();
        response.setCode(0);
        Map message = new HashMap();
        message.put("total", users.length);
        int refereeNum = 0;
        int memberNum = 0;
        for(int i = 0, len = users.length; i < len; i++) {
            User user = users[i];
            if(user.getReferee() == 1) {
                refereeNum++;
            }
            if(user.getMember() == 1) {
                memberNum++;
            }
        }
        message.put("refereeNum", refereeNum);
        message.put("memberNum", memberNum);
        response.setMessage(message);
        return response;
    }

    @GetMapping("page")
    public Response getPageUser(int member, String keywords, int pageIndex, int pageSize) {
        Response response = new Response();
        User[] users;
        if(member == 2) {
            users = userService.getUsersByKeywords(keywords);
        }else {
            users = userService.getUsersByMember(member, keywords);
        }
        int total = users.length;
        Map message = new HashMap();
        message.put("total", total);
        message.put("userList",Arrays.copyOfRange(users, pageSize*(pageIndex-1), pageSize*(pageIndex-1) + pageSize > total ? total : pageSize*(pageIndex-1) + pageSize));
        response.setMessage(message);
        return response;
    }

    @GetMapping("detail")
    public Response getUserByUid(int uid) {
        Response response = new Response();
        Map message = new HashMap();
        try {
            User user = userService.getUserByUid(uid);
            response.setCode(0);
            message.put("detail", user);
            response.setMessage(message);
        }catch (Exception e) {
            response.setCode(-1);
            response.setMessage(message);
        }

        return response;
    }
}
