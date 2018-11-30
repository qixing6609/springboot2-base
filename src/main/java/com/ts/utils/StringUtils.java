package com.ts.utils;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.google.common.base.CaseFormat;


public class StringUtils {
	
    /**
     * The empty String {@code ""}.
     * @since 2.0
     */
    public static final String EMPTY = "";
	private static final String CHARSET_NAME = "UTF-8";

    /**
     * 过滤mysql数据库的非法字符串
     * @param value
     * @return
     */
	public static String filterQueryValueForMySQL(String value){
		value = org.apache.commons.lang3.StringUtils.remove(value, "'");

        return value;
	}

	/**
	 * 处理mysql数据库的字符串转义,适用于sql
	 * @param value
	 * @return
	 */
	public static String escapeSQLValueForMySQL(String value){
        return escapeSQLValueForMySQL(value,true);
	}
	
	/**
	 * 处理mysql数据库的字符串转义,适用于sql
	 * @param value
	 * @param autosign
	 * @return
	 */
	public static String escapeSQLValueForMySQL(String value,boolean autosign){
		value = org.apache.commons.lang3.StringUtils.replace(value, "'",    "\\'");
		value = org.apache.commons.lang3.StringUtils.replace(value, "_",    "\\_");
		value = org.apache.commons.lang3.StringUtils.replace(value, "%",    "\\%");
		if(autosign){
		    value = "%" + value + "%";
		}
        return value;
	}
	
    /**
     * 转义like语句中的
     * <code>'_'</code><code>'%'</code>
     * 将<code>'?'</code>转成sql的<code>'/_'</code>
     * 将<code>'%'</code>转成sql的<code>'/%'</code>
     * <p>
     *   例如搜索<code>?aa*bb?c_d%f</code>将转化成<br/>
     *   <code>_aa%bb_c/_d/%f</code>
     * </p>
     * @param likeStr
     * @return
     * @author <a href="http://jdkcn.com">somebody</a>
     */
    public static String escapeSQLLike(String likeStr) {
    	
        String str = org.apache.commons.lang3.StringUtils.replace(likeStr, "_", "/_");
        str = org.apache.commons.lang3.StringUtils.replace(str, "%",    "/%");
        str = org.apache.commons.lang3.StringUtils.replace(str, "％",    "/％");
        str = org.apache.commons.lang3.StringUtils.replace(str, "'",    "''");
        str = org.apache.commons.lang3.StringUtils.replace(str, "?", "/?");
       // str = StringUtils.replace(str, "*", "%");
        str = org.apache.commons.lang3.StringUtils.replace(str, "/", "//");
        str = "%" + str + "%";
        return str;
    }
    
    public static String escapeSQLLike1(String likeStr) {
    	
        String str = org.apache.commons.lang3.StringUtils.replace(likeStr, "_", "/_");
        str = org.apache.commons.lang3.StringUtils.replace(str, "%",    "/%");
        str = org.apache.commons.lang3.StringUtils.replace(str, "％",    "/％");
        str = org.apache.commons.lang3.StringUtils.replace(str, "'",    "''");
        str = org.apache.commons.lang3.StringUtils.replace(str, "?", "/?");
        str = org.apache.commons.lang3.StringUtils.replace(str, "*", "%");
        str = org.apache.commons.lang3.StringUtils.replace(str, "/", "//");
        return str;
    }
	
    /**
     * 将Object转换成字符串
     * @param obj
     * @return
     */
    public static String convertObjectToString(Object obj){
    	if(obj==null){
    		return "";
    	}
    	else{
    		return obj.toString();
    	}
    }
    /**
     * 
     * (验证是否是正确的身份证号)<BR>
     * 方法名：isIdCard<BR>
     * 创建人：shixiaofei <BR>
     * 时间：2018年5月17日-下午2:23:30 <BR>
     * @param no
     * @return boolean<BR>
     * @exception <BR>
     * @since  2.0
     */
    public static boolean isIdCard(String no)
    {
       // 对身份证号进行长度等简单判断
       if (no == null || no.length() != 18 || !no.matches("\\d{17}[0-9X]"))
       {
          return false;
       }
       // 1-17位相乘因子数组
       int[] factor = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
       // 18位随机码数组
       char[] random = "10X98765432".toCharArray();
       // 计算1-17位与相应因子乘积之和
       int total = 0;
       for (int i = 0; i < 17; i++)
       {
          total += Character.getNumericValue(no.charAt(i)) * factor[i];
       }
       // 判断随机码是否相等
        return  random[total % 11] == no.charAt(17);
    }

