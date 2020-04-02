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

    @Override
    public Team getTeam(int matchId, String caption) {
        return teamMapper.getTeam(matchId, caption);
    }

    @Override
    public Team getTeamById(int teamId) {
        return teamMapper.getTeamById(teamId);
    }

    @Override
    public Team[] getTeamList(int matchId) {
        return teamMapper.getTeamList(matchId);
    }

    @Override
    public Team[] getInTeam(int matchId) {
        return teamMapper.getInTeam(matchId);
    }
}
