package com.example.badmintonapi.service;

import com.example.badmintonapi.domain.Notice;

public interface NoticeService {
    public boolean insertNotice(Notice notice);
    public Notice[] getNoticeByStatus(int delete);
}
