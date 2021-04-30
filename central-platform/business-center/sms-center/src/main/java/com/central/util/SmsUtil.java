package com.central.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

public class SmsUtil {
    //国内
    public static int sendSmsDomestic(String mobile,String userName,String psw,String productId,String xh,String smsContent) throws Exception{
        Map paramentMap = new LinkedHashMap();
        paramentMap.put("username", userName);//用户名
        String strtime = new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis());
        String pass = MD5Util.getMD5(MD5Util.getMD5(psw)+strtime);
        paramentMap.put("tkey",  strtime);
        paramentMap.put("password", pass);//加密后密码
        paramentMap.put("productid", productId);//产品id
        paramentMap.put("mobile", mobile);//号码
        paramentMap.put("content",  smsContent);//内容
        paramentMap.put("xh",  xh);

        String status = sendHttpRequest16("http://www.ztsms.cn/sendNSms.do", paramentMap, "UTF-8", "POST");
        String[] arr=status.split(",");
        int intStatus=Integer.parseInt(arr[0]);
        return intStatus;
    }

    //国外
    public static int sendSmsAbroad(String mobile,String userName,String psw,String productId,String xh,String smsContent) throws Exception{
        String url ="http://www.ztsms.cn/sendGSms.do";
        String strtime = new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis());
        try{
            smsContent= URLEncoder.encode(smsContent,"utf-8");
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String param="url="+url+"&username="+userName+"&password="+MD5Util.getMD5(psw)+"&mobile="+mobile+"&content="+smsContent+"&productid="+productId+"&xh"+xh;
        String status=HttpRequest.sendPost(url, param);//sendPost or sendGet
        String[] arr=status.split(",");
        int intStatus=Integer.parseInt(arr[0]);
        return intStatus;
    }

    public static String sendHttpRequest16(String strUrl, Map<String, String> paramentMap, String anaycle, String presendway) {
        StringBuffer sb = new StringBuffer();
        try {
            URL url = new URL(strUrl);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setDoOutput(true);
            httpConn.setRequestMethod(presendway);
            httpConn.setConnectTimeout(60000);
            httpConn.setReadTimeout(60000);

            StringBuffer parament = new StringBuffer();
            for (Map.Entry entry : paramentMap.entrySet()) {
                if (("".equals(entry.getKey())) || (entry.getKey() == null)) {
                    parament.append(URLEncoder.encode((String) entry.getValue(), anaycle) + "&");
                } else if ((((String) entry.getKey()).equalsIgnoreCase("MOBILE")) || (((String) entry.getKey()).equalsIgnoreCase("phone")) ||
                        (((String) entry.getKey()).equalsIgnoreCase("mb")) || (((String) entry.getKey()).equalsIgnoreCase("tele"))) {
                    if (((String) entry.getValue()).endsWith(",")) {// 手机号以,结尾
                        parament.append((String) entry.getKey() + "=" + ((String) entry.getValue()).substring(0, ((String) entry.getValue()).length() - 1) + "&");
                    } else {
                        parament.append((String) entry.getKey() + "=" + ((String) entry.getValue()) + "&");
                    }
                } else if ((((String) entry.getKey()).equalsIgnoreCase("Content")) || (((String) entry.getKey()).equalsIgnoreCase("Message")) || (((String) entry.getKey()).equalsIgnoreCase("ms"))
                        || (((String) entry.getKey()).equalsIgnoreCase("msg_content")) || (((String) entry.getKey()).equalsIgnoreCase("msg")) || (((String) entry.getKey()).equalsIgnoreCase("sms"))
                        || (((String) entry.getKey()).equalsIgnoreCase("smscontent"))|| (((String) entry.getKey()).equalsIgnoreCase("smsg")))
                    parament.append((String) entry.getKey() + "=" + URLEncoder.encode((String) entry.getValue(), anaycle) + "&");
                else {
                    parament.append((String) entry.getKey() + "=" + (String) entry.getValue() + "&");
                }
            }
            //log.info(url+"?"+parament.toString());
            if (("".equals(parament.toString())) || (parament.toString() == null))
                return "9999";
            if (parament.toString().endsWith("&")) {
                parament.setLength(parament.length() - 1);
            }

            //httpConn.setRequestProperty("Content-Length", String.valueOf(parament.toString().getBytes().length));
            httpConn.setRequestProperty("Content-Length", String.valueOf(parament.toString().getBytes().length));

            httpConn.getOutputStream().write(parament.toString().getBytes());
            httpConn.getOutputStream().flush();
            httpConn.getOutputStream().close();
            InputStream is = httpConn.getInputStream();
            BufferedReader bf = new BufferedReader(new InputStreamReader(is,anaycle));
            String tr = "";
            while ((tr = bf.readLine()) != null) {
                sb.append("\n").append(tr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString().replaceFirst("\n", "");
    }

    public String getrandomnum6()
    {
        double r2 = Math.random()*1034*2034;
        String sReturn=String.valueOf(r2).substring(0,6);
        if(sReturn.contains(".")) sReturn=sReturn.replace(".", "2");
        return sReturn;
    }
}
