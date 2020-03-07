package com.example.badmintonapi.controller;

import com.example.badmintonapi.domain.Notice;
import com.example.badmintonapi.domain.Response;
import com.example.badmintonapi.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/notice")
public class NoticeController {
    @Autowired
    NoticeService noticeService;

    @PostMapping("insert")
    public boolean insertNotice(@RequestBody Notice notice) {
        return noticeService.insertNotice(notice);
    }

    @GetMapping("list")
    public Response getNoticeByPage(int delete, int pageSize, int pageIndex) {
        Response response = new Response();
        response.setCode(0);
        Map message = new HashMap();
        Notice[] notices = noticeService.getNoticeByStatus(delete);
        int total = notices.length;
        message.put("notice", Arrays.copyOfRange(notices, pageSize*(pageIndex-1), pageSize*(pageIndex-1) + pageSize > total ? total : pageSize*(pageIndex-1) + pageSize));
        message.put("total", total);
        response.setMessage(message);

        return response;
    }
}
