package com.central.model.sms;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 作者 bailanghuan
 * @version 创建时间：2020年2月28日
 * 系统用户实体
 */
@Data
public class SmsUser implements Serializable {
    private static final long serialVersionUID = -6896012896805137071L;
    private Long id;
    private String sysName;//短信系统名称
    private String userCode;//系统用户编码
    private String userName;//短信供应商使用的用户名
    private String password;//短信供应商设置的密码
    private String productid;//短信产品id
    private String xh;// 短信型号，一般留空
    private String suffix;// 短信后缀
    private Integer enabled;//是否启用，1-启用，0-不启用
    private String remark;//备注
    private Date createTime;//创建时间
    private Date updateTime;//修改时间
    private Integer totalSendNum;//累计发送的短信数量
    private Integer daySendNum;//每天发送的短信数量
    private Integer ipDaySendNum;//每个IP每天发送的短信数量
    private Integer telDaySendNum;//每个电话每天发送的短信数量
    private Integer abnormalNum;//短信异常报警次数
    private Integer alarmNum;//当天发送短信数量的预警值，0-100。默认80
    private Integer intervalTime;//预警时间间隔（以分为单位）
    //private List<SmsRecord> records;
}
