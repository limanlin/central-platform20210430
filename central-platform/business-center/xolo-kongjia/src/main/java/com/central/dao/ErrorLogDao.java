package com.central.dao;

import com.central.model.xolokongjia.ErrorLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ErrorLogDao {
    //新增
    @Insert("insert into xlkj_error_log(operatdate,error_content,operat_content,user_id,user_name) " +
            " values (#{operatDate},#{errorContent},#{operatContent},#{userId},#{userName})")
    int save(ErrorLog errorLog);
    //单个查询
    @Select("select * from xlkj_error_log t where t.id = #{id}")
    ErrorLog findById(Long id);
    //删除
    int deleteById(Long id);
    //按条件查询
    int count(Map<String, Object> params);
    List<ErrorLog> findList(Map<String, Object> params);
}