    /**
     * 将Object转换成字符串,如果大于指定的长度缩略显示,可指定省略的字数,可设定前缀,可设定省略号格式
     * @param obj
     * @param length
     * @param less
     * @param prefix
     * @param suspensionpoints
     * @return
     */
    public static String convertObjectToString(Object obj,int length,int less,String prefix,String suspensionpoints){
    	if(obj==null){
    		return "";
    	}
    	else{
    		String str = obj.toString();
    		
    		if(org.apache.commons.lang3.StringUtils.isEmpty(str)){
    			return "";
    		}
    		else{
	    		if(str.length()<=length){
	    			return str;
	    		}
	    		else{
	    			str = org.apache.commons.lang3.StringUtils.substring(obj.toString(), 0,length-less) + suspensionpoints;
	    		    return prefix + str;
	    		} 		
    		}
    	}
    }

    /**
     * 将Object转换成字符串,如果大于指定的长度缩略显示,可指定省略的字数,可设定省略号格式
     * @param obj
     * @param length
     * @param less
     * @param prefix
     * @param suspensionpoints
     * @return
     */
    public static String convertObjectToString(Object obj,int length,int less,String suspensionpoints){
        return convertObjectToString(obj,length,less,"",suspensionpoints);
    }
    
    /**
     * 将Object转换成字符串,如果大于指定的长度缩略显示,可指定省略的字数
     * @param obj
     * @param length
     * @param less
     * @param prefix
     * @param suspensionpoints
     * @return
     */
    public static String convertObjectToString(Object obj,int length,int less){
        return convertObjectToString(obj,length,less,"","…");
    }    
    
    /**
     * 将Object转换成字符串,如果大于指定的长度缩略显示
     * @param obj
     * @param length
     * @return
     */
    public static String convertObjectToString(Object obj,int length){
    	return convertObjectToString(obj,length,1,"","…");
    }
	
    /**
     * 在指定字符串之前追加字符串
     * @param str
     * @param addition
     * @return
     */
    public static String additionBefore(String str ,String addition){
    	if(org.apache.commons.lang3.StringUtils.isNotEmpty(str)){
    		return str = org.apache.commons.lang3.StringUtils.isNotEmpty(addition)?addition + str:str;
    	}
    	else{
    		return addition;
    	}
    }

    /**
     * 在指定字符串之后追加字符串
     * @param str
     * @param addition
     * @return
     */
    public static String additionAfter(String str ,String addition){
    	if(org.apache.commons.lang3.StringUtils.isNotEmpty(str)){
    		return str = org.apache.commons.lang3.StringUtils.isNotEmpty(addition)?str + addition:str;
    	}
    	else{
    		return addition;
    	}
    }
    
