package com.example.badmintonapi.mapper;

import com.example.badmintonapi.domain.Notice;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface NoticeMapper {
    @Insert("insert into notice(title, content, top, promulgator, time) values(#{title}, #{content}, #{top}, #{promulgator}, #{time})")
    boolean InsertNotice(Notice notice);

    @Select("select * from notice where 'delete'=#{delete}")
    Notice[] getNoticeByStatus(int delete);
}
