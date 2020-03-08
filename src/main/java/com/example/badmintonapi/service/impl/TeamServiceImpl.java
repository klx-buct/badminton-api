package com.example.badmintonapi.service.impl;

import com.example.badmintonapi.domain.Team;
import com.example.badmintonapi.mapper.TeamMapper;
import com.example.badmintonapi.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    TeamMapper teamMapper;

    @Override
    public boolean insertTeam(Team team) {
        int result = teamMapper.insertTeam(team);
        return result == 1 ? true : false;
    }
}