    /**
     * 将指定的字符串旧的charset转换成新的charset
     * @param str
     * @param oldCharset
     * @param newCharset
     * @return
     */
    public static String convertCharset(String str,String oldCharset,String newCharset){
    	try {
			return new String(str.getBytes(oldCharset),newCharset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return str;
		}
    }
    
    public static String replaceIndicatedPosition(String text,char with,int position){
    	if(org.apache.commons.lang3.StringUtils.isEmpty(text)){
    		return text;
    	}
    	else{
    		if((text.length()-1)<position){
    			return text;
    		}
    		else{
    			String start = text.substring(0, position);
    			String end = text.substring(position+1, text.length());
    			String result = start + with + end;
    			return result;
    		}
    	}
    }
    
    /**
     * 将类似[1:a],[2:b],[3:c]格式的字符串以第一个分隔符分隔成List,
     * 再把List中的每个字符以第二个分隔符分隔成字符数组
     * @param dataString
     * @param firstSeparator
     * @param secondSeparator
     * @return
     */
    public static List<String[]> splitDataStringToList(String dataString,String firstSeparator,String secondSeparator){
    	List<String[]> list = new ArrayList<String[]>();
    	if(org.apache.commons.lang3.StringUtils.isNotBlank(dataString)){
    		String[] datas = org.apache.commons.lang3.StringUtils.split(dataString, firstSeparator);
    		if(datas!=null){
    			//System.out.println(datas.length);
    			for(String data : datas){
    				String[] child = org.apache.commons.lang3.StringUtils.split(data, secondSeparator);
    				list.add(child);
    			}
    		}
    	}
    	return list;
    }
    
    /**
     * 将原有字符串，以指定的分隔符分开，给每一个字符串项加上指定的前缀和后缀，再以原来的分隔符连接起来
     * 例：1,2,3 -> '1','2','3'
     * @param strcoll
     * @param separator
     * @param prefix
     * @param suffix
     * @return
     */
//	public static String addPrefixAndSuffixForEachStringItem(String strcoll,String separator,String prefix,String suffix){
//		String result = "";
//		if(org.apache.commons.lang3.StringUtils.isNotBlank(strcoll)){
//			String[] ids = org.apache.commons.lang3.StringUtils.split(strcoll, separator);
//			List<String> a = new ArrayList<String>();
//			org.apache.commons.collections.CollectionUtils.addAll(a, ids);
//			result = org.springframework.util.StringUtils.collectionToDelimitedString(a, separator, prefix, suffix);
//		}
//		return result;
//	}
    
	/**
	 * 去掉首尾相同字符串
	 * @param str
	 * @param remove
	 * @return
	 */
	public static String removeStartAndEnd(String str,String remove){
		return removeStartAndEnd(str,remove,remove);
	}
	
	/**
	 * 去掉首尾的指定的字符串
	 * @param str
	 * @param removestart
	 * @param removeend
	 * @return
	 */
	public static String removeStartAndEnd(String str,String removestart,String removeend){
		if(str==null)
			return null;
		
		str = org.apache.commons.lang3.StringUtils.removeStart(str, removestart);
		str = org.apache.commons.lang3.StringUtils.removeEnd(str, removeend);
		return str;
	}
	
	/**
	 * 根据指定的开始字符串和结束字符串替换中间的内容
	 * @param str
	 * @param open
	 * @param close
	 * @return
	 */
	public static String substringBetweenIgnoreCase(String str, String open, String close) {
		if (str == null || open == null || close == null)
			return null;
		str = str.toLowerCase();
		int start = str.indexOf(open.toLowerCase());
		if (start != -1) {
			int end = str.indexOf(close.toLowerCase(), start + open.length());
			if (end != -1)
				return str.substring(start + open.length(), end);
		}
		return null;
	}
	
    
    /**
     * 空文字列判定
     *
     * @param input 入力文字列
     * @return true/false
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input)||"null".equals(input)) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 生成投资人昵称
     * @param userIdPart1
     * @return
     */
    public static String getTenderUserId(String userRole) {
    	String userIdPart1 = "";
		String userIdPart2 = "";
		String userId;
		
    	switch (userRole) {
		case "1":
			userIdPart1 = "SDT"+userRole;
			break;
		case "2":
			userIdPart1 = "SDR"+userRole;
			break;
		default:
			userIdPart1 = "SDT1";
		}
    	
		//5-21位
		userIdPart1 += DateUtil.convertDate2String(new Date(), DateUtil.DATE_TIME_LOCALE_STAMP);
		//22-25位
		Random random = new Random();
		
		for (int i=0 ; i<4; i++) {
			userIdPart2 += random.nextInt(9);
		}
		userId = userIdPart1 + userIdPart2;
			
		return userId;
	}
    
    public static String leftPad(String str, int length, char paddingChar) {
        str = org.apache.commons.lang3.StringUtils.leftPad(str, length, paddingChar);
        return str;
    }
    
    /**
     * 转换为字节数组
     * @param str
     * @return
     */
    public static byte[] getBytes(String str){
    	if (str != null){
    		try {
				return str.getBytes(CHARSET_NAME);
			} catch (UnsupportedEncodingException e) {
				return null;
			}
    	}else{
    		return null;
    	}
    }
    
    /**
     * 转换为字节数组
     * @param str
     * @return
     */
    public static String toString(byte[] bytes){
    	try {
			return new String(bytes, CHARSET_NAME);
		} catch (UnsupportedEncodingException e) {
			return EMPTY;
		}
    }
    
    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }
    
