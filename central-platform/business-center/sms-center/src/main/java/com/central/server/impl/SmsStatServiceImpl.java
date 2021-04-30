package com.central.server.impl;

import com.central.dao.SmsStatDao;
import com.central.model.common.PageResult;
import com.central.model.common.utils.PageUtil;
import com.central.model.sms.SmsStat;
import com.central.server.SmsStatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class SmsStatServiceImpl implements SmsStatService {
    @Autowired
    private SmsStatDao smsStatDao;
    @Override
    public void save(SmsStat smsStat) {
        smsStat.setStatDate(new Date());
        smsStatDao.save(smsStat);
        log.info("新增短信统计：{}", smsStat);
    }

    @Override
    public void update(SmsStat smsStat) {
        smsStatDao.update(smsStat);
        log.info("更新短信统计：{}", smsStat);
    }

    @Override
    public void delete(Long id) {
        SmsStat smsStat=smsStatDao.findById(id);
        smsStatDao.deleteById(id);
        log.info("删除短信统计：{}", smsStat);
    }

    @Override
    public SmsStat findById(Long id) {
        return smsStatDao.findById(id);
    }

    @Override
    public PageResult<SmsStat> findSmsStats(Map<String, Object> params) {
        int total =smsStatDao.count(params);
        List<SmsStat> list = Collections.emptyList();
        if (total > 0) {
            PageUtil.pageParamConver(params, true);
            list=smsStatDao.findList(params);
        }
        return PageResult.<SmsStat>builder().data(list).code(0).count((long)total).build();
    }
}
