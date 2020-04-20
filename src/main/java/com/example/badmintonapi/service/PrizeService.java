package com.example.badmintonapi.service;

import com.example.badmintonapi.domain.Prize;

public interface PrizeService {
    public boolean insert(Prize prize);

    public Prize[] select(int matchId);
}
