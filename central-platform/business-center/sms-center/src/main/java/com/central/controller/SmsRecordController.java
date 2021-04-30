package com.central.controller;

import com.central.model.common.PageResult;
import com.central.model.common.Result;
import com.central.model.common.utils.JsonResult;
import com.central.model.sms.SmsRecord;
import com.central.server.SmsRecordService;
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
@Api(tags = "短信系统短信记录模块api")
public class SmsRecordController {
    @Autowired
    private SmsRecordService smsRecordService;
    //获取短信系统短信记录列表,含查询
    @PreAuthorize("hasAuthority('smsRecord:get/smsRecords')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit",value = "分页结束位置", required = true, dataType = "Integer")
    })
    @ApiOperation(value = "smsRecords")
    @GetMapping("/smsRecords")
    public PageResult<SmsRecord> smsRecordsList(@RequestParam Map<String, Object> params){
        return smsRecordService.findSmsRecords(params);
    }
    /**
     * 新增or修改短信系统短信记录信息
     * @param smsRecord
     * @return
     */
    @PostMapping("/smsRecords/saveOrUpdate")
    @PreAuthorize("hasAnyAuthority('smsRecord:post/smsRecords/saveOrUpdate')")
    public Result saveOrUpdate(@RequestBody SmsRecord smsRecord) {
        try{
            if (smsRecord.getId() != null){
                smsRecordService.update(smsRecord);
            }else {
                smsRecordService.save(smsRecord);
            }
            return Result.succeed("操作成功");
        }catch (Exception ex){
            ex.printStackTrace();
            return Result.failed("操作失败");
        }
    }
    /**
     * 删除短信系统短信记录
     * @param id
     */
    @PreAuthorize("hasAuthority('smsRecord:delete/smsRecords/{id}')")
    @ApiOperation(value = "删除短信记录")
    @DeleteMapping("/smsRecords/{id}")
    public Result delete(@PathVariable Long id) {

        try {
            smsRecordService.delete(id);
            return Result.succeed("操作成功");
        }catch (Exception ex){
            ex.printStackTrace();
            return Result.failed("操作失败");
        }

    }
    /**
     * 短信发送
     * @param  mobile 手机号,smsContent 短信内容，sysCode 系统编码
     * */
    @PostMapping("/smsSend")
    public JsonResult SendSMM(String mobile, String smsContent, String ipAddress, String userName,long timeStamp,String sign, int isAbroad){
        return smsRecordService.SendSMM(mobile,smsContent,ipAddress,userName,timeStamp,sign,isAbroad);
    }
}
