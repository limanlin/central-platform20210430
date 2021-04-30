package com.central.service.impl;

import com.central.dao.ErrorLogDao;
import com.central.model.common.PageResult;
import com.central.model.common.utils.PageUtil;
import com.central.model.xolokongjia.ErrorLog;
import com.central.service.ErrorLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
@Slf4j
@Service
public class ErrorLogServiceImpl  implements ErrorLogService {
    @Autowired
    private ErrorLogDao errorLogDao;

    @Override
    public void save(ErrorLog errorLog) {
        errorLogDao.save(errorLog);
        //log.info("新增报错信息：{}", errorLog);
    }

    @Override
    public void delete(Long id) {
        ErrorLog errorLog=errorLogDao.findById(id);
        errorLogDao.deleteById(id);
        //log.info("删除报错信息：{}", errorLog);
    }

    @Override
    public ErrorLog findById(Long id) {
        return errorLogDao.findById(id);
    }

    @Override
    public PageResult<ErrorLog> findErrorLogInfo(Map<String, Object> params) {
        int total =errorLogDao.count(params);
        List<ErrorLog> list = Collections.emptyList();
        if (total > 0) {
            PageUtil.pageParamConver(params, true);
            list=errorLogDao.findList(params);
        }
        return PageResult.<ErrorLog>builder().data(list).code(0).count((long)total).build();
    }
}
