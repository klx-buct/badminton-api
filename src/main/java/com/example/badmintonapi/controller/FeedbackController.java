package com.example.badmintonapi.controller;


import com.example.badmintonapi.domain.Feedback;
import com.example.badmintonapi.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/feedback")
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;
    @PostMapping("insert")
    public boolean insert(@RequestBody Feedback feedback) {
        return feedbackService.insert(feedback);
    }
    @GetMapping("list")
    public Feedback[] getList() {
        return feedbackService.list();
    }

    @PostMapping("update")
    public boolean update(@RequestBody Map<String, String> params) {
        int status = Integer.parseInt(params.get("status"));
        int id = Integer.parseInt(params.get("id"));

        return feedbackService.update(status, id);
    }
}
