package com.example.badmintonapi.service.impl;

import com.example.badmintonapi.domain.Referee;
import com.example.badmintonapi.mapper.RefereeMapper;
import com.example.badmintonapi.service.RefereeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefereeImpl implements RefereeService {
    @Autowired
    RefereeMapper refereeMapper;

    @Override
    public boolean insert(Referee referee) {
        int result = refereeMapper.insert(referee);
        return result == 1 ? true : false;
    }

    @Override
    public Referee[] select(int status) {
        return refereeMapper.select(status);
    }
}
