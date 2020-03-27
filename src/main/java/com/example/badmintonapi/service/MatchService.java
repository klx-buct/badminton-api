package com.example.badmintonapi.service;

import com.example.badmintonapi.domain.Match;

public interface MatchService {
    public int insertMatch(Match match);

    public Match[] getMatchByStatus(int status);

    public Match[] getMatchByKeywords(String name);

    public Match getMatchById(int id);

    public boolean updateMatch(Match match);

    public boolean updateMatchEnterid(String enterId, int id);

    public boolean updateMatchRefereeId(String refereeId, int id);

    public Match[] getIngMatch();

    public Match[] getNeedPrize(int isPrize);

    public boolean updatePrize(int isPrize, int id);
}
