package com.central.server;

import com.central.model.common.PageResult;
import com.central.model.sms.SmsUser;

import java.util.Map;

public interface SmsUserService  {
    void save(SmsUser smsUser);
    void update(SmsUser smsUser);
    void delete(Long id);
    SmsUser findById(Long id);
    PageResult<SmsUser> findSmsUsers(Map<String, Object> params);
    SmsUser findByCode(String userCode);
}
