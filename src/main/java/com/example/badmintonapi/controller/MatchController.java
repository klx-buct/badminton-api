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

    @Autowired
    TeamDetailService teamDetailService;

    @Autowired
    MatchTypeService matchTypeService;

    @PostMapping("add")
    public Response insert(@RequestBody Match match) {
        matchService.insertMatch(match);
        Response response = new Response();
        response.setCode(0);
        Map message = new HashMap();
        message.put("result", match.getId());
        for (int i:
             match.getMatchType()) {
            switch (i) {
                case 0: matchTypeService.insert(new MatchType(0, "男单", 1, match.getId()));break;
                case 1: matchTypeService.insert(new MatchType(1, "女单", 1, match.getId()));break;
                case 2: matchTypeService.insert(new MatchType(2, "男双", 2, match.getId()));break;
                case 3: matchTypeService.insert(new MatchType(3, "女双", 2, match.getId()));break;
                case 4: matchTypeService.insert(new MatchType(4, "混双", 2, match.getId()));break;
                default:break;
            }
        }
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
        //创建team
        Team team = new Team();
        team.setMatchId(id);
        team.setName(name);
        team.setPeople(userList);
        team.setCaption(Integer.parseInt(userNumber[0]));
        this.teamService.insertTeam(team);
        Confrontation confrontation = new Confrontation();
        confrontation.setMatchId(id);
        confrontation.setTeamId(team.getId());
        confrontation.setEnd(0);
        confrontationService.insert(confrontation);
        for(int i = 0; i < userNumber.length; i++) {
            User user = this.userService.getUserBySchoolNumber(userNumber[i]);
            //记录队长id
            //更新用户参与的比赛id字段
            if(user.getJoinMatch() == null) {
                this.userService.updateJoinMatch(id + "", user.getId());
            }else {
                this.userService.updateJoinMatch(user.getJoinMatch()+"-"+id, user.getId());
            }
            TeamDetail teamDetail = new TeamDetail();
            teamDetail.setTeamId(team.getId());
            teamDetail.setUserId(user.getUid());
            teamDetail.setName(user.getName());
            teamDetailService.insert(teamDetail);
        }
        //更新match的enterID
        Match match = this.matchService.getMatchById(id);
        match.setActualPlayer(match.getActualPlayer()+userNumber.length);
        this.matchService.updateMatch(match);
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
                if(match.getStatus()!=-1)
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
                    MatchResult item = this.matchResultService.getItem(matchId, user.getUid() + "-" + user2.getUid(), user2.getUid() + "-" + user.getUid());
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

    @GetMapping("userReferee")
    public Response userReferee(int uid) {
        Response response = new Response();
        Map message = new HashMap();
        try {
            MatchResult[] userReferee = this.matchResultService.getUserReferee(uid);
            for (MatchResult matchResult:
                 userReferee) {
                Match matchById = this.matchService.getMatchById(matchResult.getMatchId());
                MatchRound matchRound = this.matchRoundService.getMatchRound(matchById.getStatus(), matchById.getId());
                matchResult.setMatchName(matchById.getName());
                matchResult.setRoundName(matchRound.getText());
            }
            response.setCode(0);
            message.put("matchResult", userReferee);
            response.setMessage(message);
        }catch (Exception e) {
            response.setCode(-1);
            message.put("error", e);
            response.setMessage(message);
        }

        return response;
    }

    @PostMapping("updateResult")
    public Response updateResult(@RequestBody Map<String, String> params) {
        int id = Integer.parseInt(params.get("id"));
        String grade = params.get("grade");
        Response response = new Response();
        Map message = new HashMap();
        try {
            MatchResult matchResult = new MatchResult();
            matchResult.setId(id);
            matchResult.setGrade(grade);
            boolean res = this.matchResultService.updateResult(matchResult);
            MatchResult resultById = this.matchResultService.getResultById(id);
            String contestant = resultById.getContestant();
            String[] uids = contestant.split("-");
            String[] grades = grade.split("-");
            Confrontation confrontation;
            if(Integer.parseInt(grades[0]) < Integer.parseInt(grades[1])) {
                confrontation = this.confrontationService.userMatch(resultById.getMatchId(), Integer.parseInt(uids[0]));
            }else {
                confrontation = this.confrontationService.userMatch(resultById.getMatchId(), Integer.parseInt(uids[1]));
            }
            this.confrontationService.updateEnd(resultById.getRound(), confrontation.getId());
            message.put("result", res);
            response.setCode(0);
            response.setMessage(message);
        }catch (Exception e) {
            response.setCode(-1);
            message.put("error", e);
            response.setMessage(message);
        }

        return response;
    }

    @GetMapping("round")
    public Response getRound(int matchId) {
        Response response = new Response();
        Map message = new HashMap();
        try {
            MatchRound[] round = this.matchRoundService.getRound(matchId);
            response.setCode(0);
            message.put("round", round);
            response.setMessage(message);
        }catch (Exception e) {
            response.setCode(-1);
            message.put("error", e);
            response.setMessage(message);
        }

        return response;
    }

    @GetMapping("users")
    public Response getUsersByRound(int matchId, int roundType) {
        Response response = new Response();
        Map message = new HashMap();
        try {
            Match matchById = this.matchService.getMatchById(matchId);
            List userList = new ArrayList<>();
            if(matchById.getIsTeamUp() == 0) { //不允许组队
                Confrontation[] list = this.confrontationService.getList(roundType, matchId);
                for (Confrontation confrontation:
                     list) {
                    User user = this.userService.getUserByUid(confrontation.getTeamId());
                    Map map = new HashMap();
                    map.put("id", user.getUid());
                    map.put("teamName", user.getUsername());
                    map.put("realName", user.getName());
                    userList.add(map);
                }
                message.put("user", userList);
                response.setMessage(message);
                response.setCode(0);
            }else {

            }
        }catch (Exception e) {
            response.setCode(-1);
            message.put("error", e);
            response.setMessage(message);
        }

        return response;
    }

    @GetMapping("prizeList")
    public Response prizeList() {
        Response response = new Response();
        Map message = new HashMap();
        try {
            Match[] needPrize = this.matchService.getNeedPrize(0);
            message.put("prizeList", needPrize);
            response.setCode(0);
            response.setMessage(message);
        }catch (Exception e) {
            response.setCode(-1);
            message.put("error", e);
            response.setMessage(message);
        }

        return response;
    }

    @PostMapping("end")
    public Response end(@RequestBody Map<String, String> params) {
        Response response = new Response();
        Map message = new HashMap();
        int matchId = Integer.parseInt(params.get("matchId"));
        try {
            Match matchById = this.matchService.getMatchById(matchId);
            int nowRound = matchById.getStatus()+1;
            matchById.setStatus(-1);
            this.matchService.updateMatch(matchById);
            Confrontation[] list = this.confrontationService.getList(0, matchId);
            for (Confrontation confrontation:
                 list) {
                this.confrontationService.updateEnd(nowRound, confrontation.getId());
            }
            MatchRound matchRound = new MatchRound();
            matchRound.setRound(nowRound);
            matchRound.setMatchId(matchId);
            matchRound.setText("最终胜利者");
            this.matchRoundService.insert(matchRound);
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

    @GetMapping("against")
    public Response against(int matchId, int uid) {
        Response response = new Response();
        Map message = new HashMap();
        Match match = this.matchService.getMatchById(matchId);
        User user = this.userService.getUserByUid(uid);
        Team team = this.teamService.getTeam(matchId, user.getSchoolNumber());
        if(team == null) {
            message.put("result", false);
            response.setCode(0);
            response.setMessage(message);
        }else {
            TeamDetail[] teamDetails = this.teamDetailService.getTeamDetail(team.getId());
            int round = match.getStatus();
            boolean judge = true;
            for (TeamDetail teamDetail:
                 teamDetails) {
                if(teamDetail.getType()==null && round == 0) {
                    continue;
                }
                String[] split = teamDetail.getType().split(",");
                if(split.length != round) {
                    judge = false;

                }
            }
            if(judge) {
                message.put("result", true);
                message.put("teamId", team.getId());
                response.setCode(0);
                response.setMessage(message);
            }else {
                message.put("result", false);
                response.setCode(0);
                response.setMessage(message);
            }
        }

        return response;
    }

    @GetMapping("peopleList")
    public Response getPeopleList(int teamId) {
        Team teamById = this.teamService.getTeamById(teamId);
        String[] split = teamById.getPeople().split("-");
        List<User> users = new ArrayList<>();
        for (String schoolNumber:
             split) {
            users.add(this.userService.getUserBySchoolNumber(schoolNumber));
        }

        Response response = new Response();
        Map message = new HashMap();
        response.setCode(0);
        message.put("userList", users);
        response.setMessage(message);

        return response;
    }
    
    @GetMapping("getMatchType")
    public Response getMatchType(int matchId) {
        MatchType[] matchTypes = matchTypeService.get(matchId);
        Response response = new Response();
        Map message = new HashMap();
        response.setCode(0);
        message.put("matchType", matchTypes);
        response.setMessage(message);
        return response;
    }

    @PostMapping("teamAgainst")
    public Response teamAgainst(@RequestBody Map<String, String> params) {
        int type = Integer.parseInt(params.get("type"));
        String user = params.get("user");
        int teamId = Integer.parseInt(params.get("teamId"));
        Team teamById = teamService.getTeamById(teamId);
        Match matchById = matchService.getMatchById(teamById.getMatchId());
        int round = matchById.getStatus();
        String[] split = user.split("-");
        for (String userId:
             split) {
            int uid = Integer.parseInt(userId);
            TeamDetail select = teamDetailService.select(teamId, uid);
            if(select.getType() == null) {
                teamDetailService.update(teamId, uid, (round+1)+"-"+type);
            }else {
                teamDetailService.update(teamId, uid, select.getType()+","+(round+1)+"-"+type);
            }
        }

        Response response = new Response();
        Map message = new HashMap();
        response.setCode(0);
        message.put("result", true);
        response.setMessage(message);

        return response;
    }
}
