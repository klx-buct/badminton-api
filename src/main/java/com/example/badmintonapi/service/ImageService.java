package com.example.badmintonapi.service;

import com.example.badmintonapi.domain.Image;

public interface ImageService {
    public Image select(String type);

    public boolean update(String imgUrl, String type);
}
