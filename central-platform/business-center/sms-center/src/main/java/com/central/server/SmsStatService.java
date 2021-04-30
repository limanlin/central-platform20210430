package com.central.server;

import com.central.model.common.PageResult;
import com.central.model.sms.SmsStat;

import java.util.Map;

public interface SmsStatService {
    void save(SmsStat smsStat);
    void update(SmsStat smsStat);
    void delete(Long id);
    SmsStat findById(Long id);
    PageResult<SmsStat> findSmsStats(Map<String, Object> params);
}
