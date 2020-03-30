package com.example.badmintonapi.service;

import com.example.badmintonapi.domain.MatchType;

public interface MatchTypeService {
    boolean insert(MatchType matchType);

    MatchType[] get(int matchId);
}
