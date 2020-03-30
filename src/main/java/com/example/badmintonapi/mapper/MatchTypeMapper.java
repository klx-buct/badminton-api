package com.example.badmintonapi.mapper;

import com.example.badmintonapi.domain.MatchType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface MatchTypeMapper {
    @Insert("insert into `match-type`(type, text, num, matchId) values(#{type}, #{text}, #{num}, #{matchId})")
    int insert(MatchType matchType);

    @Select("select * from `match-type` where matchId=#{matchId}")
    MatchType[] get(int matchId);
}
