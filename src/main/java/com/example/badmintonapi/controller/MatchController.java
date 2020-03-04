package com.example.badmintonapi.controller;

import com.example.badmintonapi.domain.Match;
import com.example.badmintonapi.domain.Response;
import com.example.badmintonapi.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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

    @GetMapping("list")
    public Response getMatch(int status, int pageSize, int pageIndex, String keywords) {
        Match[] match;
        if(keywords.equals("")) {
            match = matchService.getMatchByStatus(status);
        }else {
            match = matchService.getMatchByKeywords(keywords);
            System.out.println(match);
        }
        Response response = new Response();
        response.setCode(0);
        Map message = new HashMap();
        int total = match.length;
        message.put("matchList", Arrays.copyOfRange(match, pageSize*(pageIndex-1), pageSize*(pageIndex-1) + pageSize > total ? total : pageSize*(pageIndex-1) + pageSize));
        message.put("total", total);
        response.setMessage(message);
        return response;
    }
}
