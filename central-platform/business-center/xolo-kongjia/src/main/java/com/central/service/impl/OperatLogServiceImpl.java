package com.central.service.impl;

import com.central.dao.OperatLogDao;
import com.central.model.common.PageResult;
import com.central.model.common.utils.PageUtil;
import com.central.model.xolokongjia.OperatLog;
import com.central.service.OperatLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class OperatLogServiceImpl implements OperatLogService {
    @Autowired
    private OperatLogDao operatLogDao;

    @Override
    public void save(OperatLog operatLog) {
        operatLogDao.save(operatLog);
        //log.info("新增操作日志信息：{}", operatLog);
    }

    @Override
    public void delete(Long id) {
        OperatLog operatLog=operatLogDao.findById(id);
        operatLogDao.deleteById(id);
        //log.info("删除操作日志信息：{}", operatLog);
    }

    @Override
    public OperatLog findById(Long id) {
        return operatLogDao.findById(id);
    }

    @Override
    public PageResult<OperatLog> findOperatLogInfo(Map<String, Object> params) {
        int total =operatLogDao.count(params);
        List<OperatLog> list = Collections.emptyList();
        if (total > 0) {
            PageUtil.pageParamConver(params, true);
            list=operatLogDao.findList(params);
        }
        return PageResult.<OperatLog>builder().data(list).code(0).count((long)total).build();
    }
}
