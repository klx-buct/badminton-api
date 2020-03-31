package com.example.badmintonapi.service;

import com.example.badmintonapi.domain.TeamDetail;

public interface TeamDetailService {
    public boolean insert(TeamDetail teamDetail);

    public TeamDetail[] getTeamDetail(int teamId);

    public boolean update(int teamId, int userId, String type);

    public TeamDetail select(int teamId, int userId);

    public TeamDetail[] get(int teamId, String type);
}
