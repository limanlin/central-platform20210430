package com.central.controller;

import com.central.model.common.PageResult;
import com.central.model.common.Result;
import com.central.model.xolokongjia.Brand;
import com.central.service.BrandService;
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
@Api(tags = "品牌api")
public class BrandController {
    @Autowired
    private BrandService brandService;
    //获取品牌列表,含查询
    @PreAuthorize("hasAuthority('brand:get/brands')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit",value = "分页结束位置", required = true, dataType = "Integer")
    })
    @ApiOperation(value = "brands")
    @GetMapping("/brands")
    public PageResult<Brand> findLevelInfoList(@RequestParam Map<String, Object> params){
        return brandService.findBrandInfo(params);
    }
    /**
     * 新增or修改品牌
     * @param brand
     * @return
     */
    @PostMapping("/brands/saveOrUpdate")
    @PreAuthorize("hasAnyAuthority('brand:post/brands/saveOrUpdate')")
    public Result saveOrUpdate(@RequestBody Brand brand) {
        try{
            if (brand.getId() != null){
                brandService.update(brand);
            }else {
                brandService.save(brand);
            }
            return Result.succeed("操作成功");
        }catch (Exception ex){
            ex.printStackTrace();
            return Result.failed("操作失败");
        }
    }
    /**
     * 删除品牌
     * @param id
     */
    @PreAuthorize("hasAuthority('brand:delete/brands/{id}')")
    @ApiOperation(value = "删除品牌")
    @DeleteMapping("/brands/{id}")
    public Result delete(@PathVariable Long id) {

        try {
            brandService.delete(id);
            return Result.succeed("操作成功");
        }catch (Exception ex){
            ex.printStackTrace();
            return Result.failed("操作失败");
        }

    }
}
