package com.central.model.sms;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 作者 bailanghuan
 * @version 创建时间：2020年3月5日
 * 请求短信接口记录实体
 */
@Data
public class SmsRequest implements Serializable {
    private static final long serialVersionUID = -3763143651637274891L;
    private Long id;
    private String userName;//用户名
    private String phone;//发送短信手机号
    private String content;//发送短信内容
    private Date sendTime;//请求时间
    private String ipAddress;//发送短信的IP地址
}
