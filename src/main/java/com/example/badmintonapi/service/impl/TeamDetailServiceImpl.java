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

    @Override
    public TeamDetail[] getTeamDetail(int teamId) {
        return teamDetailMapper.getTeamDetail(teamId);
    }

    @Override
    public boolean update(int teamId, int userId, String type) {
        int result = teamDetailMapper.update(teamId, userId, type);
        return result == 1 ? true : false;
    }

    @Override
    public TeamDetail select(int teamId, int userId) {
        return teamDetailMapper.select(teamId, userId);
    }

    @Override
    public TeamDetail[] get(int teamId, String type) {
        return teamDetailMapper.get(teamId, type);
    }
}
