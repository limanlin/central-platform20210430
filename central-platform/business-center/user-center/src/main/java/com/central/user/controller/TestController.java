package com.central.user.controller;

import javax.annotation.Resource;

import com.central.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author 作者 owen E-mail: 624191343@qq.com
* @version 创建时间：2017年11月12日 上午22:57:51
 */
@RestController
public class TestController {
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@GetMapping("/test111")
	public String hello() {
		String userName="younihanshi";
		long time=new Date().getTime();
		return MD5Util.getMD5(userName+time);
	}

	 
}
