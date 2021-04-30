package com.central.dao;

import com.central.model.xolokongjia.OperatLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface OperatLogDao {
    //新增
    @Insert("insert into xlkj_operat_log(operatdate,user_id,user_name,operat_content,interface_name,interface_param,result) " +
            " values (#{operatDate},#{userId},#{userName},#{operatContent},#{interfaceName},#{interfaceParam},#{result})")
    int save(OperatLog operatLog);
    //单个查询
    @Select("select * from xlkj_operat_log t where t.id = #{id}")
    OperatLog findById(Long id);
    //删除
    int deleteById(Long id);
    //按条件查询
    int count(Map<String, Object> params);
    List<OperatLog> findList(Map<String, Object> params);
}
