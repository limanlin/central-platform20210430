package com.central.dao;

import com.central.model.sms.SmsRequest;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface SmsRequestDao {
    //新增
    @Insert("insert into sms_request(userName,phone,content,sendTime,ipAddress)"
            +" values (#{userName},#{phone},#{content},#{sendTime},#{ipAddress})")
    int save(SmsRequest smsRecord);
    @Delete("delete from sms_request where id = #{id}")
        //删除
    int deleteById(Long id);
    //单个查询
    @Select("select * from sms_request t where t.id = #{id}")
    SmsRequest findById(Long id);
    //按条件查询
    int count(Map<String, Object> params);
    List<SmsRequest> findList(Map<String, Object> params);
}
