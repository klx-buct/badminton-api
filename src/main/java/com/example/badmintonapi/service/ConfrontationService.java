package com.example.badmintonapi.service;

import com.example.badmintonapi.domain.Confrontation;

public interface ConfrontationService {
    public boolean insert(Confrontation confrontation);

    public Confrontation[] getList(int end, int matchId);

    public Confrontation[] getListByMatchId(int matchId);

    public boolean updateMatch(String match, int id);

    public Confrontation userMatch(int matchId, int teamId);

    public Confrontation findOpponent(int matchId, String match, int nowUid);

    public boolean updateEnd(int end, int id);
}
