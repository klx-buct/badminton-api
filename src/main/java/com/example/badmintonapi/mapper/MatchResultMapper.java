package com.example.badmintonapi.mapper;

import com.example.badmintonapi.domain.MatchResult;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface MatchResultMapper {
    @Select("select * from `match-result` where contestant like concat('%', #{uid}, '%') and round = #{round} and matchId=#{matchId}")
    MatchResult getMatchResult(String uid, int round, int matchId);

    @Select("select * from `match-result` where matchId=#{matchId}")
    MatchResult[] getMatchResultByMatchId(int matchId);

    @Insert("insert into `match-result`(matchId, round, contestant, team1, team2, referee, refereeName, address) values(#{matchId}, #{round}, #{contestant}, #{team1}, #{team2}, #{referee}, #{refereeName}, #{address})")
    int insert(MatchResult matchResult);

}
