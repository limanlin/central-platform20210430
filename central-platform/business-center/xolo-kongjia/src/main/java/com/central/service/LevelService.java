package com.central.service;

import com.central.model.common.PageResult;
import com.central.model.xolokongjia.Level;

import java.util.Map;

public interface LevelService {
    void save(Level level);
    void update(Level level);
    void delete(Long id);
    Level findById(Long id);
    PageResult<Level> findLevelInfo(Map<String, Object> params);
}
