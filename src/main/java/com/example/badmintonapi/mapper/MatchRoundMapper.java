package com.example.badmintonapi.mapper;

import com.example.badmintonapi.domain.MatchRound;
import org.apache.ibatis.annotations.Insert;

public interface MatchRoundMapper {
    @Insert("insert into `match-round`(round, text, matchId) values(#{round}, #{text}, #{matchId})")
    int insert(MatchRound matchRound);
}
