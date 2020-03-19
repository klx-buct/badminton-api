package com.example.badmintonapi.mapper;

import com.example.badmintonapi.domain.MatchRound;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface MatchRoundMapper {
    @Insert("insert into `match-round`(round, text, matchId) values(#{round}, #{text}, #{matchId})")
    int insert(MatchRound matchRound);

    @Select("select * from `match-round` where round=${round} and matchId=#{matchId}")
    MatchRound getMatchRound(int round, int matchId);
}
