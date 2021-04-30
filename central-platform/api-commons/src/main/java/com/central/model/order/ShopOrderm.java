package com.central.model.order;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class ShopOrderm implements Serializable {
    private Long id;
    private String orderNumber;
    private Long orderUserId;
    private Date orderTime;
    private Long outUserId;
    private Date outTime;
    private Integer status;
}
