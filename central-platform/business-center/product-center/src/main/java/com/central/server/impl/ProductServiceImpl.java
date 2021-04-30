package com.central.server.impl;

import com.alibaba.fastjson.JSON;
import com.central.dao.ProductDao;
import com.central.model.common.PageResult;
import com.central.model.common.Result;
import com.central.model.common.utils.PageUtil;
import com.central.model.product.Product;
import com.central.server.ProductService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public void save(Product product) {
        product.setCreateTime(new Date());
        productDao.save(product);
        log.info("新增产品：{}", product);
    }

    @Override
    public void update(Product product) {
        product.setUpdateTime(new Date());;
        productDao.update(product);
        log.info("修改产品：{}", product);
    }

    @Override
    public void delete(Long id) {
        Product product=productDao.findById(id);
        productDao.deleteById(id);
        log.info("删除产品：{}", product);
    }

    @Override
    public Product findById(Long id) {
        return productDao.findById(id);
    }

    @Override
    public PageResult<Product> findProducts(Map<String, Object> params) {
        int total =productDao.count(params);
        List<Product> list = Collections.emptyList();
        if (total > 0) {
            PageUtil.pageParamConver(params, true);
            list=productDao.findList(params);
        }
        return PageResult.<Product>builder().data(list).code(0).count((long)total).build();
    }

    @Override
    public String findProductsList(int page,int limit) {
        String JsonStr="";
        Map<String, Object> params=new HashMap();
        params.put("page",page);
        params.put("limit",limit);
        //params.put("access_token",access_token);
        int total =productDao.count(params);
        List<Product> list = Collections.emptyList();
        if (total > 0) {
            PageUtil.pageParamConver(params, true);
            list=productDao.findList(params);
            JsonStr= JSON.toJSONString(list);
        }
        return JsonStr;
    }
    @Override
    public Result updateIsUse(Map<String, Object> params) {
        Long id = MapUtils.getLong(params, "id");
        int isUse = MapUtils.getInteger(params, "isUse");
        Product product=productDao.findById(id);
        if(product==null){
            throw new IllegalArgumentException("产品不存在");
        }
        product.setIsUse(isUse);
        int i=productDao.update(product);
        log.info("修改产品是否可用：{}", product);
        return i > 0 ? Result.succeed(product, "更新成功") : Result.failed("更新失败");
    }
}
