package com.central.model.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysDictionary implements Serializable {
    private static final long serialVersionUID = -6886012896705128070L;
    private Long id;
    private String valueCode; //字典值
    private String valueName; //字典值名称
    private String typeCode; //字典编码
    private String typeName; //字典名称
    private Integer sortNum;//字典排序
}
