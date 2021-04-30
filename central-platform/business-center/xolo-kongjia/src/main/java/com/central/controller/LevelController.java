package com.central.controller;

import com.central.model.common.PageResult;
import com.central.model.common.Result;
import com.central.model.xolokongjia.Level;
import com.central.service.LevelService;
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
@Api(tags = "级别api")
public class LevelController {
    @Autowired
    private LevelService levelService;
    //获取级别列表,含查询
    @PreAuthorize("hasAuthority('level:get/levels')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit",value = "分页结束位置", required = true, dataType = "Integer")
    })
    @ApiOperation(value = "levels")
    @GetMapping("/levels")
    public PageResult<Level> findLevelInfoList(@RequestParam Map<String, Object> params){
        return levelService.findLevelInfo(params);
    }
    /**
     * 新增or修改级别信息
     * @param level
     * @return
     */
    @PostMapping("/levels/saveOrUpdate")
    @PreAuthorize("hasAnyAuthority('level:post/levels/saveOrUpdate')")
    public Result saveOrUpdate(@RequestBody Level level) {
        try{
            if (level.getId() != null){
                levelService.update(level);
            }else {
                levelService.save(level);
            }
            return Result.succeed("操作成功");
        }catch (Exception ex){
            ex.printStackTrace();
            return Result.failed("操作失败");
        }
    }
    /**
     * 删除短信系统报警用户信息
     * @param id
     */
    @PreAuthorize("hasAuthority('level:delete/levels/{id}')")
    @ApiOperation(value = "删除报警用户")
    @DeleteMapping("/levels/{id}")
    public Result delete(@PathVariable Long id) {

        try {
            levelService.delete(id);
            return Result.succeed("操作成功");
        }catch (Exception ex){
            ex.printStackTrace();
            return Result.failed("操作失败");
        }

    }
}
