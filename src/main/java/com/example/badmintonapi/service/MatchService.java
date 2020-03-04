package com.example.badmintonapi.service;

import com.example.badmintonapi.domain.Match;

public interface MatchService {
    public boolean insertMatch(Match match);

    public Match[] getMatchByStatus(int status);

    public Match[] getMatchByKeywords(String name);

    public Match getMatchById(int id);
}
