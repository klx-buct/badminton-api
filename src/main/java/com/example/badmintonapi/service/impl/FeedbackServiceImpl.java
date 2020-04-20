package com.example.badmintonapi.service.impl;

import com.example.badmintonapi.domain.Feedback;
import com.example.badmintonapi.mapper.FeedbackMapper;
import com.example.badmintonapi.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    FeedbackMapper feedbackMapper;
    @Override
    public boolean insert(Feedback feedback) {
        int res = feedbackMapper.insert(feedback);

        return res == 1 ? true : false;
    }
}
