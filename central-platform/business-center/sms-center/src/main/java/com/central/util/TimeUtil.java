package com.central.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

	/**
	 * 计算指定日期距今多少年
	 * @param times 指定日期
	 * @return 年
	 */
	public static int getTime(String times){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String time=sdf.format(new Date());
		String t1 = time.replace('-','/');
		String t2 = times.replace('-','/');

		@SuppressWarnings("deprecation")
		Date dt1= new Date(t1);
		@SuppressWarnings("deprecation")
		Date dt2= new Date(t2);
		long i= (dt1.getTime() - dt2.getTime())/(1000*60*60*24);
		return (int) (i/365);
	}
	/**
	 * 返回当前时间
	 * @return
	 */
	public static String getNowTime(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=sdf.format(new Date());
		return time;
	}

	/**
	 * 返回当前时间
	 * @return
	 */
	public static String getNowTime(String format){
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		String time=sdf.format(new Date());
		return time;
	}
	/**
	 * 时间戳转日期 带时分秒
	 * @param ms
	 * @return
	 */
	public static String transForDateTime(Integer ms){
		if(ms==null){
			ms=0;
		}
		long msl=(long)ms*1000;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String temp=null;
		if(ms!=null){
			try {
				String str=sdf.format(msl);
				temp=sdf.format(sdf.parse(str));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}

	//计算时间差，以分钟为单位。如：2018-08-08 和 2018-08-07 相差24h
	public double getDistanceTime(Date startTime, Date endTime) {
		double minute = 0;
		long time1 = startTime.getTime();
		long time2 = endTime.getTime();

		long diff;
		if (time1 < time2) {
			diff = time2 - time1;
		} else {
			diff = time1 - time2;
		}
		minute = (diff / ( 60 * 1000));
		return minute;
	}

	/**
	 * 字符串转换成日期
	 * @param str
	 * @return date
	 */
	public Date StrToDate(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}

