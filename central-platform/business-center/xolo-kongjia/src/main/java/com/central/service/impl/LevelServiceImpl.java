package com.central.service.impl;

import com.central.dao.LevelDao;
import com.central.model.common.PageResult;
import com.central.model.common.utils.PageUtil;
import com.central.model.xolokongjia.Level;
import com.central.service.LevelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
@Slf4j
@Service
public class LevelServiceImpl implements LevelService {
    @Autowired
    private LevelDao levelDao;
    @Override
    public void save(Level level) {
        levelDao.save(level);
        //log.info("新增级别：{}", level);
    }
    @Override
    public void update(Level level) {
        levelDao.update(level);
        //log.info("修改级别：{}", level);
    }
    @Override
    public void delete(Long id) {
        Level level=levelDao.findById(id);
        levelDao.deleteById(id);
        //log.info("删除级别：{}", level);
    }

    @Override
    public Level findById(Long id) {
        return levelDao.findById(id);
    }

    @Override
    public PageResult<Level> findLevelInfo(Map<String, Object> params) {
        int total =levelDao.count(params);
        List<Level> list = Collections.emptyList();
        if (total > 0) {
            PageUtil.pageParamConver(params, true);
            list=levelDao.findList(params);
        }
        return PageResult.<Level>builder().data(list).code(0).count((long)total).build();
    }
}
