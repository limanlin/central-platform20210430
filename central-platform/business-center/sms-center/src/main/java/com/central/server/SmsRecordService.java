package com.central.server;

import com.central.model.common.PageResult;
import com.central.model.common.utils.JsonResult;
import com.central.model.sms.SmsRecord;
import com.central.model.sms.SmsSendReport;

import java.util.Map;

public interface SmsRecordService {
    void save(SmsRecord smsRecord);
    void update(SmsRecord smsRecord);
    void delete(Long id);
    SmsRecord findById(Long id);
    PageResult<SmsRecord> findSmsRecords(Map<String, Object> params);
    JsonResult SendSMM(String mobile, String smsContent, String ipAddress, String userName,long timeStamp,String sign, int isAbroad);
}
