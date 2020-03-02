package com.example.badmintonapi.mapper;

import com.example.badmintonapi.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Insert("INSERT INTO user(username, password) VALUES(#{username}, #{password})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")   //keyProperty java对象的属性；keyColumn表示数据库的字段
    int insert(User user);

    @Select("select * from user where username=#{username} and password=#{password}")
    User select(User user);
}
