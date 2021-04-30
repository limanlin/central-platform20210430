package com.central.dao;

import com.central.model.xolokongjia.Level;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface LevelDao {
    //新增
    @Insert("insert into xlkj_level(level_code,level_name,sort_no)"
            +" values (#{levelCode},#{levelName},#{sortNo})")
    int save(Level level);
    //修改
    int update(Level level);
    @Delete("delete from xlkj_level where id = #{id}")
        //删除
    int deleteById(Long id);
    //单个查询
    @Select("select * from xlkj_level t where t.id = #{id}")
    Level findById(Long id);
    //按条件查询
    int count(Map<String, Object> params);
    List<Level> findList(Map<String, Object> params);
}
