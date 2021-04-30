package com.central.model.product;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * @author 作者 bailanghuan
 * @version 创建时间：2019年8月12日
 * 产品实体
 */
@Data
public class Product implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6886012896705137070L;
    private Long id;
    private String fullName; //产品全称
    private String shortName; //产品简称
    private Integer brand; //品牌编码
    private Integer productType;//产品系列编码
    private String productCode;//产品编码
    private String picUrl;//产品图片url
    private Integer minUnit;//最小单位
    private Integer maxUnit;//最大单位
    private Integer maxNum;//最大单位单品数量，比如每箱多少盒
    private String place;//产地
    private Integer days;//保质期（天数）
    private Double price;//产品原价
    private Double salePrice;//销售单价
    private Double weight;//重量
    private Date createTime;
    private Integer createUserId; //创建人id
    private Date updateTime;
    private Integer updateUserId; //修改人id
    private Integer isUse; //是否上架，0-下架，1-上架
}
