package com.example.badmintonapi.mapper;

import com.example.badmintonapi.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
    @Insert("INSERT INTO user(username, password) VALUES(#{username}, #{password})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")   //keyProperty java对象的属性；keyColumn表示数据库的字段
    int insert(User user);

    @Select("select * from user where username=#{username} and password=#{password}")
    User select(User user);

    @Select("select * from `user-detail`")
    User[] getAllUser();

    @Select("select * from `user-detail` where name like concat('%', #{name}, '%')")
    User[] getUsersByKeywords(String name);

    @Select("select * from `user-detail` where member=#{member} and name like concat('%', #{name}, '%')")
    User[] getUsersByMember(int member, String name);

    @Select("select * from `user-detail` where schoolNumber = #{schoolNumber}")
    User getUserBySchoolNumber(String schoolNumber);

    @Update("update `user-detail` set joinMatch=#{joinMatch} where id=#{id}")
    boolean updateJoinMatch(String joinMatch, int id);
}
