package com.example.badmintonapi.controller;

import com.example.badmintonapi.domain.Image;
import com.example.badmintonapi.domain.Notice;
import com.example.badmintonapi.domain.Response;
import com.example.badmintonapi.service.ImageService;
import com.example.badmintonapi.service.NoticeService;
import com.sun.tools.corba.se.idl.constExpr.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/notice")
public class NoticeController {
    @Autowired
    NoticeService noticeService;

    @Autowired
    ImageService imageService;

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

    @GetMapping("show")
    public Response showNotice() {
        Response response = new Response();
        response.setCode(0);
        Notice[] notices = this.noticeService.getNoticeByStatus(0);
        Comparator cmp = new MyComparator();
        Arrays.sort(notices, cmp);
        Map message = new HashMap();
        message.put("notices", notices);
        response.setMessage(message);
        return response;
    }

    @PostMapping("updateImg")
    public boolean update(@RequestBody Image image) {
        return imageService.update(image.getImgUrl(), "home");
    }

    @GetMapping("homeImg")
    public Image select() {
        return imageService.select("home");
    }
}

class MyComparator implements Comparator<Notice> {
    @Override
    public int compare(Notice o1, Notice o2) {
        /*
         * 如果o1小于o2，我们就返回正值，如果o1大于o2我们就返回负值， 这样颠倒一下，就可以实现降序排序了,反之即可自定义升序排序了
         */
        if(o1.getTop() > o2.getTop()) {
            return -1;
        }else if(o1.getTop() < o2.getTop()) {
            return 1;
        }else {
            return o2.getId() - o1.getId();
        }
    }
}
