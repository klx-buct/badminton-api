package com.example.badmintonapi.service.impl;

import com.example.badmintonapi.domain.MatchRound;
import com.example.badmintonapi.mapper.MatchRoundMapper;
import com.example.badmintonapi.service.MatchRoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchRoundServiceImpl implements MatchRoundService {
    @Autowired
    MatchRoundMapper matchRoundMapper;
    @Override
    public boolean insert(MatchRound matchRound) {
        int result = matchRoundMapper.insert(matchRound);

        return result == 1 ? true : false;
    }

    @Override
    public MatchRound getMatchRound(int round, int matchId) {
        return matchRoundMapper.getMatchRound(round, matchId);
    }

    @Override
    public MatchRound[] getRound(int matchId) {
        return matchRoundMapper.getRound(matchId);
    }
}
