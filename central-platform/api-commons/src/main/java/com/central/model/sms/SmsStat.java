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
public class SmsStat implements Serializable {

    private static final long serialVersionUID = -6402607179148883544L;
    private Long id;
    private String userCode;//系统用户编码
    private String sysName;//系统名称
    private Date statDate;//统计日期
    private Integer totalNum;//发送短信数量
    private Long alarmTime;//下次预警时间
}
