package com.central.controller;

import com.central.model.common.PageResult;
import com.central.model.sms.SmsStat;
import com.central.server.SmsStatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@Api(tags = "短信统计模块api")
public class SmsStatController {
    @Autowired
    private SmsStatService smsStatService;
    //获取系统用户列表,含查询
    @PreAuthorize("hasAuthority('smsStat:get/smsStats')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit",value = "分页结束位置", required = true, dataType = "Integer")
    })
    @ApiOperation(value = "smsStats")
    @GetMapping("/smsStats")
    public PageResult<SmsStat> smsUserList(@RequestParam Map<String, Object> params){
        return smsStatService.findSmsStats(params);
    }
}
