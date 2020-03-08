package com.example.badmintonapi.service.impl;

import com.example.badmintonapi.domain.Match;
import com.example.badmintonapi.mapper.MatchMapper;
import com.example.badmintonapi.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchServiceImpl implements MatchService {
    @Autowired
    MatchMapper matchMapper;

    @Override
    public boolean insertMatch(Match match) {
        int insert = matchMapper.insert(match);
        return insert == 1 ? true : false;
    }

    @Override
    public Match[] getMatchByStatus(int status) {
        return matchMapper.getMatchByStatus(status);
    }

    @Override
    public Match[] getMatchByKeywords(String name) {
        return matchMapper.getMatchByKeywords(name);
    }

    @Override
    public Match getMatchById(int id) {
        return matchMapper.getMatchById(id);
    }

    @Override
    public boolean updateMatch(Match match) {
        int res = matchMapper.updateMatch(match);
        return res == 1 ? true : false;
    }

    @Override
    public boolean updateMatchEnterid(String enterId, int id) {
        int result = this.matchMapper.updateMatchEnterid(enterId, id);
        return result == 1 ? true : false;
    }
}
