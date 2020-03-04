package com.example.badmintonapi.controller;

import com.example.badmintonapi.domain.Response;
import com.example.badmintonapi.domain.Type;
import com.example.badmintonapi.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/type")
public class TypeController {
    @Autowired
    TypeService typeService;

    @GetMapping("list")
    public Response getType() {
        Type[] list = this.typeService.getType();
        Response response = new Response();
        response.setCode(0);
        Map message = new HashMap<>();
        message.put("result", list);
        response.setMessage(message);

        return response;
    }
}
