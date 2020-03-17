package com.example.badmintonapi.service;

import com.example.badmintonapi.domain.MatchResult;

public interface MatchResultService {
    public MatchResult getMatchResult(String uid, int round);

    public MatchResult[] getMatchResultByMatchId(int matchId);
}
