package com.central.server;

import com.central.model.common.PageResult;
import com.central.model.sms.SmsSendReport;

import java.util.Map;

public interface SmsSendReportService {
    PageResult<SmsSendReport> smsReportList(Map<String, Object> params);
}
