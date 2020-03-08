package com.example.badmintonapi.mapper;

import com.example.badmintonapi.domain.Team;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

public interface TeamMapper {
    @Insert("insert into team(matchId, name, people, caption, grade, victor) values(#{matchId}, #{name}, #{people}, #{caption}, #{grade}, #{victor})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insertTeam(Team team);
}
