package com.central.model.xolokongjia;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ErrorLog implements Serializable {
    private static final long serialVersionUID = -6899012296805137070L;
    private Long id;
    private Date operatDate;//报错时间
    private String errorContent;//报错内容
    private String operatContent;//操作内容
    private Long userId; //操作人
    private String userName;//操作人姓名
}
