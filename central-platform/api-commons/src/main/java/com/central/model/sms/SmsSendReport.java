package com.central.model.sms;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 作者 bailanghuan
 * @version 创建时间：2020年2月28日
 * 按系统统计短信发送实体
 */
@Data
public class SmsSendReport implements Serializable {
    private static final long serialVersionUID = 4323570539616338778L;
    private String userCode;//系统用户编码
    private String sysName;//系统名称
    private Integer validCount;//发送有效短信数量
    private Integer invalidCount;//无效短信数量
    private Integer alarmCount;//警告次数
}
