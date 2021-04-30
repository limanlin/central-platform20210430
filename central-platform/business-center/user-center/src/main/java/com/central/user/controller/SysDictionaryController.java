package com.central.user.controller;

import com.central.model.common.PageResult;
import com.central.model.common.Result;
import com.central.model.user.SysDictionary;
import com.central.user.service.SysDictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Api(tags = "数据字典api")
@RequestMapping("/SysDictionarys")
public class SysDictionaryController {
    @Autowired
    private SysDictionaryService sysDictionaryService;
    /**
     * 添加数据字典 或者 更新
     * @param sysDictionary
     * @return
     */
    @PreAuthorize("hasAnyAuthority('sysDictionary:post/sysDictionarys','sysDictionary:put/sysDictionarys')")
    @ApiOperation(value = "新增或修改数据字典")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody SysDictionary sysDictionary) {

        try{
            if (sysDictionary.getId() != null){
                sysDictionaryService.update(sysDictionary);
            }else {
                sysDictionaryService.save(sysDictionary);
            }
            return Result.succeed("操作成功");
        }catch (Exception ex){
            ex.printStackTrace();
            return Result.failed("操作失败");
        }

    }
    //删除数据字典
    @PreAuthorize("hasAuthority('sysDictionary:delete/sysDictionarys/{id}')")
    @ApiOperation(value = "删除菜单")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {

        try {
            sysDictionaryService.delete(id);
            return Result.succeed("操作成功");
        }catch (Exception ex){
            ex.printStackTrace();
            return Result.failed("操作失败");
        }

    }
    //按数据字典值查询
    @PreAuthorize("hasAuthority('sysDictionary:get/sysDictionarys')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit",value = "分页结束位置", required = true, dataType = "Integer")
    })
    @ApiOperation(value = "sysDictionarys")
    @GetMapping("/sysDictionarys")
    public PageResult<SysDictionary> sysDictionaryList(@RequestParam Map<String, Object> params){
        return sysDictionaryService.findSysDictionarys(params);
    }
}
