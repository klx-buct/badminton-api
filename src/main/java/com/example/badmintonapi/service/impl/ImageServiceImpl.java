package com.example.badmintonapi.service.impl;

import com.example.badmintonapi.domain.Image;
import com.example.badmintonapi.mapper.ImageMapper;
import com.example.badmintonapi.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    ImageMapper imageMapper;

    @Override
    public Image select(String type) {
        return imageMapper.select(type);
    }

    @Override
    public boolean update(String imgUrl, String type) {
        int result = imageMapper.update(imgUrl, type);
        return result == 1 ? true : false;
    }
}
