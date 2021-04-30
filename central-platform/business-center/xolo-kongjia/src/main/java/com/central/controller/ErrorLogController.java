package com.central.controller;

import com.central.model.common.PageResult;
import com.central.model.common.Result;
import com.central.model.xolokongjia.ErrorLog;
import com.central.service.ErrorLogService;
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
@Api(tags = "代码报错日志api")
public class ErrorLogController {
    @Autowired
    private ErrorLogService errorLogService;
    //获取代码报错日志
    @PreAuthorize("hasAuthority('errorLog:get/errorLogs')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit",value = "分页结束位置", required = true, dataType = "Integer")
    })
    @ApiOperation(value = "errorLogs")
    @GetMapping("/errorLogs")
    public PageResult<ErrorLog> findErrorLogInfoList(@RequestParam Map<String, Object> params){
        return errorLogService.findErrorLogInfo(params);
    }
    /**
     * 新增代码报错信息
     * @return
     */
    @PostMapping("/errorLogs/save")
    @PreAuthorize("hasAnyAuthority('errorLog:post/errorLogs/save')")
    public Result save(@RequestBody ErrorLog errorLog) {
        try{
            errorLogService.save(errorLog);
            return Result.succeed("操作成功");
        }catch (Exception ex){
            ex.printStackTrace();
            return Result.failed("操作失败");
        }
    }
    /**
     * 删除代码报错信息
     * @param id
     */
    @PreAuthorize("hasAuthority('errorLog:delete/errorLogs/{id}')")
    @ApiOperation(value = "删除代码报错信息")
    @DeleteMapping("/errorLogs/{id}")
    public Result delete(@PathVariable Long id) {

        try {
            errorLogService.delete(id);
            return Result.succeed("操作成功");
        }catch (Exception ex){
            ex.printStackTrace();
            return Result.failed("操作失败");
        }

    }
}
