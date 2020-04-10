package com.example.badmintonapi.mapper;

import com.example.badmintonapi.domain.Team;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface TeamMapper {
    @Insert("insert into team(matchId, name, people, caption, grade, victor) values(#{matchId}, #{name}, #{people}, #{caption}, #{grade}, #{victor})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insertTeam(Team team);

    @Select("select * from team where matchId = #{matchId} and caption = #{caption}")
    Team getTeam(int matchId, String caption);

    @Select("select * from team where id=#{teamId}")
    Team getTeamById(int teamId);

    @Select("select * from team where matchId=#{matchId}")
    Team[] getTeamList(int matchId);

    @Select("select * from team where matchId=#{matchId} and end!=-1")
    Team[] getInTeam(int matchId);

    @Update("update team set grade=#{grade}, victor=#{victor} where id=#{teamId}")
    int update(String grade, String victor, int teamId);
}
