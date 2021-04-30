package com.central.server;

import com.central.model.common.PageResult;
import com.central.model.sms.SmsAlarm;

import java.util.Map;

public interface SmsAlarmService {
    void save(SmsAlarm smsAlarm);
    void update(SmsAlarm smsAlarm);
    void delete(Long id);
    SmsAlarm findById(Long id);
    PageResult<SmsAlarm> findSmsAlarmUsers(Map<String, Object> params);
}
