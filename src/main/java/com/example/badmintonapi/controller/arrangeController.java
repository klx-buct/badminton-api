package com.example.badmintonapi.controller;

import com.example.badmintonapi.domain.*;
import com.example.badmintonapi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("api/arrange")
public class arrangeController {
    @Autowired
    MatchService matchService;

    @Autowired
    ConfrontationService confrontationService;

    @Autowired
    MatchResultService matchResultService;

    @Autowired
    UserService userService;

    @Autowired
    MatchRoundService matchRoundService;

    @Autowired
    TeamService teamService;

    @Autowired
    MatchTypeService matchTypeService;

    @Autowired
    TeamDetailService teamDetailService;

    @GetMapping("singleList")
    List<Arrange> getList() {
        Match[] matches = matchService.getIngMatch();
        List<Arrange> arranges = new ArrayList<Arrange>();
        for (int i = 0,len = matches.length; i < len; i++) {
            if(matches[i].getIsTeamUp()==1) {
                continue;
            }
            int id = matches[i].getId();
            Confrontation[] confrontations = confrontationService.getList(0,  id);
            boolean judge = true;
            if(matches[i].getStatus() != 0) {
                for (Confrontation confrontation:
                        confrontations) {
                    int teamId = confrontation.getTeamId();
                    MatchResult matchResult = matchResultService.getMatchResult(teamId + "", matches[i].getStatus(), matches[i].getId());
                    if(matchResult != null && matchResult.getGrade() == null) {
                        judge = false;
                    }
                }
            }
            Arrange arrange = new Arrange();
            arrange.setId(i);
            arrange.setName(matches[i].getName());
            arrange.setAllNum(matches[i].getPlayer());
            arrange.setNowNum(confrontations.length);
            arrange.setStatus(judge ? 1 : 0);
            arrange.setRound(matches[i].getStatus());
            arrange.setMatchId(matches[i].getId());
            arranges.add(arrange);
        }

        return arranges;
    }

    @GetMapping("teamList")
    List<Arrange> teamList() {
        Match[] matches = matchService.getIngMatch();
        List<Arrange> arranges = new ArrayList<Arrange>();
        for (int i = 0,len = matches.length; i < len; i++) {
            if(matches[i].getIsTeamUp()==0) {
                continue;
            }
            int id = matches[i].getId();
            Confrontation[] confrontations = confrontationService.getList(0,  id);
            boolean judge = true;
            MatchResult[] matchResults = matchResultService.getMatchResultByMatchId(id);
            for (MatchResult matchResult:
                 matchResults) {
                if(matchResult.getGrade() == null) {
                    judge = false;
                }
            }
            Arrange arrange = new Arrange();
            arrange.setId(i);
            arrange.setName(matches[i].getName());
            arrange.setAllNum(matches[i].getPlayer());
            arrange.setNowNum(confrontations.length);
            arrange.setStatus(judge ? 1 : 0);
            arrange.setRound(matches[i].getStatus());
            arrange.setMatchId(matches[i].getId());
            arranges.add(arrange);
        }
        return arranges;
    }

    @GetMapping("confrontation")
    MatchResult[] getListByMatchId(int matchId) {
        return this.matchResultService.getMatchResultByMatchId(matchId);
    }

