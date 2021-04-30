package com.central.dao;

import com.central.model.sms.SmsUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface SmsUserDao {
    //新增
    @Insert("insert into sms_user(sysName,userCode,userName,password,productid,xh,enabled,remark,createTime,daySendNum,ipDaySendNum,telDaySendNum,suffix,alarmNum,intervalTime)"
            +" values (#{sysName},#{userCode},#{userName},#{password},#{productid},#{xh},#{enabled},#{remark},#{createTime},#{daySendNum},#{ipDaySendNum},#{telDaySendNum},#{suffix},#{alarmNum},#{intervalTime})")
    int save(SmsUser smsUser);
    //修改
    int update(SmsUser smsUser);
    @Delete("delete from sms_user where id = #{id}")
    //删除
    int deleteById(Long id);
    //单个查询
    @Select("select * from sms_user t where t.id = #{id}")
    SmsUser findById(Long id);
    //按系统编码查询
    @Select("select * from sms_user t where t.userCode = #{userCode}")
    SmsUser findByCode(String userCode);

    //按用户名查询
    @Select("select * from sms_user t where t.userName = #{userName}")
    SmsUser findByName(String userName);

    //按条件查询
    int count(Map<String, Object> params);
    List<SmsUser> findList(Map<String, Object> params);

}
