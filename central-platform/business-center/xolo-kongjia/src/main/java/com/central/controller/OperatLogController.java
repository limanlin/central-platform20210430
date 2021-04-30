package com.central.controller;

import com.central.model.common.PageResult;
import com.central.model.common.Result;
import com.central.model.xolokongjia.OperatLog;
import com.central.service.OperatLogService;
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
@Api(tags = "操作日志api")
public class OperatLogController {
    @Autowired
    private OperatLogService operatLogService;
    //获取操作日志
    @PreAuthorize("hasAuthority('operatLog:get/operatLogs')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit",value = "分页结束位置", required = true, dataType = "Integer")
    })
    @ApiOperation(value = "operatLogs")
    @GetMapping("/operatLogs")
    public PageResult<OperatLog> findOperatLogInfoList(@RequestParam Map<String, Object> params){
        return operatLogService.findOperatLogInfo(params);
    }
    /**
     * 新增代码报错信息
     * @return
     */
    @PostMapping("/operatLogs/save")
    @PreAuthorize("hasAnyAuthority('operatLog:post/operatLogs/save')")
    public Result save(@RequestBody OperatLog operatLog) {
        try{
            operatLogService.save(operatLog);
            return Result.succeed("操作成功");
        }catch (Exception ex){
            ex.printStackTrace();
            return Result.failed("操作失败");
        }
    }
    /**
     * 删除操作日志
     * @param id
     */
    @PreAuthorize("hasAuthority('operatLog:delete/operatLogs/{id}')")
    @ApiOperation(value = "删除操作日志")
    @DeleteMapping("/operatLogs/{id}")
    public Result delete(@PathVariable Long id) {

        try {
            operatLogService.delete(id);
            return Result.succeed("操作成功");
        }catch (Exception ex){
            ex.printStackTrace();
            return Result.failed("操作失败");
        }

    }
}