    @GetMapping("auto")
    Response auto(int matchId, String address, int sort, String roundName) {
        Response response = new Response();
        Map message = new HashMap<>();
//        try {
            //sort积分排序or随机排序
            Match match = this.matchService.getMatchById(matchId);
            match.setStatus(match.getStatus()+1);
            int round = match.getStatus();
            //添加轮数描述
            MatchRound matchRound = new MatchRound();
            matchRound.setMatchId(matchId);
            matchRound.setRound(round);
            matchRound.setText(roundName);
            this.matchRoundService.insert(matchRound);
            this.matchService.updateMatch(match);
            List<User> refereeList = new ArrayList<>();
            int refereeNum = 0;
            int addressNum = 0;
            String[] addressList = address.split("-");
            String[] refereeId = match.getRefereeId().split("-");
            for (String id:
                    refereeId) {
                refereeList.add(this.userService.getUserByUid(Integer.parseInt(id)));
            }

            if(match.getIsTeamUp() == 0) { //不允许组队
                if(sort == 1) {
                    Collections.sort(refereeList, new Comparator<User>() {
                        @Override
                        public int compare(User o1, User o2) {
                            return o1.getGrade() - o2.getGrade();
                        }
                    });
                }else {
                    Collections.shuffle(refereeList);
                }
                Confrontation[] list = this.confrontationService.getList(0, matchId);
                int nullNum = judgeNull(list.length);
                for(int i = 0, len = list.length; i < len - nullNum; i+=2) {
                    System.out.println("i"+i);
                    if(match.getStatus()==1) {
                        this.confrontationService.updateMatch(round+"-"+(i+1+""), list[i].getId());
                        this.confrontationService.updateMatch(round+"-"+(i+1+""), list[i+1].getId());
                    }else {
                        this.confrontationService.updateMatch(list[i].getMatch()+","+round+"-"+(i+1+""), list[i].getId());
                        this.confrontationService.updateMatch(list[i+1].getMatch()+","+round+"-"+(i+1+""), list[i+1].getId());
                    }
                    User user1 = this.userService.getUserByUid(list[i].getTeamId());
                    User user2 = this.userService.getUserByUid(list[i + 1].getTeamId());
                    MatchResult matchResult = new MatchResult();
                    matchResult.setAddress(addressList[addressNum]);
                    addressNum++;
                    addressNum = addressNum % addressList.length;
                    matchResult.setContestant(user1.getUid()+"-"+user2.getUid());
                    matchResult.setMatchId(matchId);
                    User referee = refereeList.get(refereeNum);
                    refereeNum++;
                    refereeNum = refereeNum % refereeId.length;
                    matchResult.setReferee(referee.getUid());
                    matchResult.setRefereeName(referee.getUsername());
                    matchResult.setTeam1(user1.getUsername());
                    matchResult.setTeam2(user2.getUsername());
                    matchResult.setRound(round);
                    this.matchResultService.insert(matchResult);
                }
                for(int len = list.length, i = len - nullNum; i < len; i++) {
                    MatchResult matchResult = new MatchResult();
                    if(match.getStatus()==1) {
                        this.confrontationService.updateMatch(round+"-"+"0", list[i].getId());
                    }else {
                        this.confrontationService.updateMatch(list[i].getMatch()+","+round+"-"+"-1", list[i].getId());
                    }
                    User user = this.userService.getUserByUid(list[i].getTeamId());
                    matchResult.setContestant("");
                    matchResult.setRound(round);
                    matchResult.setTeam1(user.getUsername());
                    this.matchResultService.insert(matchResult);
                }
            }else { //允许组队
                teamAuto(refereeList, addressList, matchId, round);
            }
            response.setCode(0);
            message.put("result", true);
            response.setMessage(message);
//        }catch (Exception e) {
//            System.out.println(e);
//            response.setCode(-1);
//            message.put("error", e);
//            response.setMessage(message);
//        }
        return response;
    }

    int judgeNull(int num) {
        int i = 1;
        while(num > Math.pow(2, i)) {
            i++;
        }
        return (int)Math.pow(2, i) - num;
    }

    public String getUser(TeamDetail[] teamDetails) {
        if(teamDetails.length == 1) {
            User user = userService.getUserByUid(teamDetails[0].getUserId());

            return user.getName();
        }else {
            System.out.println("teamDetails");
            System.out.println(teamDetails.length);
            User user1 = userService.getUserByUid(teamDetails[0].getUserId());
            User user2 = userService.getUserByUid(teamDetails[1].getUserId());

            return user1.getName()+"/"+user2.getName();
        }
    }

