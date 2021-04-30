package com.central.dao;

import com.central.model.xolokongjia.Brand;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface BrandDao {
    //新增
    @Insert("insert into xlkj_brand(brand_code,brand_name)"
            +" values (#{brandCode},#{brandName})")
    int save(Brand brand);
    //修改
    int update(Brand brand);
    @Delete("delete from xlkj_brand where id = #{id}")
        //删除
    int deleteById(Long id);
    //单个查询
    @Select("select * from xlkj_brand t where t.id = #{id}")
    Brand findById(Long id);
    //按条件查询
    int count(Map<String, Object> params);
    List<Brand> findList(Map<String, Object> params);
}
