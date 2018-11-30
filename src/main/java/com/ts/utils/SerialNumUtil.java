package com.ts.utils;

import java.util.Date;
import java.util.Random;

/**
 * @date 2017年1月6日 上午11:46:22
 * @Description: 序列号生成工具
 * 
 */
public class SerialNumUtil {
	
	private static Random rand = new Random(System.currentTimeMillis());
	
	/**
	 * 
	 * 生成一个流水号<BR>
	 * 方法名：sn<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2018年1月3日-上午11:34:11 <BR>
	 * @param startStr
	 * @return String<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	public static String sn(String startStr) {
		StringBuilder sb = new StringBuilder(32);
		sb.append(startStr).append(DateUtil.convertDate2String(new Date(), DateUtil.DATE_TIME_LOCALE_STAMP)).append(rand.nextInt(900000)+100000);
		return sb.toString();
	}
	
	public static String sn(String startStr, String endStr) {
		StringBuilder sb = new StringBuilder(32);
		sb.append(startStr).append(".").append(endStr);
		return sb.toString();
	}
}
