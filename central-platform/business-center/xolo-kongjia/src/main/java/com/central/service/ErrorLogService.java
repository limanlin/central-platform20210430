package com.central.service;

import com.central.model.common.PageResult;
import com.central.model.xolokongjia.ErrorLog;

import java.util.Map;

public interface ErrorLogService {
    void save(ErrorLog errorLog);
    void delete(Long id);
    ErrorLog findById(Long id);
    PageResult<ErrorLog> findErrorLogInfo(Map<String, Object> params);
}
