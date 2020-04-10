package com.example.badmintonapi.mapper;

import com.example.badmintonapi.domain.Image;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ImageMapper {
    @Select("select * from image where type = #{type}")
    Image select(String type);

    @Update("update image set imgUrl=#{imgUrl} where type=#{type}")
    int update(String imgUrl, String type);
}
