package com.central.server.impl;

import com.central.dao.SmsSendReportDao;
import com.central.model.common.PageResult;
import com.central.model.common.utils.PageUtil;
import com.central.model.sms.SmsSendReport;
import com.central.server.SmsSendReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class SmsSendReportServiceImpl implements SmsSendReportService {
    @Autowired
    private SmsSendReportDao smsSendReportDao;
    @Override
    public PageResult<SmsSendReport> smsReportList(Map<String, Object> params) {
        int total =smsSendReportDao.count(params);
        List<SmsSendReport> list = Collections.emptyList();
        if (total > 0) {
            PageUtil.pageParamConver(params, true);
            list=smsSendReportDao.smsReportList(params);
        }
        return PageResult.<SmsSendReport>builder().data(list).code(0).count((long)total).build();
    }
}
