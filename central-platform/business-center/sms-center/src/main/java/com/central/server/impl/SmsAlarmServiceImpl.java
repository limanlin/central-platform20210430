package com.central.server.impl;

import com.central.dao.SmsAlarmDao;
import com.central.model.common.PageResult;
import com.central.model.common.utils.PageUtil;
import com.central.model.sms.SmsAlarm;
import com.central.server.SmsAlarmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class SmsAlarmServiceImpl implements SmsAlarmService {
    @Autowired
    private SmsAlarmDao smsAlarmDao;

    @Override
    public void save(SmsAlarm smsAlarm) {
        smsAlarmDao.save(smsAlarm);
        log.info("新增报警人：{}", smsAlarm);
    }

    @Override
    public void update(SmsAlarm smsAlarm) {
        smsAlarmDao.update(smsAlarm);
        log.info("修改报警人：{}", smsAlarm);
    }

    @Override
    public void delete(Long id) {
        SmsAlarm smsAlarm=smsAlarmDao.findById(id);
        smsAlarmDao.deleteById(id);
        log.info("删除报警人：{}", smsAlarm);
    }

    @Override
    public SmsAlarm findById(Long id) {
        return smsAlarmDao.findById(id);
    }

    @Override
    public PageResult<SmsAlarm> findSmsAlarmUsers(Map<String, Object> params) {
        int total =smsAlarmDao.count(params);
        List<SmsAlarm> list = Collections.emptyList();
        if (total > 0) {
            PageUtil.pageParamConver(params, true);
            list=smsAlarmDao.findList(params);
        }
        return PageResult.<SmsAlarm>builder().data(list).code(0).count((long)total).build();
    }
}
