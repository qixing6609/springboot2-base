package com.ts.utils;

import org.apache.commons.lang3.time.DateFormatUtils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by shan on 16/7/28.
 */
public class DateUtils extends DateUtil {
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
	
    private static final String YYYY_MM_DD = "yyyy-MM-dd";
    private static final String YMDHMS_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String YMDHM_FROMAT = "yyyy-MM-dd HH:mm";
    
    public final static String MON = "周一";
	public final static String TUE = "周二";
	public final static String WED = "周三";
	public final static String THU = "周四";
	public final static String FRI = "周五";
	public final static String SAT = "周六";
	public final static String SUN = "周日";

	private static final String DEFAULT_DATETIME_FORMAT = null;

    public static int daysOfTwoDate(Date start, Date end) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        int startDay = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.setTime(end);
        int endDay = calendar.get(Calendar.DAY_OF_YEAR);
        return endDay - startDay;
    }

    public static String getOffsetDate(Date start, int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        calendar.add(Calendar.DAY_OF_MONTH, offset);
        return DateFormatUtils.format(calendar, YYYY_MM_DD);
    }

    public static String formatYMD(Date date) {
        return DateFormatUtils.format(date, YYYY_MM_DD);
    }

    public static String getCurrTime() {
        Date now = new Date();
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return outFormat.format(now);
    }


    public static String getDateOfYMDHMSString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(YMDHMS_FORMAT);
        return format.format(date);
    }

    public static String getDateOfYMDHMString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(YMDHM_FROMAT);
        return format.format(date);
    }
    
    /**
	 * 从yyyymmdd中取得星期的几的方法
	 *
	 * @param strDate 入力日期（yyyymmdd）
	 * @return 1～７
	 * @author 2011/11/01
	 */
	public static int getWeekDayIndex(String origDate) {
		int formatDate = 0;
		String strDate = "";
		if (origDate == null) {
			return formatDate;
		}
		strDate = String.valueOf(origDate).trim();
		if ("".equals(strDate) || "0".equals(strDate)) {
			return formatDate;
		}

		if (strDate.length() < 8) {
			return formatDate;
		}

		//        SimpleDateFormat sdf = new SimpleDateFormat(DATE);
		try {
			Date date = parseDate(strDate, DEFAULT_DATETIME_FORMAT);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar.get(Calendar.DAY_OF_WEEK);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}

	}
    
    /**
	 * 取得星期几的方法
	 * @return 处理后的字符串
	 *
	 * @author 2011/11/01
	 */
	public static String getWeekDayName(String origDate) {
		switch (getWeekDayIndex(origDate)) {
		case 2:
			return MON;
		case 3:
			return TUE;
		case 4:
			return WED;
		case 5:
			return THU;
		case 6:
			return FRI;
		case 7:
			return SAT;
		case 1:
			return SUN;
		default:
			return "";
		}
	}
	
	/**
	 * 
	 * 累加周<BR>
	 * 方法名：addWeek<BR>
	 * 创建人：gaojianyu <BR>
	 * 时间：2018年7月27日-下午3:02:22 <BR>
	 * @param date
	 * @param week
	 * @return Date<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	public static Date addWeek(Date date, int week) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(3, week);
		return cal.getTime();
	}
	
	/**
	 * 根据参数，累加天数
	 *
	 * @param date 日期对象
	 * @param day 需要累加的天数
	 * @return 计算后的日期
	 */
	public static Date addDay(Date date, int day) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, day);
		return calendar.getTime();
	}
	/**
	 * 根据参数，小时的累加
	 *
	 * @param date 日期对象
	 * @param hour 需要累加的小时
	 * @return 计算后的日期
	 */
	public static Date addHour(Date date, int hour) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, hour);
		return calendar.getTime();
	}

	/**
	 * 根据参数，分钟累加
	 *
	 * @param date 日期对象
	 * @param min 需要累加的分钟
	 * @return 计算后的日期
	 */
	public static Date addMinute(Date date, int min) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, min);
		return calendar.getTime();
	}
	
	public static Date convertString2Date(String str, String pattern) {
		if (str == null || "".equals(str)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(sdf.parse(str.trim()));
		} catch (ParseException ex) {
			throw new RuntimeException(ex);
		}
		return calendar.getTime();
	}
}
