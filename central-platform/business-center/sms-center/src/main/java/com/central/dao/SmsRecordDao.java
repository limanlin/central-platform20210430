package com.central.dao;

import com.central.model.sms.SmsRecord;
import com.central.model.sms.SmsSendReport;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface SmsRecordDao {
    //新增
    @Insert("insert into sms_record(userCode,sysName,phone,content,message,status,sendTime,smsType,ipAddress)"
            +" values (#{userCode},#{sysName},#{phone},#{content},#{message},#{status},#{sendTime},#{smsType},#{ipAddress})")
    int save(SmsRecord smsRecord);
    //修改
    int update(SmsRecord smsRecord);
    @Delete("delete from sms_record where id = #{id}")
        //删除
    int deleteById(Long id);
    //单个查询
    @Select("select * from sms_record t where t.id = #{id}")
    SmsRecord findById(Long id);
    //按条件查询
    int count(Map<String, Object> params);
    List<SmsRecord> findList(Map<String, Object> params);
}
