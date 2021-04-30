package com.central.model.sms;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 作者 bailanghuan
 * @version 创建时间：2020年2月28日
 * 警告记录实体
 */
@Data
public class SmsAlarm implements Serializable {
    private static final long serialVersionUID = -6698012896805137070L;
    private Long id;
    private String userCode;//系统用户编码
    private String sysName;//系统名称
    private String phone;//警告信息手机号
    private String smsContent;//接收警告信息手机号
    private Date operatdate;//警告时间
    private String ipAddress;//IP地址
    private Integer alarmType;//警告类型，1-手机号，2-IP地址
    //private List<SmsRecord> records;
}