    /**
     * <p>Checks if a CharSequence is whitespace, empty ("") or null.</p>
     *
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     *
     * @param cs  the CharSequence to check, may be null
     * @return {@code true} if the CharSequence is null, empty or whitespace
     * @since 2.0
     * @since 3.0 Changed signature from isBlank(String) to isBlank(CharSequence)
     */
    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }
    
    /**
	 * 功能：身份证的有效验证
	 *
	 * @param IDStr
	 *            身份证号
	 * @return 有效：返回"" 无效：返回String信息
	 * @throws ParseException
	 */
	public static String checkIdCard(String IDStr) throws ParseException {
		String errorInfo = "";// 记录错误信息
		String[] ValCodeArr = { "1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2" };
		String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2" };
		String Ai = "";
		// ================ 号码的长度 15位或18位 ================
		if (IDStr.length() != 15 && IDStr.length() != 18) {
			errorInfo = "身份证号码不对。";
			return errorInfo;
		}
		// =======================(end)========================

		// ================ 数字 除最后以为都为数字 ================
		if (IDStr.length() == 18) {
			Ai = IDStr.substring(0, 17);
		} else if (IDStr.length() == 15) {
			Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
		}
		if (isNumeric(Ai) == false) {
			errorInfo = "身份证号码不对";
			return errorInfo;
		}
		// =======================(end)========================

		// ================ 出生年月是否有效 ================
		String strYear = Ai.substring(6, 10);// 年份
		String strMonth = Ai.substring(10, 12);// 月份
		String strDay = Ai.substring(12, 14);// 月份
		if (isDate(strYear + "-" + strMonth + "-" + strDay) == false) {
			errorInfo = "身份证生日无效。";
			return errorInfo;
		}
		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
				|| (gc.getTime().getTime() - s.parse(strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
			errorInfo = "身份证生日不在有效范围。";
			return errorInfo;
		}
		if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
			errorInfo = "身份证月份无效";
			return errorInfo;
		}
		if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
			errorInfo = "身份证日期无效";
			return errorInfo;
		}
		// =====================(end)=====================

		// ================ 地区码时候有效 ================
		Hashtable<String, String> h = getAreaCode();
		if (h.get(Ai.substring(0, 2)) == null) {
			errorInfo = "身份证地区编码错误。";
			return errorInfo;
		}
		// ==============================================

		// ================ 判断最后一位的值 ================
		int TotalmulAiWi = 0;
		for (int i = 0; i < 17; i++) {
			TotalmulAiWi = TotalmulAiWi + Integer.parseInt(String.valueOf(Ai.charAt(i))) * Integer.parseInt(Wi[i]);
		}
		int modValue = TotalmulAiWi % 11;
		String strVerifyCode = ValCodeArr[modValue];
		Ai = Ai + strVerifyCode;

		if (IDStr.length() == 18) {
			if (Ai.equals(IDStr.toLowerCase()) == false) {
				errorInfo = "身份证无效";
				return errorInfo;
			}
		} else {
			return "";
		}
		// =====================(end)=====================
		return "";
	}

	/**
	 * 功能：设置地区编码
	 *
	 * @return Hashtable 对象
	 */
	private static Hashtable<String, String> getAreaCode() {
		Hashtable<String, String> hashtable = new Hashtable<>();
		hashtable.put("11", "北京");
		hashtable.put("12", "天津");
		hashtable.put("13", "河北");
		hashtable.put("14", "山西");
		hashtable.put("15", "内蒙古");
		hashtable.put("21", "辽宁");
		hashtable.put("22", "吉林");
		hashtable.put("23", "黑龙江");
		hashtable.put("31", "上海");
		hashtable.put("32", "江苏");
		hashtable.put("33", "浙江");
		hashtable.put("34", "安徽");
		hashtable.put("35", "福建");
		hashtable.put("36", "江西");
		hashtable.put("37", "山东");
		hashtable.put("41", "河南");
		hashtable.put("42", "湖北");
		hashtable.put("43", "湖南");
		hashtable.put("44", "广东");
		hashtable.put("45", "广西");
		hashtable.put("46", "海南");
		hashtable.put("50", "重庆");
		hashtable.put("51", "四川");
		hashtable.put("52", "贵州");
		hashtable.put("53", "云南");
		hashtable.put("54", "西藏");
		hashtable.put("61", "陕西");
		hashtable.put("62", "甘肃");
		hashtable.put("63", "青海");
		hashtable.put("64", "宁夏");
		hashtable.put("65", "新疆");
		hashtable.put("71", "台湾");
		hashtable.put("81", "香港");
		hashtable.put("82", "澳门");
		hashtable.put("91", "国外");
		return hashtable;
	}

	/**
	 * 功能：判断字符串是否为日期格式
	 *
	 * @param str
	 * @return
	 */
	public static boolean isDate(String strDate) {
		Pattern pattern = Pattern
				.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
		Matcher m = pattern.matcher(strDate);
		if (m.matches()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 校验银行卡卡号
	 *
	 * @param cardId
	 * @return
	 */
	public static boolean checkBankCard(String cardId) {
		if (StringUtils.isEmpty(cardId)) {
			return false;
		}
		if (!isNumeric(cardId)) {
			return false;
		}
		if (cardId.length() >= 12) {
			return true;
		} else {
			return false;
		}
		// char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() -
		// 1));
		// if (bit == 'N') {
		// return false;
		// }
		// return cardId.charAt(cardId.length() - 1) == bit;
		// return true;
	}
	
	public static boolean isNumeric(String input) {
		if (StringUtils.isEmpty(input)) {
			return false;
		}
		if (input.indexOf(".") != input.lastIndexOf(".")) {
			return false;
		}
		input = input.replace(".", "");
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if ((c < '0' || c > '9')) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * 字符串中间隐藏加****<BR>
	 * 方法名：concealCenter<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2017年2月24日-下午4:56:37 <BR>
	 * @param str
	 * @return String<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	public static String concealCenter(String str){
		StringBuilder builder = new StringBuilder();
		if (!isBlank(str) && str.length() > 8) {
			builder.append(str.substring(0, 3));
			builder.append("****");
			builder.append(str.substring(str.length()-4));
			return builder.toString();
		}
		return str;
	}
	
	/**
	 * 
	 * 姓名脱敏<BR>
	 * 方法名：concealRealName<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2018年5月9日-上午10:18:23 <BR>
	 * @param str
	 * @return String<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	public static String concealRealName(String str){
		StringBuilder builder = new StringBuilder();
		if (!isBlank(str) && str.length() > 2) {
			builder.append(str.substring(0, 1));
			builder.append("*");
			builder.append(str.substring(str.length()-1));
			return builder.toString();
		}
		if (!isBlank(str) && str.length() == 2) {
			builder.append(str.substring(0, 1));
			builder.append("*");
			return builder.toString();
		}
		return str;
	}
	
	/**
	 * 
	 * 字符串中间隐藏加****<BR>
	 * 方法名：concealCenter<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2017年2月24日-下午4:56:37 <BR>
	 * @param str
	 * @return String<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	public static String bankConcealCenter(String str){
		StringBuilder builder = new StringBuilder();
		if (!isBlank(str) && str.length() > 8) {
			builder.append(str.substring(0, 4));
			builder.append(" **** **** ");
			builder.append(str.substring(str.length()-4));
			return builder.toString();
		}
		return str;
	}
	/**
	 * 
	 * 字符串后四位前隐藏加****<BR>
	 * 方法名：strKeepTheLastFour<BR>
	 * 创建人：史晓飞 <BR>
	 * 时间：2018年5月8日-下午4:56:37 <BR>
	 * @param str
	 * @return String<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	public static String strKeepTheLastFour(String str){
		StringBuilder builder = new StringBuilder();
		if (!isBlank(str) && str.length() > 4) {
			for (int i = 0; i < str.length()-4; i++) {
				builder.append("*");
			}
			builder.append(str.substring(str.length()-4));
			return builder.toString();
		}
		return str;
	}
	/**
	 * 
	 * 取字符串suffixLength个后缀<BR>
	 * 方法名：suffix<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2017年2月24日-下午5:00:40 <BR>
	 * @param str
	 * @param suffixLength
	 * @return String<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	public static String suffix(String str, int suffixLength){
		if (!isBlank(str) && str.length() > suffixLength) {
			return str.substring(str.length()-suffixLength);
		}
		return str;
	}
	
	/**
	 * 
	 * 获取html第一img的src<BR>
	 * 方法名：htmlFisrtImgSrc<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2017年12月22日-下午7:17:54 <BR>
	 * @param html
	 * @return String<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	public static String htmlFisrtImgSrc(String html){
//		Document doc=Jsoup.parse(html);
//        Elements imgs=doc.select("img[src]");
//        if (imgs != null && imgs.size() > 0) {
//			return imgs.get(0).toString();
//		}
        return "";
	}
	
	/**
	 * 将emoji表情替换成*
	 *
	 * @param source
	 * @return 过滤后的字符串
	 */
	public static String filterEmoji(String source) {
		if(StringUtils.isNotBlank(source)){
			return source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "*");
		}else{
			return source;
		}
	}
	
	/**
	 * 将大写字母改成小写，并在成后的小写字母前加— 如：TestStringName  改成 test_string_name
	 */
	public static String  str_lower_camel(String str){
		str = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, str);
		System.out.println(str+"-------------");
		return str;
	}
}
