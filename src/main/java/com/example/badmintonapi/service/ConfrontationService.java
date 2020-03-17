package com.example.badmintonapi.service;

import com.example.badmintonapi.domain.Confrontation;

public interface ConfrontationService {
    public boolean insert(Confrontation confrontation);

    public Confrontation[] getList(int end, int matchId);

    public Confrontation[] getListByMatchId(int matchId);
}
