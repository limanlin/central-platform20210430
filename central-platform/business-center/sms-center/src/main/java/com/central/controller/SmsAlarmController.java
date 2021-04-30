package com.central.controller;

import com.central.model.common.PageResult;
import com.central.model.common.Result;
import com.central.model.sms.SmsAlarm;
import com.central.server.SmsAlarmService;
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
@Api(tags = "短信系统报警用户模块api")
public class SmsAlarmController {
    @Autowired
    private SmsAlarmService smsAlarmService;
    //获取短信系统报警用户列表,含查询
    @PreAuthorize("hasAuthority('smsAlarm:get/smsAlarms')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit",value = "分页结束位置", required = true, dataType = "Integer")
    })
    @ApiOperation(value = "smsAlarms")
    @GetMapping("/smsAlarms")
    public PageResult<SmsAlarm> smsAlarmUsersList(@RequestParam Map<String, Object> params){
        return smsAlarmService.findSmsAlarmUsers(params);
    }
    /**
     * 新增or修改短信系统报警用户信息
     * @param smsAlarm
     * @return
     */
    @PostMapping("/smsAlarms/saveOrUpdate")
    @PreAuthorize("hasAnyAuthority('smsAlarm:post/smsAlarms/saveOrUpdate')")
    public Result saveOrUpdate(@RequestBody SmsAlarm smsAlarm) {
        try{
            if (smsAlarm.getId() != null){
                smsAlarmService.update(smsAlarm);
            }else {
                smsAlarmService.save(smsAlarm);
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
    @PreAuthorize("hasAuthority('smsAlarm:delete/smsAlarms/{id}')")
    @ApiOperation(value = "删除报警用户")
    @DeleteMapping("/smsAlarms/{id}")
    public Result delete(@PathVariable Long id) {

        try {
            smsAlarmService.delete(id);
            return Result.succeed("操作成功");
        }catch (Exception ex){
            ex.printStackTrace();
            return Result.failed("操作失败");
        }

    }
}
