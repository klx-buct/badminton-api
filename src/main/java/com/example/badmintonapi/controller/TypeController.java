package com.example.badmintonapi.controller;

import com.example.badmintonapi.domain.Type;
import com.example.badmintonapi.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/type")
public class TypeController {
    @Autowired
    TypeService typeService;

    @GetMapping("list")
    public Type[] getType() {
        return this.typeService.getType();
    }
}
