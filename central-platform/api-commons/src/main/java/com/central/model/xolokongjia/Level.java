package com.central.model.xolokongjia;

import lombok.Data;

import java.io.Serializable;

@Data
public class Level implements Serializable {
    private static final long serialVersionUID = -6899012396835137070L;
    private Long id;
    private Long leveLcode;
    private String levelName;
    private Long sortNo;
}
