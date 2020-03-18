package com.example.badmintonapi.controller;

import com.example.badmintonapi.domain.*;
import com.example.badmintonapi.service.ConfrontationService;
import com.example.badmintonapi.service.MatchResultService;
import com.example.badmintonapi.service.MatchService;
import com.example.badmintonapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("list")
    List<Arrange> getList() {
        Match[] matches = matchService.getIngMatch();
        List<Arrange> arranges = new ArrayList<Arrange>();
        for (int i = 0,len = matches.length; i < len; i++) {
            int id = matches[i].getId();
            Confrontation[] confrontations = confrontationService.getList(0,  id);
            boolean judge = true;
            if(matches[i].getStatus() != 0) {
                for (Confrontation confrontation:
                        confrontations) {
                    int teamId = confrontation.getTeamId();
                    MatchResult matchResult = matchResultService.getMatchResult(teamId + "", matches[i].getStatus());
                    if(matchResult.getGrade() == null) {
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

    @GetMapping("confrontation")
    MatchResult[] getListByMatchId(int matchId) {
        return this.matchResultService.getMatchResultByMatchId(matchId);
    }

    @GetMapping("auto")
    boolean auto(int matchId, String address) {
        Match match = this.matchService.getMatchById(matchId);
        match.setStatus(match.getStatus()+1);
        int round = match.getStatus();
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
            Confrontation[] list = this.confrontationService.getList(0, matchId);
            int nullNum = judgeNull(list.length);
            System.out.println(nullNum);
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
                    this.confrontationService.updateMatch(round+"-"+"-1", list[i].getId());
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

        }
        return true;
    }

    int judgeNull(int num) {
        int i = 1;
        while(num > Math.pow(2, i)) {
            i++;
        }
        return (int)Math.pow(2, i) - num;
    }
}
