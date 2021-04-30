package com.central.util;


import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.InetAddress;
import java.net.URLEncoder;

/**
 * 接入钉钉
 * */
@Slf4j
public class DingDingUtil {
    public static String sendToDingDing(String content){
        String secret="31df83fb695b9e9a362f2122a214c2a512da7c1f79fa4d5ade23cbb19b88e558";//秘钥
        String result="";
        content="【xolo】\n"+content;
        try {
            Long timestamp = System.currentTimeMillis();
            String stringToSign = timestamp + "\n" + secret;
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
            String sign= URLEncoder.encode(new String(Base64.encodeBase64(signData)),"UTF-8");
            String ip = InetAddress.getLocalHost().getHostAddress();
            //调用钉钉接口
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?access_token="+secret);
            OapiRobotSendRequest request = new OapiRobotSendRequest();
            request.setMsgtype("text");
            OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
            text.setContent(content);
            request.setText(text);
            OapiRobotSendResponse response = client.execute(request);
            result=response.getMsg();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("接入钉钉报错：",e.getMessage());
        }
        return result;
    }
}

