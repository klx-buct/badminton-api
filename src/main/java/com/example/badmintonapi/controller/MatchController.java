package com.example.badmintonapi.controller;

import com.example.badmintonapi.domain.Match;
import com.example.badmintonapi.domain.Response;
import com.example.badmintonapi.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/match")
public class MatchController {
    @Autowired
    MatchService matchService;

    @PostMapping("add")
    public Response insert(@RequestBody Match match) {
        boolean res = this.matchService.insertMatch(match);
        Response response = new Response();
        response.setCode(0);
        Map message = new HashMap();
        message.put("result", res);

        response.setMessage(message);

        return response;
    }
}
