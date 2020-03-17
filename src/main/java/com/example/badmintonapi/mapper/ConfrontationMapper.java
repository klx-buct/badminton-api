package com.example.badmintonapi.mapper;

import com.example.badmintonapi.domain.Confrontation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface ConfrontationMapper {
    @Insert("insert into confrontation(matchId, teamId, match, end) values(#{matchId}, #{teamId}, #{match}, #{end})")
    int insert(Confrontation confrontation);

    @Select("select * from confrontation where end = #{end} and matchId = #{matchId}")
    Confrontation[] getList(int end, int matchId);

    @Select("select * from confrontation where matchId=#{matchId}")
    Confrontation[] getListByMatchId(int matchId);
}
