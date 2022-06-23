package com.unbmy.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.unbmy.recruit.mapper.NoticeMapper;
import com.unbmy.recruit.pojo.Notice;
import com.unbmy.recruit.service.INoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
        return noticeMapper.selectList(null);
    }

    @Override
    public List<Notice> getLatestNotice() {
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("time");
        queryWrapper.last("limit 5");
        return noticeMapper.selectList(queryWrapper);
    }

    @Override
    public Notice getNoticeById(Long id) {
        return noticeMapper.selectById(id);
    }
}
