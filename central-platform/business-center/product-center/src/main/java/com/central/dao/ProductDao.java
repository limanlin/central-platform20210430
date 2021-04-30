package com.central.dao;

import com.central.model.product.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductDao {

    @Insert("insert into product(fullName,shortName,brand,productType,productCode,picUrl,minUnit,maxUnit,maxNum,place,days,price,salePrice,weight,createTime,createUserId,isUse) "
            + "values(#{fullName}, #{shortName}, #{brand}, #{productType}, #{productCode}, #{picUrl}, #{minUnit}, #{maxUnit}, #{maxNum}, #{place},#{days},#{price},#{salePrice},#{weight},#{createTime},#{createUserId},#{isUse})")
    int save(Product product);

    int update(Product product);

    @Delete("delete from product where id = #{id}")
    int deleteById(Long id);

    @Select("select * from product t where t.id = #{id}")
    Product findById(Long id);

    int count(Map<String, Object> params);

    List<Product> findList(Map<String, Object> params);

}
