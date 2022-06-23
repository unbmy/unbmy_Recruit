package com.unbmy.recruit.service;

import com.unbmy.recruit.pojo.Notice;

import java.util.List;

/**
 * @author Unbmy
 */
public interface INoticeService {

    List<Notice> getAllNotice();
    List<Notice> getLatestNotice();
    Notice getNoticeById(Long id);

}
