package com.central.server;

import com.central.model.common.PageResult;
import com.central.model.common.Result;
import com.central.model.product.Product;

import java.util.Map;

public interface ProductService {
    /**
     * 添加产品
     * @param product
     */
    void save(Product product);
    /**
     * 修改产品
     * @param product
     */
    void update(Product product);
    /**
     * 删除产品
     * @param id
     */
    void delete(Long id);
    /**
     * 获取产品对象
     * @param id
     * @return
     */
    Product findById(Long id);
    /**
     * 产品列表
     * @param params
     * @return
     */
    PageResult<Product> findProducts(Map<String, Object> params);

    String findProductsList(int page, int limit);

    /**
     * 是否上架状态变更
     * @param params
     * @return
     */
    Result updateIsUse(Map<String, Object> params);

}
