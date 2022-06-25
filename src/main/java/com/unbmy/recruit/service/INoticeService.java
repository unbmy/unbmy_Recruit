package com.unbmy.recruit.service;

import com.unbmy.recruit.pojo.Notice;

import java.util.Date;
import java.util.List;

/**
 * @author Unbmy
 */
public interface INoticeService {

    List<Notice> getAllNotice();
    List<Notice> getLatestNotice();
    Notice getNoticeById(Long id);
    int updateNotice(Notice notice);
    int deleteNotice(Long id);
    int addNotice(String topic, String content, Date date);

}
