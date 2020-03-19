package com.example.badmintonapi.service;

import com.example.badmintonapi.domain.MatchRound;

public interface MatchRoundService {
    public boolean insert(MatchRound matchRound);

    public MatchRound getMatchRound(int round, int matchId);

    public MatchRound[] getRound(int matchId);
}
