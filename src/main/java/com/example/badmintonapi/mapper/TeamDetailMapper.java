package com.example.badmintonapi.mapper;

import com.example.badmintonapi.domain.TeamDetail;
import org.apache.ibatis.annotations.Insert;

public interface TeamDetailMapper {
    @Insert("insert into `team-detail`(teamId, userId, type, name) values(#{teamId}, #{userId}, #{type}, #{name})")
    public int insert(TeamDetail teamDetail);
}
