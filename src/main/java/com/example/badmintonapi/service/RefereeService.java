package com.example.badmintonapi.service;

import com.example.badmintonapi.domain.Referee;

public interface RefereeService {
    public boolean insert(Referee referee);

    public Referee[] select(int status);
}
