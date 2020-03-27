package com.example.badmintonapi.service.impl;

import com.example.badmintonapi.domain.TeamDetail;
import com.example.badmintonapi.mapper.TeamDetailMapper;
import com.example.badmintonapi.service.TeamDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamDetailServiceImpl implements TeamDetailService {
    @Autowired
    TeamDetailMapper teamDetailMapper;
    @Override
    public boolean insert(TeamDetail teamDetail) {
        int res = teamDetailMapper.insert(teamDetail);

        return res == 1 ? true : false;
    }
}
