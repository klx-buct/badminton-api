package com.example.badmintonapi.mapper;

import com.example.badmintonapi.domain.MatchResult;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface MatchResultMapper {
    @Select("select * from `match-result` where contestant like concat('%', #{uid}, '%') and round = #{round} and matchId=#{matchId}")
    MatchResult getMatchResult(String uid, int round, int matchId);

    @Select("select * from `match-result` where matchId=#{matchId}")
    MatchResult[] getMatchResultByMatchId(int matchId);

    @Insert("insert into `match-result`(matchId, round, contestant, team1, team2, referee, refereeName, address, type) values(#{matchId}, #{round}, #{contestant}, #{team1}, #{team2}, #{referee}, #{refereeName}, #{address}, #{type})")
    int insert(MatchResult matchResult);

    @Select("select * from `match-result` where matchId=#{matchId} and (contestant=#{contestant} or contestant=#{contestant2})")
    MatchResult getItem(int matchId, String contestant, String contestant2);

    @Select("select * from `match-result` where matchId=#{matchId} and (contestant=#{contestant} or contestant=#{contestant2})")
    MatchResult[] getItems(int matchId, String contestant, String contestant2);

    @Select(("select * from `match-result` where referee=#{uid} and grade is null"))
    MatchResult[] getUserReferee(int uid);

    @Update("update `match-result` set grade=#{grade} where id=#{id}")
    int updateResult(MatchResult matchResult);

    @Select("select * from `match-result` where id = #{id}")
    MatchResult getResultById(int id);

    @Select("select * from `match-result` where team1=#{name} or team2=#{name} and grade is null")
    MatchResult getUserMatch(String name);

}
