package com.example.badmintonapi.mapper;

import com.example.badmintonapi.domain.Referee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface RefereeMapper {
    @Insert("insert into `referee-application`(uid, time, text, img, status, username) values(#{uid}, #{time}, #{text}, #{img}, #{status}, #{username})")
    int insert(Referee referee);

    @Select("select * from `referee-application` where status=#{status}")
    Referee[] select(int status);
}
