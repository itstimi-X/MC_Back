package com.mini.mbti_collector.service;

import com.mini.mbti_collector.dto.MbtiDto;
import com.mini.mbti_collector.dto.MbtiDto.Response;

public interface MbtiService {
    public void saveMbtiResult(String authorizationHeader, MbtiDto.Request request) throws Exception;

    Response getLatestMbtiResult(String authorizationHeader) throws Exception;
}
