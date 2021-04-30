package com.central.controller;

import com.central.model.common.PageResult;
import com.central.model.common.Result;
import com.central.model.sms.SmsRequest;
import com.central.server.SmsRequestService;
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
@Api(tags = "短信请求记录模块api")
public class smsRequestController {
    @Autowired
    private SmsRequestService smsRequestService;
    //获取短信系统请求发送短信记录列表,含查询
    @PreAuthorize("hasAuthority('smsRequest:get/smsRequests')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit",value = "分页结束位置", required = true, dataType = "Integer")
    })
    @ApiOperation(value = "Requests")
    @GetMapping("/smsRequests")
    public PageResult<SmsRequest> smsRequestsList(@RequestParam Map<String, Object> params){
        return smsRequestService.findSmsRequests(params);
    }
    /**
     * 新增
     * @param smsRequest
     * @return
     */
    @PostMapping("/smsRequests/saveOrUpdate")
    @PreAuthorize("hasAnyAuthority('smsRequest:post/smsRequests/saveOrUpdate')")
    public Result saveOrUpdate(@RequestBody SmsRequest smsRequest) {
        try{
            if (smsRequest.getId() == null){
                smsRequestService.save(smsRequest);
            }
            return Result.succeed("操作成功");
        }catch (Exception ex){
            ex.printStackTrace();
            return Result.failed("操作失败");
        }
    }
    /**
     * 删除短信系统短信请求记录
     * @param id
     */
    @PreAuthorize("hasAuthority('smsRequest:delete/smsRequests/{id}')")
    @ApiOperation(value = "删除请求记录")
    @DeleteMapping("/smsRequests/{id}")
    public Result delete(@PathVariable Long id) {

        try {
            smsRequestService.delete(id);
            return Result.succeed("操作成功");
        }catch (Exception ex){
            ex.printStackTrace();
            return Result.failed("操作失败");
        }

    }
}
