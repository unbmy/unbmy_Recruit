package com.unbmy.recruit.service;

import com.unbmy.recruit.pojo.Notice;
import com.unbmy.recruit.vo.NoticeVo;

import java.util.Date;
import java.util.List;

/**
 * @author Unbmy
 */
public interface INoticeService {

    List<Notice> getAllNotice();
    List<Notice> getLatestNotice();
    Notice getNoticeById(Long id);
    NoticeVo queryNotice(Integer current);
    int updateNotice(Notice notice);
    int deleteNotice(Long id);
    int addNotice(String topic, String content, Date date);

}
