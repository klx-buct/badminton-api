package com.example.badmintonapi.controller;

import com.example.badmintonapi.domain.*;
import com.example.badmintonapi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("api/match")
public class MatchController {
    @Autowired
    MatchService matchService;

    @Autowired
    UserService userService;

    @Autowired
    TeamService teamService;

    @Autowired
    ConfrontationService confrontationService;

    @Autowired
    MatchResultService matchResultService;

    @Autowired
    MatchRoundService matchRoundService;

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
        Match[] matches = this.matchService.getMatchByStatus(-1);
        Map message = new HashMap();
        message.put("matches", matches);
        response.setMessage(message);
        return response;
    }
    @PostMapping("joinMatch")
    public Response joinMatch(@RequestBody Map<String, String> params) {
        int matchId = Integer.parseInt(params.get("id"));
        String schoolNumber = params.get("schoolNumber");
        Response response = new Response();
        Map message = new HashMap();
        try {
            User user = this.userService.getUserBySchoolNumber(schoolNumber);
            Match match = this.matchService.getMatchById(matchId);
            if(user.getJoinMatch() == null) {
                this.userService.updateJoinMatch(match.getId() + "", user.getId());
            }else {
                this.userService.updateJoinMatch(user.getJoinMatch()+"-"+match.getId(), user.getId());
            }
            match.setActualPlayer(match.getActualPlayer()+1);
            System.out.println(match.getActualPlayer());
            this.matchService.updateMatch(match);
            if(match.getEnterId() == null) {
                this.matchService.updateMatchEnterid(user.getUid()+"", matchId);
            }else {
                this.matchService.updateMatchEnterid(match.getEnterId()+"-"+user.getUid(), matchId);
            }
            Confrontation confrontation = new Confrontation();
            confrontation.setMatchId(matchId);
            confrontation.setTeamId(user.getUid());
            confrontation.setEnd(0);
            this.confrontationService.insert(confrontation);
            response.setCode(0);
            message.put("result", true);
            response.setMessage(message);
        }catch (Exception ex) {
            response.setCode(-1);
            message.put("error", ex);
            response.setMessage(message);
        }
        return response;
    }

    @PostMapping("refereeMatch")
    public Response refereeMatch(@RequestBody Map<String, String> params) {
        String schoolNumber = params.get("schoolNumber");
        int matchId = Integer.parseInt(params.get("id"));
        Response response = new Response();
        Map message = new HashMap();
        try {
           User user = this.userService.getUserBySchoolNumber(schoolNumber);
           Match match = this.matchService.getMatchById(matchId);
           if(user.getRefereeMatch() == null) {
               this.userService.updateRefereeMatch(match.getId()+"", user.getId());
           }else {
               this.userService.updateRefereeMatch(user.getRefereeMatch()+"-"+match.getId(), user.getId());
           }
           match.setActualReferee(match.getActualReferee()+1);
           this.matchService.updateMatch(match);
           if(match.getRefereeId() == null){
               this.matchService.updateMatchRefereeId(user.getId()+"", matchId);
           }else {
               this.matchService.updateMatchRefereeId(match.getRefereeId()+"-"+user.getId(), matchId);
           }
           response.setCode(0);
           message.put("result", true);
           response.setMessage(message);
        }catch (Exception e) {
            response.setCode(-1);
            message.put("error", e);
            response.setMessage(message);
        }

        return response;
    }

    @GetMapping("userMatch")
    public Response userMatch(int uid) {
        Response response = new Response();
        Map message = new HashMap();
        try {
            List<Match> matches = new ArrayList<>();
            User user = this.userService.getUserByUid(uid);
            String joinMatch = user.getJoinMatch();
            String[] matchList = joinMatch.split("-");
            for (String matchId:
                 matchList) {
                Match match = this.matchService.getMatchById(Integer.parseInt(matchId));
                matches.add(match);
            }
            message.put("matchList", matches);
            response.setCode(0);
            response.setMessage(message);
        }catch (Exception e) {
            response.setCode(-1);
            message.put("error", e);
            response.setMessage(message);
        }

        return response;
    }

    @GetMapping("arrange")
    public Response arrange(int uid, int matchId) {
        Response response = new Response();
        Map message = new HashMap();
        try{
            Match match = this.matchService.getMatchById(matchId);
            if(match.getIsTeamUp() == 0) { //不允许组队
                Confrontation confrontation = this.confrontationService.userMatch(matchId, uid);
                String[] matchList = confrontation.getMatch().split(",");
                String nowMatch = matchList[matchList.length-1];
                if(nowMatch.contains("0")) { //轮空了
                    message.put("null", true);
                }else {
                    User user = this.userService.getUserByUid(uid);
                    Confrontation opponentConfrontation = this.confrontationService.findOpponent(matchId, nowMatch, uid);
                    User user2 = this.userService.getUserByUid(opponentConfrontation.getTeamId());
                    MatchRound matchRound = this.matchRoundService.getMatchRound(match.getStatus(), matchId);
                    message.put("roundName", matchRound.getText());
                    message.put("null", false);
                    message.put("matchName", match.getName());
                    message.put("team1", user.getUsername());
                    message.put("team2", user2.getUsername());
                    MatchResult item = this.matchResultService.getItem(matchId, user.getUid() + "-" + user2.getUid());
                    message.put("address", item.getAddress());
                    message.put("referee", item.getRefereeName());
                    response.setMessage(message);
                }
            }else { //允许组队

            }
        }catch (Exception e) {
            response.setCode(-1);
            message.put("error", e);
            response.setMessage(message);
        }
        return response;
    }
}
