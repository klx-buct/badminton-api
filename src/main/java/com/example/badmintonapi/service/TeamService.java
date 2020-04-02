package com.example.badmintonapi.service;

import com.example.badmintonapi.domain.Team;

public interface TeamService {
    public boolean insertTeam(Team team);

    public Team getTeam(int matchId, String caption);

    public Team getTeamById(int teamId);

    public Team[] getTeamList(int matchId);

    public Team[] getInTeam(int matchId);
}
