package com.mini.mbti_collector.service;

import com.mini.mbti_collector.dto.MbtiDto;
import com.mini.mbti_collector.dto.MbtiDto.Response;
import java.util.List;

public interface MbtiService {
    public void saveMbtiResult(String authorizationHeader, MbtiDto.Request request) throws Exception;

    Response getLatestMbtiResult(String authorizationHeader) throws Exception;

    List<Response> getRadarChartData(String authorizationHeader) throws Exception;
}
