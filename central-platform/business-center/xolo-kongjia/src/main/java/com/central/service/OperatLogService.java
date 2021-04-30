package com.central.service;

import com.central.model.common.PageResult;
import com.central.model.xolokongjia.OperatLog;

import java.util.Map;

public interface OperatLogService {
    void save(OperatLog operatLog);
    void delete(Long id);
    OperatLog findById(Long id);
    PageResult<OperatLog> findOperatLogInfo(Map<String, Object> params);
}
