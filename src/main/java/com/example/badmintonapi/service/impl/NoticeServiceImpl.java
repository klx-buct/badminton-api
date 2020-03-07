package com.example.badmintonapi.service.impl;

import com.example.badmintonapi.domain.Notice;
import com.example.badmintonapi.mapper.NoticeMapper;
import com.example.badmintonapi.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    NoticeMapper noticeMapper;

    @Override
    public boolean insertNotice(Notice notice) {
        return noticeMapper.InsertNotice(notice);
    }

    @Override
    public Notice[] getNoticeByStatus(int delete) {
        return noticeMapper.getNoticeByStatus(delete);
    }
}
