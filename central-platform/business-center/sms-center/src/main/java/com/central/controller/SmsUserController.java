package com.central.controller;

import com.central.model.common.PageResult;
import com.central.model.common.Result;
import com.central.model.sms.SmsUser;
import com.central.server.SmsUserService;
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
@Api(tags = "短信系统用户模块api")
public class SmsUserController {
    @Autowired
    private SmsUserService smsUserService;
    //获取系统用户列表,含查询
    @PreAuthorize("hasAuthority('smsUser:get/smsUsers')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit",value = "分页结束位置", required = true, dataType = "Integer")
    })
    @ApiOperation(value = "smsUsers")
    @GetMapping("/smsUsers")
    public PageResult<SmsUser> smsUserList(@RequestParam Map<String, Object> params){
        return smsUserService.findSmsUsers(params);
    }
    /**
     * 新增or修改系统用户信息
     * @param smsUser
     * @return
     */
    @PostMapping("/smsUsers/saveOrUpdate")
    @PreAuthorize("hasAnyAuthority('smsUser:post/smsUsers/saveOrUpdate')")
    public Result saveOrUpdate(@RequestBody SmsUser smsUser) {
        try{
            if (smsUser.getId() != null){
                smsUserService.update(smsUser);
            }else {
                smsUserService.save(smsUser);
            }
            return Result.succeed("操作成功");
        }catch (Exception ex){
            ex.printStackTrace();
            return Result.failed("操作失败");
        }
    }
    /**
     * 删除短信系统用户信息
     * @param id
     */
    @PreAuthorize("hasAuthority('smsUser:delete/smsUsers/{id}')")
    @ApiOperation(value = "删除产品")
    @DeleteMapping("/smsUsers/{id}")
    public Result delete(@PathVariable Long id) {

        try {
            smsUserService.delete(id);
            return Result.succeed("操作成功");
        }catch (Exception ex){
            ex.printStackTrace();
            return Result.failed("操作失败");
        }
    }
}
