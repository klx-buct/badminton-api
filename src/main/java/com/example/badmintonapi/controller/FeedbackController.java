package com.example.badmintonapi.controller;


import com.example.badmintonapi.domain.Feedback;
import com.example.badmintonapi.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/feedback")
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;
    @PostMapping("insert")
    public boolean insert(@RequestBody Feedback feedback) {
        return feedbackService.insert(feedback);
    }
}
