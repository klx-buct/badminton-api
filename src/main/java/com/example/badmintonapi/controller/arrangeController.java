package com.example.badmintonapi.controller;

import com.example.badmintonapi.domain.Arrange;
import com.example.badmintonapi.domain.Confrontation;
import com.example.badmintonapi.domain.Match;
import com.example.badmintonapi.domain.MatchResult;
import com.example.badmintonapi.service.ConfrontationService;
import com.example.badmintonapi.service.MatchResultService;
import com.example.badmintonapi.service.MatchService;
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
}
