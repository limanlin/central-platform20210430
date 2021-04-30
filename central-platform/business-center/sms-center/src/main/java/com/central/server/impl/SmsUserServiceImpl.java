package com.central.server.impl;

import com.central.dao.SmsUserDao;
import com.central.model.common.PageResult;
import com.central.model.common.utils.PageUtil;
import com.central.model.sms.SmsUser;
import com.central.server.SmsUserService;
import com.central.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class SmsUserServiceImpl implements SmsUserService {
    @Autowired
    private SmsUserDao smsUserDao;

    @Override
    public void save(SmsUser smsUser) {
        smsUser.setCreateTime(new Date());
        //自动生成系统编码
        String userCode= MD5Util.getMD5(smsUser.getUserName());
        smsUser.setUserCode(userCode);
        smsUserDao.save(smsUser);
        log.info("新增系统用户：{}", smsUser);
    }

    @Override
    public void update(SmsUser smsUser) {
        smsUser.setUpdateTime(new Date());;
        smsUserDao.update(smsUser);
        log.info("修改系统用户：{}", smsUser);
    }

    @Override
    public void delete(Long id) {
        SmsUser smsUser=smsUserDao.findById(id);
        smsUserDao.deleteById(id);
        log.info("删除系统用户：{}", smsUser);
    }

    @Override
    public SmsUser findById(Long id) {
        return smsUserDao.findById(id);
    }

    @Override
    public PageResult<SmsUser> findSmsUsers(Map<String, Object> params) {
        int total =smsUserDao.count(params);
        List<SmsUser> list = Collections.emptyList();
        if (total > 0) {
            PageUtil.pageParamConver(params, true);
            list=smsUserDao.findList(params);
        }
        return PageResult.<SmsUser>builder().data(list).code(0).count((long)total).build();
    }
    @Override
    public SmsUser findByCode(String userCode) {
        return smsUserDao.findByCode(userCode);
    }
}
