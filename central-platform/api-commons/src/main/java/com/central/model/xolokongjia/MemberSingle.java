package com.central.model.xolokongjia;

import lombok.Data;

import java.io.Serializable;

@Data
public class MemberSingle implements Serializable {
    private static final long serialVersionUID = -6561012396835137070L;
    private Long id;
    private String realName;
}
