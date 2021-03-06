package com.example.badmintonapi.service.impl;

import com.example.badmintonapi.domain.MatchResult;
import com.example.badmintonapi.mapper.MatchResultMapper;
import com.example.badmintonapi.service.MatchResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchResultServiceImpl implements MatchResultService {
    @Autowired
    MatchResultMapper matchResultMapper;
    @Override
    public MatchResult getMatchResult(String uid, int round, int matchId) {
        return matchResultMapper.getMatchResult(uid, round, matchId);
    }

    @Override
    public MatchResult[] getMatchResultByMatchId(int matchId) {
        return matchResultMapper.getMatchResultByMatchId(matchId);
    }

    @Override
    public boolean insert(MatchResult matchResult) {
        int result = matchResultMapper.insert(matchResult);

        return result == 1 ? true : false;
    }

    @Override
    public MatchResult getItem(int matchId, String contestant, String contestant2) {
        return matchResultMapper.getItem(matchId, contestant, contestant2);
    }

    @Override
    public MatchResult[] getUserReferee(int uid) {
        return matchResultMapper.getUserReferee(uid);
    }

    @Override
    public boolean updateResult(MatchResult matchResult) {
        int result = matchResultMapper.updateResult(matchResult);

        return result == 1 ? true : false;
    }

    @Override
    public MatchResult getResultById(int id) {
        return matchResultMapper.getResultById(id);
    }

    @Override
    public MatchResult[] getItems(int matchId, String contestant, String contestant2) {
        return matchResultMapper.getItems(matchId, contestant, contestant2);
    }

    @Override
    public MatchResult getUserMatch(String name) {
        return matchResultMapper.getUserMatch(name);
    }
}
