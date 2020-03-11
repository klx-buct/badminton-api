package com.example.badmintonapi.controller;

import com.example.badmintonapi.domain.Referee;
import com.example.badmintonapi.domain.Response;
import com.example.badmintonapi.domain.User;
import com.example.badmintonapi.service.RefereeService;
import com.example.badmintonapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/referee")
public class RefereeController {
    @Autowired
    RefereeService refereeService;
    @Autowired
    UserService userService;
    @PostMapping("insert")
    public Response insert(@RequestBody Map<String, String> params) {
        Response response = new Response();
        Map message = new HashMap();
        try {
            String time = params.get("time");
            String text = params.get("text");
            int uid = Integer.parseInt(params.get("uid"));
            User user = this.userService.getUserByUid(uid);
            String img = params.get("img");
            Referee referee = new Referee();
            referee.setImg(img);
            referee.setUid(uid);
            referee.setText(text);
            referee.setTime(time);
            referee.setStatus(0);
            referee.setUsername(user.getUsername());
            boolean result = this.refereeService.insert(referee);
            response.setCode(0);
            message.put("result", result);
            response.setMessage(message);
        }catch (Exception e) {
            response.setCode(-1);
            message.put("error", e);
            response.setMessage(message);
        }
        return response;
    }
    @GetMapping("list")
    public Response getList(int status) {
        Response response = new Response();
        Map message = new HashMap();
        try {
            response.setCode(0);
            Referee[] referees = refereeService.select(status);
            message.put("list", referees);
            response.setMessage(message);
        }catch (Exception e) {
            response.setCode(-1);
            message.put("error", e);
            response.setMessage(message);
        }

        return response;
    }
}
