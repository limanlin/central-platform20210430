package com.central.dao;

import com.central.model.sms.SmsSendReport;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface SmsSendReportDao {
    //按条件查询
    int count(Map<String, Object> params);
    //按系统统计短信发送数量
    List<SmsSendReport> smsReportList(Map<String, Object> params);
}
