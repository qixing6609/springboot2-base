package com.ts.utils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;

public class DateUtil {

	public final static String YEAR = "yyyy";
	public final static String MONTH = "MM";
	public final static String DAY = "dd";
	public final static String YEAR_MONTH = "yyyyMM";
	public final static String MONTH_DAY = "MMdd";
	public final static String DATE = "yyyyMMdd";
	public final static String HOUR = "hh";
	public final static String MINUTE = "mm";
	public final static String SECOND = "ss";
	public final static String HOUR_MINUTE = "hhmm";
	public final static String MINUTE_SECOND = "mmss";
	public final static String TIME = "HHmmss";
	public final static String DATE_TIME = "yyyyMMddHHmmss";
	public final static String YEAR_MONTH_LOCALE = "yyyy-MM";
	public final static String MONTH_DAY_LOCALE = "MM-dd";
	public final static String DATE_LOCALE = "yyyy-MM-dd";
	public final static String DATE_LOCALE_CN = "yyyy年MM月";
	public final static String DATE_LOCALE_S = "yyyy-M-d";
	public final static String HOUR_MINUTE_LOCALE = "hh:mm";
	public final static String MINUTE_SECOND_LOCALE = "mm:ss";
	public final static String TIME_LOCALE = "hh:mm:ss";
	public final static String DATE_TIME_LOCALE = "yyyy-MM-dd HH:mm:ss";
	public final static String DATE_TIME_LOCALE_M = "yyyy-MM-dd HH:mm";
	public final static String DATE_TIME_LOCALE_STAMP = "yyyyMMddHHmmssSSS";
	public final static String DATE_TIME_LOCALE_STAMP_1 = "yyyy-MM-dd HH:mm:ss.SSS";
	public final static String DATE_TIME_LOCALE_TIMESTAMP = "yyyy-MM-dd HH:mm:ss";
	public final static String HOUR_MINUTE_S = "HH:mm";
	public final static String DATE_TIME_S = "MM-dd HH:mm";
	public final static String NEN = "年";
	public final static String MON = "周一";
	public final static String TUE = "周二";
	public final static String WED = "周三";
	public final static String THU = "周四";
	public final static String FRI = "周五";
	public final static String SAT = "周六";
	public final static String SUN = "周日";
	public final static String CODE_SAT = "7";
	public final static String CODE_SUN = "1";
	public final static String SLASH = "/";
	public final static String FIRST_DAY = "01";
	public final static String BLACKETS_L = "（";
	public final static String BLACKETS_R = "）";
	public final static String GC_COLOR_RED = "#FF0000"; // 赤
	public final static String GC_COLOR_BLU = "#0000FF"; // 青

	/** 一秒钟的毫秒数 */
	public static final int ONE_SECOND_MILLIOS = 1000;
	/** 一分钟的毫秒数 */
	public static final int ONE_MINUTE_MILLIONS = 60 * ONE_SECOND_MILLIOS;
	/** 一小时的毫秒数 */
	public static final int ONE_HOUR_MILLIONS = 60 * ONE_MINUTE_MILLIONS;
	/** 一小时的秒数 */
	public static final int ONE_DAY_SECOND = 3600;
	/** 一天的毫秒数 */
	public static final int ONE_DAY_MILLIONS = 24 * ONE_HOUR_MILLIONS;

	public static Date now() {
		return new Date();
	}

	/**
	 * @Description: 计算两个日期之间有几个计息日
	 * @param d1
	 *            日期1， 必须比d2更早
	 * @param d2
	 * @return int 天数
	 */
//	public static int calcIntervalDays(Date d1, Date d2) {
//		DateTime dt1 = new DateTime(d1);
//		DateTime dt2 = new DateTime(d2);
//		Interval itv = new Interval(dt1.withTimeAtStartOfDay(), dt2.withTimeAtStartOfDay());
//		return (int) itv.toDuration().getStandardDays();
//	}

