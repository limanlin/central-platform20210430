package com.central.dao;

import com.central.model.sms.SmsStat;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface SmsStatDao {
    //新增
    @Insert("insert into sms_stat(sysName,userCode,statDate,totalNum,alarmTime)"
            +" values (#{sysName},#{userCode},#{statDate},#{totalNum},#{alarmTime})")
    int save(SmsStat smsStat);
    //修改
    int update(SmsStat smsStat);
    @Delete("delete from sms_stat where id = #{id}")
        //删除
    int deleteById(Long id);
    //单个查询
    @Select("select * from sms_stat t where t.id = #{id}")
    SmsStat findById(Long id);
    //通过userCode和statDate查找当天的数据
    @Select("select * from sms_stat t where t.userCode = #{userCode} and date(t.statDate)=curdate()")
    SmsStat findByCode(String userCode);
    //按条件查询
    int count(Map<String, Object> params);
    List<SmsStat> findList(Map<String, Object> params);
}
