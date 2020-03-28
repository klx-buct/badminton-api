package com.example.badmintonapi.service.impl;

import com.example.badmintonapi.domain.MatchType;
import com.example.badmintonapi.mapper.MatchTypeMapper;
import com.example.badmintonapi.service.MatchTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchTypeServiceImpl implements MatchTypeService {
    @Autowired
    MatchTypeMapper matchTypeMapper;
    @Override
    public boolean insert(MatchType matchType) {
        int result = matchTypeMapper.insert(matchType);
        return result == 1 ? true : false;
    }
}
