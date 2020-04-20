package com.example.badmintonapi.service.impl;

import com.example.badmintonapi.domain.Prize;
import com.example.badmintonapi.mapper.PrizeMapper;
import com.example.badmintonapi.service.PrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrizeServiceImpl implements PrizeService {
    @Autowired
    PrizeMapper prizeMapper;
    @Override
    public boolean insert(Prize prize) {
        int result = prizeMapper.insert(prize);
        return result == 1 ? true : false;
    }

    @Override
    public Prize[] select(int matchId) {
        return prizeMapper.select(matchId);
    }
}
