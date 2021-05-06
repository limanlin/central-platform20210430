//package com.central.server.impl;
//
//import com.central.dao.*;
//import com.central.log.dao.LogDao;
//import com.central.model.common.PageResult;
//import com.central.model.common.utils.JsonResult;
//import com.central.model.common.utils.PageUtil;
//import com.central.model.log.SysLog;
//import com.central.model.sms.*;
//import com.central.server.SmsRecordService;
//import com.central.server.SmsUserService;
//import com.central.util.DingDingUtil;
//import com.central.util.MD5Util;
//import com.central.util.SmsUtil;
//import com.central.util.TimeUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.poi.ss.formula.functions.Now;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.*;
//
//@Slf4j
//@Service
//public class SmsRecordServiceImpl implements SmsRecordService {
////    @Autowired
////    private SmsRecordDao smsRecordSDao;
////    @Autowired
////    private SmsUserDao smsUserDao;
////    @Autowired
////    private SmsAlarmDao smsAlarmDao;
////    @Autowired
////    private HttpServletRequest request;
////    @Autowired
////    private SmsRequestDao smsRequestDao;
////    @Autowired
////    private SmsStatDao smsStatDao;
//
//    @Override
//    public void save(SmsRecord smsRecord) {
//        smsRecord.setSendTime(new Date());
//        smsRecordSDao.save(smsRecord);
//        log.info("新增短信记录：{}", smsRecord);
//    }
//
//    @Override
//    public void update(SmsRecord smsRecord) {
//        smsRecordSDao.update(smsRecord);
//        log.info("修改短信记录：{}", smsRecord);
//    }
//
//    @Override
//    public void delete(Long id) {
//        SmsRecord smsRecord=smsRecordSDao.findById(id);
//        smsRecordSDao.deleteById(id);
//        log.info("删除短信记录：{}", smsRecord);
//    }
//
//    @Override
//    public SmsRecord findById(Long id) {
//        return smsRecordSDao.findById(id);
//    }
//
//    @Override
//    public PageResult<SmsRecord> findSmsRecords(Map<String, Object> params) {
//        int total =smsRecordSDao.count(params);
//        List<SmsRecord> list = Collections.emptyList();
//        if (total > 0) {
//            PageUtil.pageParamConver(params, true);
//            list=smsRecordSDao.findList(params);
//        }
//        return PageResult.<SmsRecord>builder().data(list).code(0).count((long)total).build();
//    }
//
//    @Override
//    public JsonResult SendSMM(String mobile,String smsContent,String ipAddress,String userName,long timeStamp,String sign,int isAbroad) {
//        String ReturnJson="";
//        int code=1;
//        Boolean flag=true;
//        //保存请求记录
//        SmsRequest smsRequest=new SmsRequest();
//        smsRequest.setSendTime(new Date());
//        smsRequest.setContent(smsContent);
//        smsRequest.setIpAddress(ipAddress);
//        smsRequest.setPhone(mobile);
//        smsRequest.setUserName(userName);
//        smsRequestDao.save(smsRequest);
//        //如果当前时间比时间戳时间大于5分钟，则不执行
//        long curTime=new Date().getTime();
//        long diff=curTime-timeStamp;
//        long minute5=5*60*1000;
//        if(diff>minute5){
//            flag=false;
//            ReturnJson="签名已过期";
//            code=-5;
//        }
//        if(flag){
//            SmsUser smsUser=smsUserDao.findByName(userName);
//            if(smsUser!=null){
//                //验证前端的加密方式是否正确
//                String userCode=smsUser.getUserCode();
//                //签名方式:md5(userName+timestamp) sign
//                String newSign= MD5Util.getMD5(userName+timeStamp);
//                if(!newSign.equals(sign)){
//                    flag=false;
//                    ReturnJson="签名不正确";
//                    code=-6;
//                }
//                if(flag){
//                    if(smsUser.getSuffix()!=null){
//                        smsContent=smsContent+"【"+smsUser.getSuffix()+"】";//将后
//                    }
//                    if(isAbroad==1){//国外
//                        try {
//                            int status=SmsUtil.sendSmsAbroad(mobile,smsUser.getUserName(),smsUser.getPassword(),smsUser.getProductid(),smsUser.getXh(),smsContent);
//                            if(status==1){
//                                ReturnJson="信息发送成功";
//                            }else{
//                                ReturnJson="信息发送不成功的原因编码："+status;
//                                smsContent=smsContent+";"+ReturnJson;
//                                code=-1;
//                            }
//                            SmsRecord smsRecord=new SmsRecord();
//                            smsRecord.setContent(smsContent);
//                            smsRecord.setSendTime(new Date());
//                            smsRecord.setPhone(mobile);
//                            smsRecord.setSmsType(0);
//                            smsRecord.setStatus(status);
//                            smsRecord.setUserCode(userCode);
//                            smsRecord.setIpAddress(ipAddress);
//                            smsRecord.setSysName(smsUser.getSysName());
//                            smsRecordSDao.save(smsRecord);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            log.info("发送国外短信报错：{}", e.getMessage());
//                            ReturnJson="发送国外短信报错,出错信息："+e.getMessage();
//                            code=-3;
//                        }
//                    }else{
//                        try {
//                            /**
//                             * 短信发送控制
//                             * 1、按系统控制每天发送短信数量
//                             * 2、每个IP每天最多只能发送多少条短信
//                             * 3、每个手机号每天最多只能发送多少条短信
//                             * 4、如果headers为空，不能发送短信。（这个暂时不知道如何弄）
//                             * */
//                            Map<String, Object> params=new HashMap<>();
//                            params.put("userCode",smsUser.getUserCode());
//                            params.put("sendTime","1");
//                            int totalCount=smsRecordSDao.count(params);
//
//                            params.clear();
//                            params.put("userCode",smsUser.getUserCode());
//                            params.put("sendTime","1");
//                            params.put("ipAddress",ipAddress);
//                            int totalIpCount=smsRecordSDao.count(params);
//
//                            params.clear();
//                            params.put("userCode",smsUser.getUserCode());
//                            params.put("sendTime","1");
//                            params.put("phone",mobile);
//                            int totalPhoneCount=smsRecordSDao.count(params);
//
//                            //String s=this.getUserAgent();
//                            //Map<String, String> map=this.getHeadersInfo();
//                            if(totalCount>=smsUser.getDaySendNum() ){
//                                flag=false;
//                                smsContent="今天发送的短信数量已经超出设定的数量值，设定值为："+smsUser.getDaySendNum();
//                                code=-2;
//                            }else if(totalIpCount>=smsUser.getIpDaySendNum()){
//                                flag=false;
//                                smsContent="今天从IP地址("+ipAddress+")发出的短信数量已经超出设定的数量值，设定值为："+smsUser.getIpDaySendNum();
//                                code=-2;
//                            }else if(totalPhoneCount>=smsUser.getTelDaySendNum()){
//                                flag=false;
//                                smsContent="今天从手机号("+mobile+")发出的短信数量已经超出设定的数量值，设定值为："+smsUser.getTelDaySendNum();
//                                code=-2;
//                            }else{
//                                int status=SmsUtil.sendSmsDomestic(mobile,smsUser.getUserName(),smsUser.getPassword(),smsUser.getProductid(),smsUser.getXh(),smsContent);
//                                if(status==1){
//                                    ReturnJson="信息发送成功";
//                                    //发送成功后，插入或更新统计表的数据
//                                    this.saveOrUpdateStat(smsUser.getUserCode(),smsUser.getSysName());
//                                }else{
//                                    ReturnJson="信息发送不成功的原因编码："+status;
//                                    smsContent=smsContent+";"+ReturnJson;
//                                    code=-1;
//                                }
//                                SmsRecord smsRecord=new SmsRecord();
//                                smsRecord.setContent(smsContent);
//                                smsRecord.setSendTime(new Date());
//                                smsRecord.setPhone(mobile);
//                                smsRecord.setSmsType(0);
//                                smsRecord.setStatus(status);
//                                smsRecord.setUserCode(userCode);
//                                smsRecord.setIpAddress(ipAddress);
//                                smsRecord.setSysName(smsUser.getSysName());
//                                smsRecordSDao.save(smsRecord);
//                            }
//                            if(flag==false){//保存警告消息
//                                SmsAlarm smsAlarm=new SmsAlarm();
//                                smsAlarm.setUserCode(userCode);
//                                smsAlarm.setPhone(mobile);
//                                smsAlarm.setSysName(smsUser.getSysName());
//                                smsAlarm.setSmsContent(smsContent);
//                                smsAlarm.setOperatdate(new Date());
//                                smsAlarm.setIpAddress(ipAddress);
//                                smsAlarmDao.save(smsAlarm);
//                                ReturnJson=smsContent;
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            log.info("发送国内短信报错：{}", e.getMessage());
//                            ReturnJson="发送国内短信报错,出错信息："+e.getMessage();
//                            code=-3;
//                        }
//                    }
//                }
//            }
//        }
//        return JsonResult.ok(code,ReturnJson);
//    }
//
//    private void saveOrUpdateStat(String userCode,String sysName){
//        SmsStat smsStat=smsStatDao.findByCode(userCode);
//        if(smsStat!=null){
//            int totalNum=smsStat.getTotalNum()+1;
//            //如果预警数值大于等于配置的值，那么发送预警到钉钉群
//            SmsUser smsUser=smsUserDao.findByCode(userCode);
//            if(smsUser.getAlarmNum()>0 ){//配置为0则不预警
//                int alarmNum=totalNum/smsUser.getDaySendNum()*100;
//                Long timestamp = System.currentTimeMillis();
//                if(smsStat.getAlarmTime()>0){
//                    if(timestamp>=smsStat.getAlarmTime()){
//                        if(alarmNum>smsUser.getAlarmNum()){
//                            String alarmContent=sysName+"系统今天的短信发送数量已经大于"+smsUser.getAlarmNum()+"%,今天已发送短信:"+totalNum+"条，请关注。";
//                            DingDingUtil.sendToDingDing(alarmContent);
//                            long interval=smsUser.getIntervalTime()*60*1000;
//                            timestamp=timestamp+interval;
//                            smsStat.setAlarmTime(timestamp);
//                        }
//                    }
//                }else{
//                    if(alarmNum>smsUser.getAlarmNum()){
//                        String alarmContent=sysName+"系统今天的短信发送数量已经大于"+smsUser.getAlarmNum()+"%，请关注。";
//                        DingDingUtil.sendToDingDing(alarmContent);
//                        long interval=smsUser.getIntervalTime()*60*1000;
//                        timestamp=timestamp+interval;
//                        smsStat.setAlarmTime(timestamp);
//                    }
//                }
//            }
//            smsStat.setTotalNum(totalNum);
//            smsStatDao.update(smsStat);
//        }else{
//            smsStat=new SmsStat();
//            smsStat.setStatDate(new Date());
//            smsStat.setSysName(sysName);
//            smsStat.setUserCode(userCode);
//            smsStat.setTotalNum(1);
//            smsStatDao.save(smsStat);
//        }
//    }
//    private String getUserAgent() {
//        return request.getHeader("user-agent");
//    }
//    private Map<String, String> getHeadersInfo() {
//        Map<String, String> map = new HashMap<String, String>();
//        Enumeration headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String key = (String) headerNames.nextElement();
//            String value = request.getHeader(key);
//            map.put(key, value);
//        }
//        return map;
//    }
//}
