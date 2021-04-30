package com.central.model.xolokongjia;

import lombok.Data;

import java.io.Serializable;

@Data
public class Brand implements Serializable {
    private static final long serialVersionUID=-6899022296305437070L;
    private Long id;
    private Long brandCode;
    private String brandName;
}
