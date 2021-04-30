package com.central.service;

import com.central.model.common.PageResult;
import com.central.model.xolokongjia.Brand;

import java.util.Map;

public interface BrandService {
    void save(Brand brand);
    void update(Brand brand);
    void delete(Long id);
    Brand findById(Long id);
    PageResult<Brand> findBrandInfo(Map<String, Object> params);
}
