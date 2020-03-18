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
    public MatchResult getMatchResult(String uid, int round) {
        return matchResultMapper.getMatchResult(uid, round);
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
}
