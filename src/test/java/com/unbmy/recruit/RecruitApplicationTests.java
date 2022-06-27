package com.unbmy.recruit;

import com.unbmy.recruit.controller.UserController;
import com.unbmy.recruit.service.INoticeService;
import com.unbmy.recruit.vo.NoticeVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
class RecruitApplicationTests {
    @Resource
    private INoticeService noticeService;

    @Test
    void contextLoads() {
        NoticeVo noticeVo = noticeService.queryNotice(1);
        System.out.println(noticeVo);
    }

}
