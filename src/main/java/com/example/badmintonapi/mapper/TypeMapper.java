package com.example.badmintonapi.mapper;

import com.example.badmintonapi.domain.Type;
import org.apache.ibatis.annotations.Select;


public interface TypeMapper {
    @Select("select * from type")
    Type[] getType();
}
