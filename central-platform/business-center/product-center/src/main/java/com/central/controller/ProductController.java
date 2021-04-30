package com.central.controller;

import com.central.model.common.PageResult;
import com.central.model.common.Result;
import com.central.model.product.Product;
import com.central.server.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@Api(tags = "产品模块api")
public class ProductController {
    @Autowired
    private ProductService productService;
    //获取产品列表,含查询
    @PreAuthorize("hasAuthority('product:get/products')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit",value = "分页结束位置", required = true, dataType = "Integer")
    })
    @ApiOperation(value = "products")
    @GetMapping("/products")
    public PageResult<Product> productList(@RequestParam Map<String, Object> params){
        return productService.findProducts(params);
    }

    @GetMapping("/productsList")
    public String productListtest(int page,int limit){
        return productService.findProductsList(page,limit);
    }

    /**
     * 新增or修改产品信息
     * @param product
     * @return
     */
    @PostMapping("/products/saveOrUpdate")
    @PreAuthorize("hasAnyAuthority('product:post/products/saveOrUpdate')")
    public Result saveOrUpdate(@RequestBody Product product) {
        try{
            if (product.getId() != null){
                productService.update(product);
            }else {
                productService.save(product);
            }
            return Result.succeed("操作成功");
        }catch (Exception ex){
            ex.printStackTrace();
            return Result.failed("操作失败");
        }
    }

    /**
     * 删除产品信息
     * @param id
     */
    @PreAuthorize("hasAuthority('product:delete/products/{id}')")
    @ApiOperation(value = "删除产品")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {

        try {
            productService.delete(id);
            return Result.succeed("操作成功");
        }catch (Exception ex){
            ex.printStackTrace();
            return Result.failed("操作失败");
        }

    }
}
