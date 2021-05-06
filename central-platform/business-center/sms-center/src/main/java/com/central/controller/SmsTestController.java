package com.central.controller;


import com.central.util.MD5Util;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author 作者 owen E-mail: 624191343@qq.com
* @version 创建时间：2017年11月12日 上午22:57:51
 */
@Slf4j
@RestController
@Api(tags = "测试api")
public class SmsTestController {
	private static final Logger logger = LoggerFactory.getLogger(SmsTestController.class);
	
	@GetMapping("/hellosms")
	public String hello() {
		String userName="younihanshi";
		long time=new Date().getTime();
		return "sign:"+ MD5Util.getMD5(userName+time)+"    time:"+time;
		//String result=DingDingUtil.sendToDingDing("再测试\nXX公司 10000\nVVV公司 30000");
		//return result;
	}

	 
}
