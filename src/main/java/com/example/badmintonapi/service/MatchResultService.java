package com.example.badmintonapi.service;

import com.example.badmintonapi.domain.MatchResult;

public interface MatchResultService {
    public MatchResult getMatchResult(String uid, int round, int matchId);

    public MatchResult[] getMatchResultByMatchId(int matchId);

    public boolean insert(MatchResult matchResult);

    public MatchResult getItem(int matchId, String contestant);

    public MatchResult[] getUserReferee(int uid);

    public boolean updateResult(MatchResult matchResult);

    public MatchResult getResultById(int id);
}
