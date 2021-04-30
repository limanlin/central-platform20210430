package com.central.dao;

import com.central.model.sms.SmsAlarm;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface SmsAlarmDao {
    //新增
    @Insert("insert into sms_alarm(userCode,sysName,phone,operatdate,smsContent,ipAddress,alarmType)"
            +" values (#{userCode},#{sysName},#{phone},#{operatdate},#{smsContent},#{ipAddress},#{alarmType})")
    int save(SmsAlarm smsAlarm);
    //修改
    int update(SmsAlarm smsAlarm);
    @Delete("delete from sms_alarm where id = #{id}")
        //删除
    int deleteById(Long id);
    //单个查询
    @Select("select * from sms_alarm t where t.id = #{id}")
    SmsAlarm findById(Long id);
    //按条件查询
    int count(Map<String, Object> params);
    List<SmsAlarm> findList(Map<String, Object> params);
}
