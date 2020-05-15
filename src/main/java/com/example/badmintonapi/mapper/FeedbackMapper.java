package com.example.badmintonapi.mapper;

import com.example.badmintonapi.domain.Feedback;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface FeedbackMapper {
    @Insert("insert into feedback(content, user, time, status) values(#{content}, #{user}, #{time}, 0)")
    int insert(Feedback feedback);

    @Select("select * from feedback where status = 0")
    Feedback[] list();

    @Update("update feedback set status=#{status} where id=#{id}")
    int update(int status, int id);
}