	public static Date addWeek(Date date, int week) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(3, week);
		return cal.getTime();
	}

	public static String formatDate(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public static Date parseDate(String dateStr, String format) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(dateStr);
	}

	public static int daysBetween(Date date1, Date date2) {
		Calendar cNow = Calendar.getInstance();
		Calendar cReturnDate = Calendar.getInstance();
		cNow.setTime(date1);
		cReturnDate.setTime(date2);
		setTimeToMidnight(cNow);
		setTimeToMidnight(cReturnDate);
		long todayMs = cNow.getTimeInMillis();
		long returnMs = cReturnDate.getTimeInMillis();
		long intervalMs = todayMs - returnMs;
		return millisecondsToDays(intervalMs);
	}

	// public static void main(String[] args) {
	// Date date1 = DateUtil.convertString2Date("2018-02-26",
	// DateUtil.DATE_LOCALE);
	// Date date2 = DateUtil.convertString2Date("2018-02-28 00:30:00",
	// DateUtil.DATE_TIME_LOCALE_TIMESTAMP);
	//// date2 = DateUtil.addMonth(date2, -1);
	//// Date date2 = new Date();
	// System.out.println("date1:"+DateUtil.convertDate2String(date1,
	// DateUtil.DATE_LOCALE));
	// System.out.println("date2:"+DateUtil.convertDate2String(date2,
	// DateUtil.DATE_LOCALE));
	// System.out.println(daysBetween(date2, date1));
	//
	// System.out.println(calcIntervalDays(date1, date2));
	// }

	private static int millisecondsToDays(long intervalMs) {
		return (int) (intervalMs / 86400000L);
	}

	private static void setTimeToMidnight(Calendar calendar) {
		calendar.set(11, 0);
		calendar.set(12, 0);
		calendar.set(13, 0);
	}

	public static int secondInt(Date date) {
		return Integer.parseInt(formatDate(date, "ss"));
	}

	public static int minuteInt(Date date) {
		return Integer.parseInt(formatDate(date, "mm"));
	}

	public static int hourInt(Date date) {
		return Integer.parseInt(formatDate(date, "HH"));
	}

	public static int dayInt(Date date) {
		return Integer.parseInt(formatDate(date, "dd"));
	}

	public static int monthInt(Date date) {
		return Integer.parseInt(formatDate(date, "MM"));
	}

	public static int yearInt(Date date) {
		return Integer.parseInt(formatDate(date, "yyyy"));
	}

	/** liugm 2016-05-24 增加日期类方法 */

	public static Date getSystemDate() {
		return new Date();
	}

	/**
	 * 取得系统时间 <br>
	 *
	 * @return 系统时间
	 */
	public static String getSystemYYYYMMDD() {

		SimpleDateFormat dateFormat1 = new SimpleDateFormat(DATE_LOCALE);
		Date sysDate = new Date();
		return dateFormat1.format(sysDate);
	}

	/**
	 * 取得系统时间 <br>
	 *
	 * @return 系统时间
	 */
	public static String getSystemDateByformart(String pattern) {

		SimpleDateFormat dateFormat1 = new SimpleDateFormat(pattern);
		Date sysDate = new Date();
		return dateFormat1.format(sysDate);
	}

	/**
	 * 取得DB时刻。
	 *
	 * @return DB时刻
	 */
	public static Date getDatabaseDate(String dataSourceName) {
		return getCurrentDateTime(dataSourceName);
	}

	/**
	 * 取得DB时刻。
	 * 
	 * @param dataSourceName(jdbc/ReferenceDB)
	 * @return Date
	 */
	public static Date getCurrentDateTime(String dataSourceName) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			DataSource dataSource = InitialContext.doLookup(dataSourceName);
			con = dataSource.getConnection();
			ps = con.prepareStatement("SELECT SYSTIMESTAMP FROM DUAL");
			rs = ps.executeQuery();
			Timestamp timestamp = null;
			while (rs.next()) {
				timestamp = rs.getTimestamp("SYSTIMESTAMP");
			}
			return new Date(timestamp.getTime());
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqle) {
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException sqle) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException sqle) {
				}
			}
		}
	}

	/**
	 * 从系统中取得年月日时分秒
	 *
	 * @return 年月日時分秒の文字列
	 */
	public static String getSystemTimeStamp() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(getSystemDate());
	}

	/**
	 * 指定的字符串，日期对象的变换方法
	 *
	 * @param str
	 *            变换对象
	 * @param pattern
	 *            日期时刻的格式
	 * @return 指定的日期格式的变换
	 * 
	 *         <pre>
	 *         convertString2Date("2004-04-05 23:22:30", "yyyy-MM-dd") = 2004 - 04 - 05;
	 *         </pre>
	 * 
	 *         <pre>
	 *         convertString2Date("2004-04-05 23:22:30", "yyyy/MM/dd") = 2004 / 04 / 05;
	 *         </pre>
	 * 
	 *         <pre>
	 * convertString2Date("2004-04-05", "yyyy-MM-dd HH:mm:ss") = 2004-04-05 00:00:00 ;
	 *         </pre>
	 */
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

	/**
	 * 指定的日期，字符串对象的变换方法
	 *
	 * @param date
	 *            变换对象
	 * @param pattern
	 *            日期时刻的格式
	 * @return 指定格式的日期，字符串的变换
	 * 
	 *         <pre>
	 *         convertString2Date("2004-04-05 23:22:30", "yyyy-MM-dd") = 2004 - 04 - 05;
	 *         </pre>
	 * 
	 *         <pre>
	 *         convertString2Date("2004-04-05 23:22:30", "yyyy/MM/dd") = 2004 / 04 / 05;
	 *         </pre>
	 * 
	 *         <pre>
	 * convertString2Date("2004-04-05", "yyyy-MM-dd HH:mm:ss") = 2004-04-05 00:00:00 ;
	 *         </pre>
	 */
	public static String convertDate2String(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * 指定的对象，日期对象的变换方法
	 *
	 * @param obj
	 *            变换对象
	 * @return 日期时刻的格式
	 */
	public static Date convertObject2Date(Object obj) {
		String strObj = ConvUtil.convToString(obj);

		Date tempDate = convertString2Date(strObj, DATE_TIME_LOCALE_TIMESTAMP);

		return tempDate;
	}

	/**
	 * 是否是日期的判断
	 *
	 * @param date
	 *            判断对象
	 * @param pattern
	 *            日期时刻的格式
	 * @return True：日期对象、False：非日期对象
	 */
	public static boolean isValidDate(Object date, String pattern) {
		if (date == null) {
			return false;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			sdf.setLenient(false);
			sdf.parse(String.valueOf(date));
		} catch (ParseException ex) {
			return false;
		}
		return true;
	}

	/**
	 * 取得指定日期的年
	 *
	 * @param date
	 *            日期对象
	 * @return 年
	 */
	public static String getYear(Date date) {
		if (date == null) {
			return "";
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return String.valueOf(calendar.get(Calendar.YEAR));
	}

	/**
	 * 取得指定日期的月
	 *
	 * @param date
	 *            日期对象
	 * @return 月份
	 */
	public static String getMonth(Date date) {
		if (date == null) {
			return "";
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH) + 1;
		return String.valueOf(month);

	}

	/**
	 * 取得指定日期的天
	 *
	 * @param date
	 *            日期对象
	 * @return 日
	 */
	public static String getDay(Date date) {
		if (date == null) {
			return "";
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return String.valueOf(calendar.get(Calendar.DATE));

	}

	/**
	 * 取得时间-小时。
	 *
	 * @param date
	 *            日期对象
	 * @return 小时
	 */
	public static String getHour(Date date) {
		if (date == null) {
			return "";
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));

	}

	/**
	 * 取得时间-分钟。
	 *
	 * @param date
	 *            日期对象
	 * @return 分钟
	 */
	public static String getMinute(Date date) {
		if (date == null) {
			return "";
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return String.valueOf(calendar.get(Calendar.MINUTE));

	}

	/**
	 * 取得时间-秒
	 *
	 * @param date
	 *            日期对象
	 * @return 秒
	 */
	public static int getSecond(Date date) {
		if (date == null) {
			return 0;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.SECOND);
	}

	/**
	 * 根据参数，累加年
	 *
	 * @param date
	 *            日期对象
	 * @param year
	 *            需要累加的年数
	 * @return 计算后的日期
	 */
	public static Date addYear(Date date, int year) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, year);
		return calendar.getTime();
	}

	/**
	 * 根据参数，累加月
	 *
	 * @param date
	 *            日期对象
	 * @param month
	 *            需要累加的月数
	 * @return 计算后的日期
	 */
	public static Date addMonth(Date date, int month) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, month);
		return calendar.getTime();
	}

	/**
	 * 根据参数，累加天数
	 *
	 * @param date
	 *            日期对象
	 * @param day
	 *            需要累加的天数
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
	 * 取得对象日期的首日
	 *
	 * @param date
	 *            日期对象
	 * @return 对象日期的首日
	 */
	public static Date getFirstDay(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	/**
	 * 取得对象日期的末日
	 *
	 * @param date
	 *            日期对象
	 * @return 对象日期的末日
	 */
	public static Date getLastDay(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, 1);
		calendar.roll(Calendar.DATE, -1);

		return calendar.getTime();
	}

	/**
	 * 根据参数，小时的累加
	 *
	 * @param date
	 *            日期对象
	 * @param hour
	 *            需要累加的小时
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
	 * @param date
	 *            日期对象
	 * @param min
	 *            需要累加的分钟
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

	/**
	 * 根据参数，秒的累加
	 *
	 * @param date
	 *            日期对象
	 * @param min
	 *            需要累加的秒
	 * @return 计算后的日期
	 */
	public static Date addSecond(Date date, int second) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, second);
		return calendar.getTime();
	}

	/**
	 * 日期周期
	 * 
	 * @param FROM
	 *            日期
	 * @param TO
	 *            日期
	 * @return h:mm:ss
	 */
	public static String dateRemainder(Date t1, Date t2) {
		if (t1 == null || t2 == null) {
			return "";
		}
		long l = (t2.getTime() - t1.getTime()) / 1000;
		l = BigDecimal.valueOf(l).abs().longValue();

		long hh = l / 60 / 60;
		long mm = (l - (hh * 60 * 60)) / 60;
		long ss = l - ((hh * 60 * 60) + (mm * 60));

		long lDay = l / 60 / 60 / 24;

		DecimalFormat df = new DecimalFormat("00");

		if (lDay > 0) {
			hh = hh - (lDay * 24);
		}
		String HH = String.valueOf(hh);

		if (HH.length() > 1) {
			HH = HH.substring(HH.length() - 1, HH.length());
		}
		return HH + ":" + df.format(BigDecimal.valueOf(mm).abs()) + ":" + df.format(BigDecimal.valueOf(ss).abs());

	}

	/**
	 * 日期周期包含天
	 * 
	 * @param FROM
	 *            日期
	 * @param TO
	 *            日期
	 * @return dd:hh:mm:ss
	 */
	public static String dateRemainderCoverDay(Date t1, Date t2) {
		if (t1 == null || t2 == null) {
			return "";
		}
		long l = (t2.getTime() - t1.getTime()) / 1000;
		l = BigDecimal.valueOf(l).abs().longValue();

		long hh = l / 60 / 60;
		long mm = (l - (hh * 60 * 60)) / 60;
		long ss = l - ((hh * 60 * 60) + (mm * 60));

		long lDay = l / 60 / 60 / 24;

		DecimalFormat df = new DecimalFormat("00");

		String days = "";
		if (lDay > 0) {
			hh = hh - (lDay * 24);
			days = df.format(BigDecimal.valueOf(lDay).abs()) + ":";
		}
		String HH = String.valueOf(hh);

		if (HH.length() > 1) {
			HH = HH.substring(HH.length() - 1, HH.length());
		}
		return days + df.format(BigDecimal.valueOf(hh).abs()) + ":" + df.format(BigDecimal.valueOf(mm).abs()) + ":"
				+ df.format(BigDecimal.valueOf(ss).abs());

	}

	/**
	 * 日期周期
	 * 
	 * @param FROM
	 *            日期
	 * @param TO
	 *            日期
	 * @return d天 h时 m分 s秒
	 */
	public static String dateRemainder2(Date t1, Date t2) {
		if (t1 == null || t2 == null) {
			return "";
		}
		long l = (t2.getTime() - t1.getTime()) / 1000;
		l = BigDecimal.valueOf(l).abs().longValue();

		long hh = l / 60 / 60;
		long mm = (l - (hh * 60 * 60)) / 60;
		long ss = l - ((hh * 60 * 60) + (mm * 60));

		long lDay = l / 60 / 60 / 24;

		// DecimalFormat df = new DecimalFormat("00");

		if (lDay > 0) {
			hh = hh - (lDay * 24);
		}
		String HH = String.valueOf(hh);

		if (HH.length() > 1) {
			HH = HH.substring(HH.length() - 1, HH.length());
		}

		StringBuffer timeBuffer = new StringBuffer();
		if (lDay > 0) {
			timeBuffer.append(lDay).append("天  ").append(hh).append("时  ").append(mm).append("分  ").append(ss)
					.append("秒  ");
		} else if (hh > 0) {
			timeBuffer.append(hh).append("时  ").append(mm).append("分  ").append(ss).append("秒  ");
		} else if (mm > 0) {
			timeBuffer.append(mm).append("分  ").append(ss).append("秒  ");
		} else {
			timeBuffer.append(ss).append("秒  ");
		}
		return timeBuffer.toString();

	}

	/**
	 * 取得YYYY年MM月的形式
	 *
	 * @param date
	 *            日期对象
	 * @return yyyy年MM月
	 */
	public static String getYearMonthString(String yearMonth) {
		String rtnYearMonth = "";
		if (!StringUtils.isEmpty(yearMonth)) {
			if (yearMonth.length() == 6) {
				rtnYearMonth = yearMonth.substring(0, 4) + "年" + yearMonth.substring(4) + "月";
			} else if (yearMonth.length() == 4) {
				rtnYearMonth = yearMonth + "年";
			}
		}
		return rtnYearMonth;
	}

	/**
	 * 日期(yyyy/mm/dd hh:mm:ss)的变换
	 *
	 * @param dateValue
	 *            日期对象
	 * @return yyyy-mm-dd hh:mm:ss"
	 * @author 2011-11-01
	 */
	public static String dateTimeToStr(Date date) {
		return convertDate2String(date, DATE_TIME_LOCALE);

	}

	/**
	 * 从yyyymmdd中取得星期的几的方法
	 *
	 * @param strDate
	 *            入力日期（yyyymmdd）
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

		// SimpleDateFormat sdf = new SimpleDateFormat(DATE);
		Date date = convertString2Date(strDate, DATE_TIME_LOCALE);
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(date);
			return calendar.get(Calendar.DAY_OF_WEEK);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}

	}

	/**
	 * 取得星期几的方法
	 * 
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
	 * 格式化日期
	 *
	 * @param strVal
	 *            入力日期（yyyy、yyyymm、yyyymmdd）
	 * @param strSpace
	 *            使用的符号(""," ","nbsp;"等)
	 * @return 处理后日期
	 *
	 * @author 2011/11/01
	 */
	public static String getYMDformat(String strDate, String strSpace) {
		String wY = "";
		String wM = "";
		String wD = "";

		switch (strDate.length()) {
		case 4:
			wY = strDate;
			break;
		case 6:
			wY = strDate.substring(0, 4);
			wM = strDate.substring(4, 6);
			break;
		case 8:
			wY = strDate.substring(0, 4);
			wM = strDate.substring(4, 6);
			wD = strDate.substring(6, 8);
			break;
		default:
			return "";
		}

		if (wY.length() > 0) {
			wY = wY + NEN;
		}
		if (wM.length() > 0) {
			wM = Integer.valueOf(wM) + "月";
		}
		if (wD.length() > 0) {
			wD = Integer.valueOf(wD) + "日";
		}
		if (wY.length() > 0 && wM.length() > 0) {
			wY = wY + strSpace;
		}
		if (wM.length() > 0 && wD.length() > 0) {
			wM = wM + strSpace;
		}

		return wY + wM + wD;

	}

	/**
	 * 曜日色の編集をする
	 *
	 * @param pDay
	 *            入力日
	 * @param dvFlg
	 *            祝日文字列フラグ("000..."＝1～31バイト・日／"0"＝平日・曜日に従う／"1"＝祝日)
	 * @param weekValue
	 *            入力週（日～土、1～7）
	 * @param strVal
	 *            値
	 * @return 処理後日付
	 *
	 * @author 2011/11/01 hiSoft HeYa
	 */
	public static String weekColor(int days, String dvFlg, String weekValue, String strVal) {
		String wWeek = "";
		String rtnStr = "";

		if (dvFlg.length() >= days) {
			// 祝日は強制的に"日"とする
			// if (CODE_SUN.equals(dvFlg.substring(days - 1, 1))) {
			if (CODE_SUN.equals(dvFlg.substring(days - 1, days))) {
				wWeek = SUN;
			} else {
				wWeek = weekValue;
			}

			if (SUN.equals(wWeek) || CODE_SUN.equals(wWeek)) {
				rtnStr = GC_COLOR_RED;

			} else if (SAT.equals(wWeek) || CODE_SAT.equals(wWeek)) {
				rtnStr = GC_COLOR_BLU;

			} else {
				rtnStr = "";
			}

		}
		return rtnStr;
	}

	/**
	 * yyyymmの加減算をする
	 *
	 * @param addValue
	 *            加減算月
	 * @param yearMonth
	 *            入力年月（yyyymm）
	 * @return 処理後日付
	 *
	 * @author 2011/11/01 hiSoft HeYa
	 */
	public static String ymMonthAdd(int addValue, String yearMonth) {
		String formatDate = "";
		String strDate = "";
		if (yearMonth == null) {
			return formatDate;
		}
		strDate = String.valueOf(yearMonth).trim();
		if ("".equals(strDate) || "0".equals(strDate)) {
			return formatDate;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(YEAR_MONTH);
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(sdf.parse(strDate));
			calendar.add(Calendar.MONTH, addValue);
			formatDate = sdf.format(calendar.getTime());
		} catch (ParseException ex) {
			throw new RuntimeException(ex);
		}
		return formatDate;
	}

	/**
	 * yyyymmddの加減算をする
	 *
	 * @param f
	 *            時間間隔(DateAdd関数を参照)
	 * @param addValue加減算月
	 * @param dateValue
	 *            入力年月日（yyyymmdd）
	 * @return 処理後日付
	 * @author 2011/11/01 hiSoft HeYa
	 */
	public static String ymdDateAdd(int f, int addValue, String dateValue) {
		String formatDate = "";
		String strDate = "";
		if (dateValue == null) {
			return formatDate;
		}
		strDate = String.valueOf(dateValue).trim();
		if ("".equals(strDate) || "0".equals(strDate) || strDate.length() != 8) {
			return formatDate;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(DATE);
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(sdf.parse(strDate));
			calendar.add(f, addValue);
			formatDate = sdf.format(calendar.getTime());
		} catch (ParseException ex) {
			throw new RuntimeException(ex);
		}
		return formatDate;
	}

	public static Date dateAdd(Date value, int field, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(value);
		calendar.add(field, amount);
		return calendar.getTime();
	}

	/**
	 * yyyymmより月末日を取得する
	 *
	 * @param yearMonth入力年月(yyyymm）
	 * @return 処理後日付
	 * @author 2011/11/01 hiSoft HeYa
	 */
	// public static Date getMonthDays(String yearMonth) {
	// return DateUtil.getLastDay(DateUtil.convertString2Date(yearMonth,
	// YEAR_MONTH));
	// }
	public static int getMonthDays(String yearMonth) {
		return ConvUtil.convToInt(getDay(getLastDay(convertString2Date(yearMonth, YEAR_MONTH))));
	}

	/**
	 * yyyymmdd⇔yyyy/mm/dd的变换
	 *
	 * @param pFlg
	 *            変換flg 1 = yyyymmdd→yyyy-mm-dd 2 = yyyy-m-d→yyyymmdd 3
	 *            =yyyymmdd→yyyymmdd 4 = yyyy-m-d→yyyy-mm-dd
	 * @param updYearMonth
	 *            对象年月日
	 * @return 处理后日付
	 * @author 2011/11/01
	 */
	public static String date8FromTo10(int flg, String updYearMonth) {
		switch (flg) {
		case 1:
			return formatDateTime(updYearMonth, DATE, DATE_LOCALE);
		case 2:
			return formatDateTime(updYearMonth, DATE_LOCALE_S, DATE);
		case 3:
			return updYearMonth;
		case 4:
			return formatDateTime(updYearMonth, DATE_LOCALE_S, DATE_LOCALE);
		default:
			return "";
		}

	}

	/**
	 * yyyymm⇔yyyy/mmに変換する
	 *
	 * @param pFlg
	 *            変換フラグ
	 * @param yearMonth
	 *            入力日期（yyyymm）
	 * @return 処理後日付
	 * @author 2011/11/01 hiSoft HeYa
	 */
	public static String date6FromTo7(int pFlg, String yearMonth) {
		return formatDateTime(yearMonth, YEAR_MONTH, YEAR_MONTH_LOCALE);

	}

	/**
	 * yyyymmddを年月日曜表示の編集をする
	 *
	 * @param inDate
	 *            入力日期（yyyymmdd）
	 * @param strSpace
	 *            使用する空白(""," ","nbsp;"等)
	 * @return 処理後日付
	 * @author 2011/11/01 hiSoft HeYa
	 */
	public static String getYMDWformat(String inDate, String strSpace) {

		String wYMD = getYMDformat(inDate, strSpace); // yyyy、yyyymm、yyyymmddを年月日表示の編集をする
		String wWork = getWeekDayName(inDate); // yyyymmddより曜日を取得する

		if (wWork.length() > 0) {
			wWork = strSpace + BLACKETS_L + wWork + BLACKETS_R;
		}
		return wYMD + wWork;

	}

	/**
	 * 引数の文字列を指定されたフォーマットで日付に変換するメソッドです。
	 *
	 * @param strDate
	 *            変換対象のオブジェクト
	 * @param origPattern
	 *            変換元日付時刻フォーマットパターン
	 * @param destPattern
	 *            変換先日付時刻フォーマットパターン
	 * @return 引数がnullの場合は空、文字列の時は指定されたパターンに従ってフォーマットパターン日付を変換したもの
	 */
	public static String formatDateTime(Object origDate, String origPattern, String destPattern) {
		String formatDate = "";
		String strDate = "";
		if (origDate == null) {
			return formatDate;
		}
		strDate = String.valueOf(origDate).trim();
		if ("".equals(strDate) || "0".equals(strDate)) {
			return formatDate;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(origPattern);
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(sdf.parse(strDate));
			sdf.applyPattern(destPattern);
			formatDate = sdf.format(calendar.getTime());
		} catch (ParseException ex) {
			throw new RuntimeException(ex);
		}

		return formatDate;
	}

	/**
	 * 
	 * 描述：获取两个日期的分钟间隔 方法名：differenceMinute 创建人：gaojy 时间：2017年5月10日下午6:13:15
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long differenceMinute(Date startDate, Date endDate) {
		long start = startDate.getTime();
		long end = endDate.getTime();
		return (end - start) / (1000 * 60);

	}

	// 获取当天的开始时间
	public static java.util.Date getDayBegin() {
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	// 获取当天的结束时间
	public static java.util.Date getDayEnd() {
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	// 获取昨天的开始时间
	public static Date getBeginDayOfYesterday() {
		Calendar cal = new GregorianCalendar();
		cal.setTime(getDayBegin());
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}

	// 获取昨天的结束时间
	public static Date getEndDayOfYesterDay() {
		Calendar cal = new GregorianCalendar();
		cal.setTime(getDayEnd());
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}

	// 获取明天的开始时间
	public static Date getBeginDayOfTomorrow() {
		Calendar cal = new GregorianCalendar();
		cal.setTime(getDayBegin());
		cal.add(Calendar.DAY_OF_MONTH, 1);

		return cal.getTime();
	}

	// 获取明天的结束时间
	public static Date getEndDayOfTomorrow() {
		Calendar cal = new GregorianCalendar();
		cal.setTime(getDayEnd());
		cal.add(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	// 获取本周的开始时间
	public static Date getBeginDayOfWeek() {
		Date date = new Date();
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
		if (dayofweek == 1) {
			dayofweek += 7;
		}
		cal.add(Calendar.DATE, 2 - dayofweek);
		return getDayStartTime(cal.getTime());
	}

	// 获取本周的结束时间
	public static Date getEndDayOfWeek() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getBeginDayOfWeek());
		cal.add(Calendar.DAY_OF_WEEK, 6);
		Date weekEndSta = cal.getTime();
		return getDayEndTime(weekEndSta);
	}

	// 获取本月的开始时间
	public static Date getBeginDayOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(getNowYear(), getNowMonth() - 1, 1);
		return getDayStartTime(calendar.getTime());
	}

	// 获取上个月的第一天
	public static String getBeforeFirstMonthdate() throws Exception {
		SimpleDateFormat format = new SimpleDateFormat(DATE_LOCALE);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return format.format(calendar.getTime());
	}

	// 获取上个月的最后一天
	public static String getBeforeLastMonthdate() throws Exception {
		SimpleDateFormat sf = new SimpleDateFormat(DATE_LOCALE);
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return sf.format(calendar.getTime());
	}

	// 获取之前第几天的时间
	public static String getPastDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat(DATE_LOCALE);
		return format.format(today);
	}

	// 获取本月的开始时间
	public static Date getBeginDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(getNowYear(date), getNowMonth(date) - 1, 1);
		return getDayStartTime(calendar.getTime());
	}

	// 获取本月的结束时间
	public static Date getEndDayOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(getNowYear(), getNowMonth() - 1, 1);
		int day = calendar.getActualMaximum(5);
		calendar.set(getNowYear(), getNowMonth() - 1, day);
		return getDayEndTime(calendar.getTime());
	}

	// 获取指定时间月份的结束时间
	public static Date getEndDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		calendar.set(getNowYear(date), getNowMonth(date) - 1, 1);
		int day = calendar.getActualMaximum(5);
		calendar.set(getNowYear(date), getNowMonth(date) - 1, day);
		return getDayEndTime(calendar.getTime());
	}

	// 获取本年的开始时间
	public static java.util.Date getBeginDayOfYear() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, getNowYear());
		// cal.set
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DATE, 1);

		return getDayStartTime(cal.getTime());
	}

	// 获取本年的结束时间
	public static java.util.Date getEndDayOfYear() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, getNowYear());
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.set(Calendar.DATE, 31);
		return getDayEndTime(cal.getTime());
	}

	/**
	 *  
	 * (获取某个日期的开始时间)<BR>
	 * 方法名：getDayStartTime<BR>
	 * 创建人：shixiaofei <BR>
	 * 时间：2018年7月5日-下午6:09:01 <BR>
	 * @param d
	 * @return Timestamp<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	public static Timestamp getDayStartTime(Date d) {
		Calendar calendar = Calendar.getInstance();
		if (null != d)
			calendar.setTime(d);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0,
				0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return new Timestamp(calendar.getTimeInMillis());
	}
	
	/**
	 *  
	 * (将指定日期转换成毫秒格式日期)<BR>
	 * 方法名：getDayStartTime<BR>
	 * 创建人：shixiaofei <BR>
	 * 时间：2018年7月5日-下午6:09:01 <BR>
	 * @param d
	 * @return Timestamp<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	public static Timestamp getDateTimestamp(Date d) {
		Calendar calendar = Calendar.getInstance();
		if (null != d)
			calendar.setTime(d);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.MILLISECOND, 0);
		return new Timestamp(calendar.getTimeInMillis());
	}

	/**
	 * 
	 * (获取某个日期的结束时间)<BR>
	 * 方法名：getDayEndTime<BR>
	 * 创建人：shixiaofei <BR>
	 * 时间：2018年7月5日-下午6:09:12 <BR>
	 * @param d
	 * @return Timestamp<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	public static Timestamp getDayEndTime(Date d) {
		Calendar calendar = Calendar.getInstance();
		if (null != d)
			calendar.setTime(d);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23,
				59, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return new Timestamp(calendar.getTimeInMillis());
	}

	// 获取今年是哪一年
	public static Integer getNowYear() {
		Date date = new Date();
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		return Integer.valueOf(gc.get(1));
	}

	// 获取今年是哪一年
	public static Integer getNowYear(Date date) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		return Integer.valueOf(gc.get(1));
	}

	// 获取本月是哪一月
	public static int getNowMonth() {
		Date date = new Date();
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		return gc.get(2) + 1;
	}

	// 获取本月是哪一月
	public static int getNowMonth(Date date) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		return gc.get(2) + 1;
	}
	// 两个日期相减得到的天数
	public static int getDiffDays(Date beginDate, Date endDate) {

		if (beginDate == null || endDate == null) {
			throw new IllegalArgumentException("getDiffDays param is null!");
		}

		long diff = (endDate.getTime() - beginDate.getTime()) / (1000 * 60 * 60 * 24);

		int days = new Long(diff).intValue();

		return days;
	}

	// 两个日期相减得到的毫秒数
	public static long dateDiff(Date beginDate, Date endDate) {
		long date1ms = beginDate.getTime();
		long date2ms = endDate.getTime();
		return date2ms - date1ms;
	}

	// 获取两个日期中的最大日期
	public static Date max(Date beginDate, Date endDate) {
		if (beginDate == null) {
			return endDate;
		}
		if (endDate == null) {
			return beginDate;
		}
		if (beginDate.after(endDate)) {
			return beginDate;
		}
		return endDate;
	}

	// 获取两个日期中的最小日期
	public static Date min(Date beginDate, Date endDate) {
		if (beginDate == null) {
			return endDate;
		}
		if (endDate == null) {
			return beginDate;
		}
		if (beginDate.after(endDate)) {
			return endDate;
		}
		return beginDate;
	}

	// 返回某月该季度的第一个月
	public static Date getFirstSeasonDate(Date date) {
		final int[] SEASON = { 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4 };
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int sean = SEASON[cal.get(Calendar.MONTH)];
		cal.set(Calendar.MONTH, sean * 3 - 3);
		return cal.getTime();
	}

	// 返回某个日期下几天的日期
	public static Date getNextDay(Date date, int i) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) + i);
		return cal.getTime();
	}

	// 返回某个日期前几天的日期
	public static Date getFrontDay(Date date, int i) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) - i);
		return cal.getTime();
	}

	// 获取某年某月到某年某月按天的切片日期集合（间隔天数的日期集合）
	public static List getTimeList(int beginYear, int beginMonth, int endYear, int endMonth, int k) {
		List list = new ArrayList();
		if (beginYear == endYear) {
			for (int j = beginMonth; j <= endMonth; j++) {
				list.add(getTimeList(beginYear, j, k));

			}
		} else {
			{
				for (int j = beginMonth; j < 12; j++) {
					list.add(getTimeList(beginYear, j, k));
				}

				for (int i = beginYear + 1; i < endYear; i++) {
					for (int j = 0; j < 12; j++) {
						list.add(getTimeList(i, j, k));
					}
				}
				for (int j = 0; j <= endMonth; j++) {
					list.add(getTimeList(endYear, j, k));
				}
			}
		}
		return list;
	}

	// 获取某年某月按天切片日期集合（某个月间隔多少天的日期集合）
	public static List getTimeList(int beginYear, int beginMonth, int k) {
		List list = new ArrayList();
		Calendar begincal = new GregorianCalendar(beginYear, beginMonth, 1);
		int max = begincal.getActualMaximum(Calendar.DATE);
		for (int i = 1; i < max; i = i + k) {
			list.add(begincal.getTime());
			begincal.add(Calendar.DATE, k);
		}
		begincal = new GregorianCalendar(beginYear, beginMonth, max);
		list.add(begincal.getTime());
		return list;
	}

	/**
	 * 获取某段时这里写代码片间内的所有日期
	 * 
	 * @param dBegin
	 * @param dEnd
	 * @return
	 */
	public static List<Date> findDates(Date dBegin, Date dEnd) {
		List<Date> lDate = new ArrayList<Date>();
		lDate.add(dBegin);
		Calendar calBegin = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calBegin.setTime(dBegin);
		Calendar calEnd = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calEnd.setTime(dEnd);
		// 测试此日期是否在指定日期之后
		while (dEnd.after(calBegin.getTime())) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
			lDate.add(calBegin.getTime());
		}
		return lDate;
	}

}