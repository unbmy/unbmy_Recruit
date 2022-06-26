package com.unbmy.recruit.service;

import com.unbmy.recruit.pojo.Request;

import java.util.List;

/**
 * @author Unbmy
 */
public interface IRequestService {

    List<Request> getAllRequest();
    Request getRequestById(Long id);
    int addRequest(Request request);
    int deleteRequest(Long id);

}
