package com.central.user.service;

import com.central.model.common.PageResult;
import com.central.model.user.SysDictionary;

import java.util.Map;

public interface SysDictionaryService {
    /**
     * 添加产品
     * @param sysDictionary
     */
    void save(SysDictionary sysDictionary);
    /**
     * 修改产品
     * @param sysDictionary
     */
    void update(SysDictionary sysDictionary);
    /**
     * 删除产品
     * @param id
     */
    void delete(Long id);
    /**
     * 产品列表
     * @param params
     * @return
     */
    PageResult<SysDictionary> findSysDictionarys(Map<String, Object> params);
}
