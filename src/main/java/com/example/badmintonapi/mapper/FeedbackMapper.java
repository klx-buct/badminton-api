package com.example.badmintonapi.mapper;

import com.example.badmintonapi.domain.Feedback;
import org.apache.ibatis.annotations.Insert;

public interface FeedbackMapper {
    @Insert("insert into feedback(content, user, time) values(#{content}, #{user}, #{time})")
    int insert(Feedback feedback);
}
