package com.example.badmintonapi.mapper;

import com.example.badmintonapi.domain.Match;
import org.apache.ibatis.annotations.Insert;

public interface MatchMapper {
    @Insert(
    "insert into " +
            "`match`(name, introduce, address, prize, begTime, endTime, `limit`, limitPeople, actualPlayer, player, referee, actualReferee, hintCount) " +
            "values(#{name}, #{introduce}, #{address}, #{prize}, #{begTime}, #{endTime}, #{limit}, #{limitPeople}, #{actualPlayer}, #{player}, #{referee}, #{actualReferee}, #{hintCount})"
    )
    int insert(Match match);
}
