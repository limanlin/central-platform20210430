package com.central.service.impl;

import com.central.dao.BrandDao;
import com.central.model.common.PageResult;
import com.central.model.common.utils.PageUtil;
import com.central.model.xolokongjia.Brand;
import com.central.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandDao brandDao;
    @Override
    public void save(Brand brand) {
        brandDao.save(brand);
        //log.info("新增品牌：{}", brand);
    }
    @Override
    public void update(Brand brand) {
        brandDao.update(brand);
        //log.info("修改品牌：{}", brand);
    }
    @Override
    public void delete(Long id) {
        Brand brand=brandDao.findById(id);
        brandDao.deleteById(id);
        //log.info("删除品牌：{}", brand);
    }

    @Override
    public Brand findById(Long id) {
        return brandDao.findById(id);
    }

    @Override
    public PageResult<Brand> findBrandInfo(Map<String, Object> params) {
        int total =brandDao.count(params);
        List<Brand> list = Collections.emptyList();
        if (total > 0) {
            PageUtil.pageParamConver(params, true);
            list=brandDao.findList(params);
        }
        return PageResult.<Brand>builder().data(list).code(0).count((long)total).build();
    }
}
