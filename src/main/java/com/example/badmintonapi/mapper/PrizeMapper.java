package com.example.badmintonapi.mapper;

import com.example.badmintonapi.domain.Prize;
import org.apache.ibatis.annotations.Insert;

public interface PrizeMapper {
    @Insert("insert into prize(name, grade, matchId, teamId, content) values(#{name}, #{grade}, #{matchId}, #{teamId}, #{content})")
    int insert(Prize prize);
}
