package com.unbmy.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.unbmy.recruit.mapper.NoticeMapper;
import com.unbmy.recruit.pojo.Notice;
import com.unbmy.recruit.service.INoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Unbmy
 */
@Service("noticeService")
public class NoticeServiceImpl implements INoticeService {
    @Resource
    private NoticeMapper noticeMapper;

    @Override
    public List<Notice> getAllNotice() {
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("time");
        return noticeMapper.selectList(queryWrapper);
    }

    @Override
    public List<Notice> getLatestNotice() {
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("time");
        queryWrapper.last("limit 3");
        return noticeMapper.selectList(queryWrapper);
    }

    @Override
    public Notice getNoticeById(Long id) {
        return noticeMapper.selectById(id);
    }

    @Override
    public int updateNotice(Notice notice) {
        return noticeMapper.updateById(notice);
    }

    @Override
    public int deleteNotice(Long id) {
        return noticeMapper.deleteById(id);
    }

    @Override
    public int addNotice(String topic, String content, Date date) {
        return noticeMapper.insert(new Notice(topic, content, date));
    }
}
