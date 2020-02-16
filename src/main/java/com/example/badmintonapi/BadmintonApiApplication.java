package com.example.badmintonapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.badmintonapi.mapper")
public class BadmintonApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BadmintonApiApplication.class, args);
    }

}