    public void teamAuto(List<User> refereeList, String[] addressList, int matchId, int round) {
        Confrontation[] list = this.confrontationService.getList(0, matchId);
        Match match = this.matchService.getMatchById(matchId);
        int refereeNum = 0;
        int addressNum = 0;
        int nullNum = judgeNull(list.length);
        for(int i = 0, len = list.length; i < len - nullNum; i+=2) {
            if (match.getStatus() == 1) {
                this.confrontationService.updateMatch(round + "-" + (i + 1 + ""), list[i].getId());
                this.confrontationService.updateMatch(round + "-" + (i + 1 + ""), list[i + 1].getId());
            } else {
                this.confrontationService.updateMatch(list[i].getMatch() + "," + round + "-" + (i + 1 + ""), list[i].getId());
                this.confrontationService.updateMatch(list[i + 1].getMatch() + "," + round + "-" + (i + 1 + ""), list[i + 1].getId());
            }
            MatchType[] matchTypes = matchTypeService.get(matchId);
            for (MatchType matchType:
                 matchTypes) {
                String type = round+"-"+matchType.getType();
                System.out.println(list[i].getTeamId());
                System.out.println(type);
                TeamDetail[] teamDetails1 = teamDetailService.get(list[i].getTeamId(), type);
                TeamDetail[] teamDetails2 = teamDetailService.get(list[i + 1].getTeamId(), type);
                Team team1 = teamService.getTeamById(list[i].getTeamId());
                Team team2 = teamService.getTeamById(list[i+1].getTeamId());
                MatchResult matchResult = new MatchResult();
                matchResult.setRound(round);
                matchResult.setContestant(team1.getId()+ "-" + team2.getId());
                matchResult.setMatchId(matchId);
                matchResult.setTeam1(getUser(teamDetails1));
                matchResult.setTeam2(getUser(teamDetails2));
                User user = refereeList.get(refereeNum);
                matchResult.setReferee(user.getUid());
                matchResult.setRefereeName(user.getName());
                refereeNum++;
                refereeNum = refereeNum % refereeList.toArray().length;
                matchResult.setAddress(addressList[addressNum]);
                addressNum++;
                addressNum = addressNum % addressList.length;
                matchResult.setType(matchType.getText());
                matchResultService.insert(matchResult);
            }
        }
        for(int len = list.length, i = len - nullNum; i < len; i++) {
//            MatchResult matchResult = new MatchResult();
            if(match.getStatus()==1) {
                this.confrontationService.updateMatch(round+"-"+"0", list[i].getId());
            }else {
                this.confrontationService.updateMatch(list[i].getMatch()+","+round+"-"+"0", list[i].getId());
            }
//            User user = this.userService.getUserByUid(list[i].getTeamId());
//            matchResult.setContestant("");
//            matchResult.setRound(round);
//            matchResult.setTeam1(user.getUsername());
//            this.matchResultService.insert(matchResult);
        }
    }

    @GetMapping("teamConfrontation")
    public Response teamConfrontation(int matchId) {
        Confrontation[] list1 = confrontationService.getList(0, matchId);
        int length = list1.length;
        int actual = (length - judgeNull(length))/2;
        Match matchById = matchService.getMatchById(matchId);
        int round = matchById.getStatus();
        List<Map> list = new ArrayList();
        for(int i = 1;i <= actual; i++) {
            String match = round+"-"+i;
            System.out.println(matchId);
            System.out.println(match);
            Confrontation[] two = confrontationService.findTwo(matchId, match);
            System.out.println(two.length);
            System.out.println(two[0].getTeamId());
            Team team1 = teamService.getTeamById(two[0].getTeamId());
            Team team2 = teamService.getTeamById(two[1].getTeamId());
            String contestant = team1.getId() + "-" + team2.getId();
            MatchResult[] items = matchResultService.getItems(matchId, team1.getId() + "-" + team2.getId(), team2.getId() + "-" + team1.getId());
            Map map = new HashMap();
            map.put("team", team1.getName()+"-"+team2.getName());
            map.put("detail", items);
            list.add(map);
        }

        Response response = new Response();
        Map message = new HashMap();
        response.setCode(0);
        message.put("result", list);
        response.setMessage(message);

        return response;
    }
}
