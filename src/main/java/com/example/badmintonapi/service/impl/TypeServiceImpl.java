package com.example.badmintonapi.service.impl;

import com.example.badmintonapi.domain.Type;
import com.example.badmintonapi.mapper.TypeMapper;
import com.example.badmintonapi.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    TypeMapper typeMapper;

    @Override
    public Type[] getType() {
        return typeMapper.getType();
    }
}
