package com.unbmy.recruit.service.impl;

import com.unbmy.recruit.mapper.RequestMapper;
import com.unbmy.recruit.pojo.Request;
import com.unbmy.recruit.service.IRequestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Unbmy
 */
@Service("requestService")
public class RequestServiceImpl implements IRequestService {
    @Resource
    private RequestMapper requestMapper;

    @Override
    public List<Request> getAllRequest() {
        return requestMapper.selectList(null);
    }

    @Override
    public Request getRequestById(Long id) {
        return requestMapper.selectById(id);
    }

    @Override
    public int addRequest(Request request) {
        return requestMapper.insert(request);
    }

    @Override
    public int deleteRequest(Long id) {
        return requestMapper.deleteById(id);
    }
}
