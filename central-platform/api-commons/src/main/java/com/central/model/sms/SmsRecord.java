package com.central.model.sms;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 作者 bailanghuan
 * @version 创建时间：2020年2月28日
 * 短信记录实体
 */
@Data
public class SmsRecord implements Serializable {
    private static final long serialVersionUID = -5886012896805137072L;
    private Long id;
    private String userCode;//系统用户编码
    private String sysName;//系统名称
    private String phone;//发送短信手机号
    private String content;//发送短信内容
    private String message;//短信供应商返回信息
    private Integer status;//发送状态，1-有效，0-无效
    private Date sendTime;//发送时间
    private Integer smsType;//短信类别，0-正常短信，1-报警短信
    private String ipAddress;//发送短信的IP地址
}
