package com.ts.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MoneyUtil {

	public static final BigDecimal B100 = BigDecimal.valueOf(100);
	public static final BigDecimal B10000 = BigDecimal.valueOf(10000);
	public static final DecimalFormat decimalFormat = new DecimalFormat("###################.##"); 
	
	/**
	 * 
	 * 将分转成元<BR>
	 * 方法名：cent2Yuan<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2017年1月11日-上午11:56:22 <BR>
	 * @param data
	 * @return double<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	public static double cent2Yuan(long data){
		BigDecimal bi = BigDecimal.valueOf(data);
		return bi.divide(B100).doubleValue();
	}
	
	/**
	 * 
	 * 分转元去<BR>
	 * 方法名：cent2YuanFloor<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2017年12月28日-上午11:35:00 <BR>
	 * @param data
	 * @return long<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	public static long cent2YuanFloor(long data){
		BigDecimal bi = BigDecimal.valueOf(data);
		return bi.divide(B100).longValue();
	}
	
	/**
	 * 
	 * 将元转成分<BR>
	 * 方法名：yuan2Cent<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2017年1月11日-上午11:56:48 <BR>
	 * @param data
	 * @return long<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	public static long yuan2Cent(double data){
		BigDecimal bi = BigDecimal.valueOf(data);
		return bi.multiply(B100).longValue();
	}
	
	public static long yuan2Cent(String data){
		BigDecimal bi = new BigDecimal(data.toCharArray());
		return bi.multiply(B100).longValue();
	}
	
	/**
	 * 
	 * 将数据库中万分之一转化为实际利率<BR>
	 * 方法名：realRate<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2017年2月15日-下午4:37:20 <BR>
	 * @param rate
	 * @return double<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	public static double realRate(long rate){
		BigDecimal bi = BigDecimal.valueOf(rate);
		return bi.divide(B10000).doubleValue();
	}
	
	/**
	 * 
	 * 计算还款费用<BR>
	 * 方法名：calRepayFee<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2018年1月8日-上午11:33:22 <BR>
	 * @param amount	金额
	 * @param period	期限
	 * @param rate		利率
	 * b＝a×i×（1＋i）^n÷〔（1＋i）^n－1〕
	 * @return Long<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
//	public static double calRepayFee(Long amount, int period, double rate) {
//		rate = rate / 12;
////        return (long) (amount * rate * Math.pow(1.0 + rate, period) / (Math.pow(1.0 + rate, period) - 1.0) + 1.0) + 1;
//        return  ((amount * rate * Math.pow(1.0 + rate, period)) / (Math.pow(1.0 + rate, period) - 1.0) * period);
//    }
	public static double calRepayFee(Long amount, int period, double rate) {
		// 计算月利率
		rate = rate / 12;
        return  ((amount * rate * Math.pow(1.0 + rate, period)) / (Math.pow(1.0 + rate, period) - 1.0));
    }
	
	/**
	 * 显示利率的数值转化为DB中存储的数值
	 *
	 * @param rate
	 * @return
	 */
	public static Integer viewRate2DBRate(Double rate){
		BigDecimal bi = BigDecimal.valueOf(rate);
		BigDecimal b2 = BigDecimal.valueOf(1000);
		return bi.multiply(b2).intValue();
	}
	
	/**
	 * 
	 * 费率单位转换<BR>
	 * 方法名：rateDesc<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2018年1月8日-上午11:22:10 <BR>
	 * @param rateUnit  1#天，2#周，3#月，4#年，5#综合费率
	 * @return String<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	public static String rateDesc(int rateUnit){
		String desc = "年费率";
		switch (rateUnit) {
		case 1:
			desc = "天费率";
			break;
		case 2:
			desc = "周费率";
			break;
		case 3:
			desc = "月费率";
			break;
		case 4:
			desc = "年费率";
			break;
		case 5:
			desc = "综合费率";
			break;

		default:
			desc = "年费率";
			break;
		}
		return desc;
	}
	
	/**
	 * 
	 * 格式化金额<BR>
	 * 方法名：formatDouble<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2018年2月11日-下午1:52:08 <BR>
	 * @param amount
	 * @return String<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	public static String formatDouble(double amount){
		return decimalFormat.format(amount);
	}
}
