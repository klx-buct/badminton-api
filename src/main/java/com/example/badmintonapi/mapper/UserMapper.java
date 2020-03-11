package com.example.badmintonapi.mapper;

import com.example.badmintonapi.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
    @Insert("INSERT INTO user(username, password, forbid, level) VALUES(#{username}, #{password}, 0, 0)")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")   //keyProperty java对象的属性；keyColumn表示数据库的字段
    int insert(User user);

    @Insert("insert into `user-detail`(name, sex, uid, `match`, username, referee, member, schoolNumber, grade) values(#{name}, #{sex}, #{uid}, #{match}, #{username}, #{referee}, #{member}, #{schoolNumber}, #{grade})")
    int insertDetail(User user);

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

    @Select("select * from `user-detail` where uid=#{uid}")
    User getUserByUid(int uid);
}
