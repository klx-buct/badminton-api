package com.example.badmintonapi.controller;

import com.example.badmintonapi.domain.Prize;
import com.example.badmintonapi.domain.Response;
import com.example.badmintonapi.service.PrizeService;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/prize")
public class PrizeController {
    @Autowired
    PrizeService prizeService;
    @PostMapping("insert")
    public Response insert(@RequestBody Map<String, String> params) {
        Response response = new Response();
        Map message = new HashMap();
        try {
            String name = params.get("prize");
            int grade = Integer.parseInt(params.get("grade"));
            String content = params.get("thing");
            int matchId = Integer.parseInt(params.get("matchId"));
            Prize prize = new Prize();
            prize.setMatchId(matchId);
            prize.setContent(content);
            prize.setGrade(grade);
            prize.setName(name);
            boolean res = prizeService.insert(prize);
            response.setCode(0);
            message.put("result", res);
            response.setMessage(message);
        }catch (Exception e) {
            message.put("error", e);
            response.setCode(-1);
            response.setMessage(message);
        }

        return response;
    }

    @GetMapping("list")
    public Prize[] select(int matchId) {
        return prizeService.select(matchId);
    }
}
