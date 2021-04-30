package com.central.server.impl;

import com.central.dao.SmsRequestDao;
import com.central.model.common.PageResult;
import com.central.model.common.utils.PageUtil;
import com.central.model.sms.SmsRequest;
import com.central.server.SmsRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class smsRequestServiceImpl implements SmsRequestService {
    @Autowired
    private SmsRequestDao smsRequestDao;
    @Override
    public void save(SmsRequest smsRequest) {
        smsRequest.setSendTime(new Date());
        smsRequestDao.save(smsRequest);
        log.info("新增请求发送短信记录：{}", smsRequest);
    }

    @Override
    public void delete(Long id) {
        SmsRequest smsRequest=smsRequestDao.findById(id);
        smsRequestDao.deleteById(id);
        log.info("删除短信请求记录：{}", smsRequest);
    }

    @Override
    public SmsRequest findById(Long id) {
        return smsRequestDao.findById(id);
    }

    @Override
    public PageResult<SmsRequest> findSmsRequests(Map<String, Object> params) {
        int total =smsRequestDao.count(params);
        List<SmsRequest> list = Collections.emptyList();
        if (total > 0) {
            PageUtil.pageParamConver(params, true);
            list=smsRequestDao.findList(params);
        }
        return PageResult.<SmsRequest>builder().data(list).code(0).count((long)total).build();
    }
}
