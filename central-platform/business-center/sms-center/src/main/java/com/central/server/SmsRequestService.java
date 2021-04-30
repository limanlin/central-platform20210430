package com.central.server;

import com.central.model.common.PageResult;
import com.central.model.sms.SmsRequest;

import java.util.Map;

public interface SmsRequestService {
    void save(SmsRequest smsRequest);
    void delete(Long id);
    SmsRequest findById(Long id);
    PageResult<SmsRequest> findSmsRequests(Map<String, Object> params);
}
