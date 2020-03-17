package com.example.badmintonapi.mapper;

import com.example.badmintonapi.domain.MatchResult;
import org.apache.ibatis.annotations.Select;

public interface MatchResultMapper {
    @Select("select * from `match-result` where contestant like concat('%', #{uid}, '%') and round = #{round}")
    MatchResult getMatchResult(String uid, int round);

    @Select("select * from `match-result` where matchId=#{matchId}")
    MatchResult[] getMatchResultByMatchId(int matchId);

}
