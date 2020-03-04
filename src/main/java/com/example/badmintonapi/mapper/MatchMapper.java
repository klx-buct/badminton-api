package com.example.badmintonapi.mapper;

import com.example.badmintonapi.domain.Match;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface MatchMapper {
    @Insert(
    "insert into " +
            "`match`(name, introduce, address, prize, begTime, endTime, `limit`, limitPeople, actualPlayer, player, referee, actualReferee, hintCount, status, time) " +
            "values(#{name}, #{introduce}, #{address}, #{prize}, #{begTime}, #{endTime}, #{limit}, #{limitPeople}, #{actualPlayer}, #{player}, #{referee}, #{actualReferee}, #{hintCount}, #{status}, #{time})"
    )
    int insert(Match match);

    @Select("select * from `match` where status=#{status}")
    Match[] getMatchByStatus(int status);

    @Select("select * from `match` where name like concat('%', #{name}, '%')")
    Match[] getMatchByKeywords(String name);

    @Select("select * from `match` where id = #{id}")
    Match getMatchById(int id);
}
