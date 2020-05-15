package com.example.badmintonapi.service;

import com.example.badmintonapi.domain.Feedback;

public interface FeedbackService {
    public boolean insert(Feedback feedback);

    public Feedback[] list();

    public boolean update(int status, int id);
}
