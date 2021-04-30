package com.central.user.dao;

import com.central.model.user.SysDictionary;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysDictionaryDao {
    @Insert("insert into sys_dictionary(valueCode,valueName,typeCode,typeName,sortNum) "
            + "values(#{valueCode},#{valueName}, #{typeCode}, #{typeName}, #{sortNum})")
    int save(SysDictionary dictionary);

    int update(SysDictionary dictionary);

    @Delete("delete from sys_dictionary where id = #{id}")
    int deleteById(Long id);

    @Select("select * from sys_dictionary t where t.id = #{id}")
    SysDictionary findById(Long id);

    int count(Map<String, Object> params);

    List<SysDictionary> findList(Map<String, Object> params);

    @Select("select * from sys_dictionary t where t.typeCode = #{typeCode}")
    List<SysDictionary> findByCodeList(String dictionaryCode);
}
