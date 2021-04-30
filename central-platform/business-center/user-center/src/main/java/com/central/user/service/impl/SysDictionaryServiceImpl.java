package com.central.user.service.impl;

import com.central.model.common.PageResult;
import com.central.model.common.utils.PageUtil;
import com.central.model.user.SysDictionary;
import com.central.user.dao.SysDictionaryDao;
import com.central.user.service.SysDictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
@Slf4j
@Service
public class SysDictionaryServiceImpl implements SysDictionaryService {
    @Autowired
    private SysDictionaryDao sysDictionaryDao;
    @Override
    public void save(SysDictionary sysDictionary) {
        sysDictionaryDao.save(sysDictionary);
        log.info("新增数据字典：{}", sysDictionary);
    }

    @Override
    public void update(SysDictionary sysDictionary) {
        sysDictionaryDao.update(sysDictionary);
        log.info("修改数据字典：{}", sysDictionary);
    }

    @Override
    public void delete(Long id) {
        SysDictionary sysDictionary=sysDictionaryDao.findById(id);
        sysDictionaryDao.deleteById(id);
        log.info("删除数据字典：{}", sysDictionary);
    }

    @Override
    public PageResult<SysDictionary> findSysDictionarys(Map<String, Object> params) {
        int total =sysDictionaryDao.count(params);
        List<SysDictionary> list = Collections.emptyList();
        if (total > 0) {
            PageUtil.pageParamConver(params, true);
            list=sysDictionaryDao.findList(params);
        }
        return PageResult.<SysDictionary>builder().data(list).code(0).count((long)total).build();
    }
}
