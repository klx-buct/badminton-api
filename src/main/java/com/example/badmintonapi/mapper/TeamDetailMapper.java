package com.example.badmintonapi.mapper;

import com.example.badmintonapi.domain.TeamDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface TeamDetailMapper {
    @Insert("insert into `team-detail`(teamId, userId, type, name) values(#{teamId}, #{userId}, #{type}, #{name})")
    public int insert(TeamDetail teamDetail);

    @Select("select * from `team-detail` where teamId=#{teamId}")
    public TeamDetail[] getTeamDetail(int teamId);
}
