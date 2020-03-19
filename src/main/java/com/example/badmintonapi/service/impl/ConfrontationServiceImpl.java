package com.example.badmintonapi.service.impl;

import com.example.badmintonapi.domain.Confrontation;
import com.example.badmintonapi.mapper.ConfrontationMapper;
import com.example.badmintonapi.service.ConfrontationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfrontationServiceImpl implements ConfrontationService {
    @Autowired
    ConfrontationMapper confrontationMapper;
    @Override
    public boolean insert(Confrontation confrontation) {
        int result = confrontationMapper.insert(confrontation);
        return result == 1 ? true : false;
    }

    @Override
    public Confrontation[] getList(int end, int matchId) {
        return confrontationMapper.getList(end, matchId);
    }

    @Override
    public Confrontation[] getListByMatchId(int matchId) {
        return confrontationMapper.getListByMatchId(matchId);
    }

    @Override
    public boolean updateMatch(String match, int id) {
        int result = confrontationMapper.updateMatch(match, id);

        return result == 1 ? true : false;
    }

    @Override
    public Confrontation userMatch(int matchId, int teamId) {
        return confrontationMapper.userMatch(matchId, teamId);
    }

    @Override
    public Confrontation findOpponent(int matchId, String match, int nowUid) {
        return confrontationMapper.findOpponent(matchId, match, nowUid);
    }
}
