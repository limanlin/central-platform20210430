package com.central.model.xolokongjia;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OperatLog implements Serializable {
    private static final long serialVersionUID = -6899012296805157072L;
    private Long id;
    private Date operatDate;//报错时间
    private Long userId; //操作人
    private String userName;//操作人姓名
    private String operatContent;//操作内容
    private String interfaceName;//接口名称
    private String interfaceParam;//接口参数
    private String result;//返回结果
}
