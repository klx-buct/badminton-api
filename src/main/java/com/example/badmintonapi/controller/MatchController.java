package com.example.badmintonapi.controller;

import com.example.badmintonapi.domain.Match;
import com.example.badmintonapi.domain.Response;
import com.example.badmintonapi.domain.Team;
import com.example.badmintonapi.domain.User;
import com.example.badmintonapi.service.MatchService;
import com.example.badmintonapi.service.TeamService;
import com.example.badmintonapi.service.UserService;
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

    @Autowired
    UserService userService;

    @Autowired
    TeamService teamService;

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

    @PostMapping("updateMatch")
    public Response updateMatch(@RequestBody Match match) {
        boolean res = this.matchService.updateMatch(match);
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

    @GetMapping("detail")
    public Response getMatchById(int id) {
        Response response = new Response();
        response.setCode(0);
        Map message = new HashMap();
        Match match = matchService.getMatchById(id);
        message.put("detail", match);
        response.setMessage(message);
        return response;
    }

    @PostMapping("joinTeamMatch")
    public Response joinTeamMatch(@RequestBody Map<String, String> params) {
        int id = Integer.parseInt(params.get("id"));
        String name = params.get("name");
        String userList = params.get("userList");
        String[] userNumber = userList.split("-");
        for(int i = 0; i < userNumber.length; i++) {
            User user = this.userService.getUserBySchoolNumber(userNumber[i]);
            //记录队长id
            //更新用户参与的比赛id字段
            if(user.getJoinMatch() == null) {
                this.userService.updateJoinMatch(id + "", user.getId());
            }else {
                this.userService.updateJoinMatch(user.getJoinMatch()+"-"+id, user.getId());
            }

        }
        //创建team
        Team team = new Team();
        team.setMatchId(id);
        team.setName(name);
        team.setPeople(userList);
        team.setCaption(Integer.parseInt(userNumber[0]));
        this.teamService.insertTeam(team);
        System.out.println(team.getId());
        //更新match的enterID
        Match match = this.matchService.getMatchById(id);
        if(match.getEnterId() == null) {
            this.matchService.updateMatchEnterid(team.getId()+"", id);
        }else {
            this.matchService.updateMatchEnterid(match.getEnterId()+"-"+team.getId(), id);
        }

        Response response = new Response();
        response.setCode(0);
        Map message = new HashMap();
        message.put("result", true);
        response.setMessage(message);

        return response;
    }
    @GetMapping("show")
    public Response showMatch() {
        Response response = new Response();
        response.setCode(0);
        Match[] matches = this.matchService.getMatchByStatus(0);
        Map message = new HashMap();
        message.put("matches", matches);
        response.setMessage(message);
        return response;
    }
}
