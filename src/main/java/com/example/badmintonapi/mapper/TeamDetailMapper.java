package com.example.badmintonapi.mapper;

import com.example.badmintonapi.domain.TeamDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface TeamDetailMapper {
    @Insert("insert into `team-detail`(teamId, userId, type, name) values(#{teamId}, #{userId}, #{type}, #{name})")
    public int insert(TeamDetail teamDetail);

    @Select("select * from `team-detail` where teamId=#{teamId}")
    public TeamDetail[] getTeamDetail(int teamId);

    @Update("update `team-detail` set type=#{type} where teamId=#{teamId} and userId=#{userId}")
    public int update(int teamId, int userId, String type);

    @Select("select * from `team-detail` where teamId=#{teamId} and userId=#{userId}")
    public TeamDetail select(int teamId, int userId);

    @Select("select * from `team-detail` where teamId=#{teamId} and type like concat('%', #{type}, '%')")
    public TeamDetail[] get(int teamId, String type);
}
