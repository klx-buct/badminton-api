package com.example.badmintonapi.mapper;

import com.example.badmintonapi.domain.Match;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface MatchMapper {
    @Insert(
    "insert into " +
            "`match`(name, introduce, address, prize, begTime, endTime, `limit`, limitPeople, actualPlayer, player, referee, actualReferee, hintCount, status, time, isTeamUp, teamUpLimit, enterId) " +
            "values(#{name}, #{introduce}, #{address}, #{prize}, #{begTime}, #{endTime}, #{limit}, #{limitPeople}, #{actualPlayer}, #{player}, #{referee}, #{actualReferee}, #{hintCount}, #{status}, #{time}, #{isTeamUp}, #{teamUpLimit}, #{enterId})"
    )
    int insert(Match match);

    @Select("select * from `match` where status=#{status}")
    Match[] getMatchByStatus(int status);

    @Select("select * from `match` where name like concat('%', #{name}, '%')")
    Match[] getMatchByKeywords(String name);

    @Select("select * from `match` where id = #{id}")
    Match getMatchById(int id);

    @Update("update `match` set name=#{name}, introduce=#{introduce}, address=#{address}, prize=#{prize}, begTime=#{begTime}, endTime=#{endTime}, `limit`=#{limit}, limitPeople=#{limitPeople}, player=#{player}, referee=#{referee}, isTeamUp=#{isTeamUp}, teamUpLimit=#{teamUpLimit}, status=#{status} where id=#{id}")
    int updateMatch(Match match);

    @Update("update `match` set enterId=#{enterId} where id = #{id}")
    int updateMatchEnterid(String enterId, int id);

    @Update("update `match` set refereeId=#{refereeId} where id = #{id}")
    int updateMatchRefereeId(String refereeId, int id);

    @Select("select * from `match` where status != -1")
    Match[] getIngMatch();
}
