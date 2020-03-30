package com.example.badmintonapi.mapper;

import com.example.badmintonapi.domain.Team;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

public interface TeamMapper {
    @Insert("insert into team(matchId, name, people, caption, grade, victor) values(#{matchId}, #{name}, #{people}, #{caption}, #{grade}, #{victor})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insertTeam(Team team);

    @Select("select * from team where matchId = #{matchId} and caption = #{caption}")
    Team getTeam(int matchId, String caption);

    @Select("select * from team where id=#{teamId}")
    Team getTeamById(int teamId);
}
