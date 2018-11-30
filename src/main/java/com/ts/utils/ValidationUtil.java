package com.ts.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.ts.exception.WebBusinessException;




/**
 * 
 * 针对json数据验证
 * ValidationUtil<BR>
 * 创建人:wangbeidou <BR>
 * 时间：2016年12月19日-下午4:28:55 <BR>
 * @version 2.0
 *
 */
public class ValidationUtil {
    public ValidationUtil() {
    }

    public static void isTrue(String paramName, boolean expression, String message) {
        if(!expression) {
        	throw new WebBusinessException(paramName, message);
        }
    }

    public static void isTrue(String paramName, boolean expression) {
        isTrue(paramName, expression, "[Assertion failed] - this expression must be true");
    }
    
    public static void isFalse(String paramName, boolean expression, String message) {
    	if(expression) {
    		throw new WebBusinessException(paramName, message);
    	}
    }

    public static void isNull(String paramName, Object object, String message) {
        if(object != null) {
        	throw new WebBusinessException(paramName, message);
        }
    }

    public static void isNull(String paramName, Object object) {
        isNull(paramName, object, "[Assertion failed] - the object argument must be null");
    }

    public static void isBlank(String paramName, Object object, String message) {
    	if(!StringUtils.isEmpty(object)) {
    		throw new WebBusinessException(paramName, message);
    	}
    }
    
    public static void notBlank(String paramName, Object object, String message) {
        if(StringUtils.isEmpty(object)) {
        	throw new WebBusinessException(paramName, message);
        }
    }
    public static void notNull(String paramName, Object object, String message) {
    	if(object == null) {
    		throw new WebBusinessException(paramName, message);
    	}
    }
    
    public static void greaterZero(String paramName, long data, String message) {
    	if(data <= 0) {
    		throw new WebBusinessException(paramName, message);
    	}
    }
    
    public static void greaterZero(String paramName, Double data, String message) {
    	if(data == null ||data <= 0) {
    		throw new WebBusinessException(paramName, message);
    	}
    }
    
    public static void greaterZero(String paramName, Integer data, String message) {
    	if(data == null || data <= 0) {
    		throw new WebBusinessException(paramName, message);
    	}
    }
    
    public static void greater(String paramName, int data1, int data2, String message) {
    	if(data1 <= data2) {
    		throw new WebBusinessException(paramName, message);
    	}
    }
    public static void greater(String paramName, double data1, double data2, String message) {
    	if(data1 <= data2) {
    		throw new WebBusinessException(paramName, message);
    	}
    }
    public static void greater(String paramName, long data1, long data2, String message) {
    	if(data1 <= data2) {
    		throw new WebBusinessException(paramName, message);
    	}
    }

    public static void notNull(String paramName, Object object) {
        notNull(paramName, object, "[Assertion failed] - this argument is required; it must not be null");
    }

    public static void hasLength(String paramName, String text, String message) {
        if(!StringUtils.hasLength(text)) {
        	throw new WebBusinessException(paramName, message);
        }
    }

    public static void hasLength(String paramName, String text) {
        hasLength(paramName, text, "[Assertion failed] - this String argument must have length; it must not be null or empty");
    }

    public static void hasText(String paramName, String text, String message) {
        if(!StringUtils.hasText(text)) {
        	throw new WebBusinessException(paramName, message);
        }
    }

    public static void hasText(String paramName, String text) {
        hasText(paramName, text, "[Assertion failed] - this String argument must have text; it must not be null, empty, or blank");
    }

    public static void doesNotContain(String paramName, String textToSearch, String substring, String message) {
        if(StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring) && textToSearch.indexOf(substring) != -1) {
        	throw new WebBusinessException(paramName, message);
        }
    }
    
    /**
     * 
     * contain中包含某个text<BR>
     * 方法名：contain<BR>
     * 创建人：wangbeidou <BR>
     * 时间：2016年12月21日-下午2:01:43 <BR>
     * @param paramName
     * @param text
     * @param contain
     * @param message void<BR>
     * @exception <BR>
     * @since  2.0
     */
    public static void contain(String paramName, String text, String[] contain, String message) {
    	if(StringUtils.hasLength(text) && !Arrays.asList(contain).contains(text)) {
    		throw new WebBusinessException(paramName, message);
    	}
    }
    
    /**
     * 
     * contain中不包含某个text<BR>
     * 方法名：notContain<BR>
     * 创建人：wangbeidou <BR>
     * 时间：2017年4月6日-下午4:16:23 <BR>
     * @param paramName
     * @param text
     * @param contain
     * @param message void<BR>
     * @exception <BR>
     * @since  2.0
     */
    public static void notContain(String paramName, String text, String[] contain, String message) {
    	if(StringUtils.hasLength(text) && Arrays.asList(contain).contains(text)) {
    		throw new WebBusinessException(paramName, message);
    	}
    }

    public static void doesNotContain(String paramName, String textToSearch, String substring) {
        doesNotContain(paramName, textToSearch, substring, "[Assertion failed] - this String argument must not contain the substring [" + substring + "]");
    }

    public static void notEmpty(String paramName, Object[] array, String message) {
        if(ObjectUtils.isEmpty(array)) {
        	throw new WebBusinessException(paramName, message);
        }
    }

    public static void notEmpty(String paramName, Object[] array) {
        notEmpty(paramName, array, "[Assertion failed] - this array must not be empty: it must contain at least 1 element");
    }

    public static void noNullElements(String paramName, Object[] array, String message) {
        if(array != null) {
            for(int i = 0; i < array.length; ++i) {
                if(array[i] == null) {
                	throw new WebBusinessException(paramName, message);
                }
            }
        }

    }

    public static void noNullElements(String paramName, Object[] array) {
        noNullElements(paramName, array, "[Assertion failed] - this array must not contain any null elements");
    }

    public static void notEmpty(String paramName, Collection<?> collection, String message) {
        if(CollectionUtils.isEmpty(collection)) {
        	throw new WebBusinessException(paramName, message);
        }
    }

    public static void notEmpty(String paramName, Collection<?> collection) {
        notEmpty(paramName, collection, "[Assertion failed] - this collection must not be empty: it must contain at least 1 element");
    }

    public static void notEmpty(String paramName, Map<?, ?> map, String message) {
        if(CollectionUtils.isEmpty(map)) {
        	throw new WebBusinessException(paramName, message);
        }
    }

    public static void notEmpty(String paramName, Map<?, ?> map) {
        notEmpty(paramName, map, "[Assertion failed] - this map must not be empty; it must contain at least one entry");
    }

    public static void regexCheck(String paramName, String data, String regex, String message) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);
        boolean isMatched = matcher.matches();
        if(!isMatched) {
        	throw new WebBusinessException(paramName, message);
        }
    }
    
    /**
     * 
     * 判断字符串是否数字，小数点后最多两位<BR>
     * 方法名：isNumber<BR>
     * 创建人：wangbeidou <BR>
     * 时间：2017年3月22日-上午11:24:28 <BR>
     * @param paramName
     * @param data
     * @param message void<BR>
     * @exception <BR>
     * @since  2.0
     */
    public static void isNumber(String paramName, String data, String message){
    	if(!StringUtils.isEmpty(data)){
    		regexCheck(paramName, data, "^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$", message);
    	}
    }
    
    /**
     * 
     * 判断字符串是否为整数<BR>
     * 方法名：isInteger<BR>
     * 创建人：shixiaofei <BR>
     * 时间：2018年7月23日-上午11:24:28 <BR>
     * @param paramName
     * @param data
     * @param message void<BR>
     * @exception <BR>
     * @since  2.0
     */
    public static void isInteger(String paramName, String data, String message){
    	if(!StringUtils.isEmpty(data)){
    		regexCheck(paramName, data, "^[-\\+]?[\\d]*$", message);
    	}
    }
    
    public static void isMobile(String paramName, String data, String message){
    	if(!StringUtils.isEmpty(data)){
    		regexCheck(paramName, data, "^(1[3|4|5|6|7|8|9][0-9])\\d{8}$", message);
    	}
    }
    /**
     * 
    * @Title: isWeakPwd  
    * @Description: 验证弱密码 必须包含数字加字母 长度6-16 
    * @param @param paramName
    * @param @param data
    * @param @param message
    * @return void 
    * @throws
     */
    public static void isWeakPwd(String paramName, String data, String message){
    	if(!StringUtils.isEmpty(data)){
    		regexCheck(paramName, data, "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$", message);
    	}
    }
    
    public static void isEmail(String paramName, String data, String message){
    	if(!StringUtils.isEmpty(data)){
    		regexCheck(paramName, data, "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w{2,3}){1,3})$", message);
    	}
    }
    
    public static void positive(String paramName, String data, String message){
    	regexCheck(paramName, data, "^\\d+$", message);
    }

    public static void checkRange(String paramName, String data, int start, int end, String message) {
        data = data.trim();
//    	if(!StringUtils.isEmpty(data)){
    		int l = data.length();
    		if(l < start || l > end) {
    			throw new WebBusinessException(paramName, message);
    		}
//    	}
    }
    
    /**
     * 描述：匹配用户名
     * 方法名：checkUsername
     * 创建人：gaojy
     * 时间：2017年3月10日下午3:38:05
     * @param paramName
     * @param data
     * @param start
     * @param end
     * @param message
     */
    public static void checkUsername(String paramName,String data,int start, int end,String message){
    	String regStr = "^[\\u4e00-\\u9fa5_a-zA-Z0-9-]{"+start+","+end+"}$";
    	if(!data.matches(regStr)){
    		throw new WebBusinessException(paramName, message);
    	}
    }
    
    public static void checkLength(String paramName, String data, int length, String message) {
    	data = data.trim();
    	if(!StringUtils.isEmpty(data)){
	    	int l = data.length();
	    	if(l != length) {
	    		throw new WebBusinessException(paramName, message);
	    	}
    	}
    }
    
    public static void verLength(String paramName, String data, int length, String message) {
    	data = data.trim();
    	if(!StringUtils.isEmpty(data)){
	    	int l = data.length();
	    	if(l > length) {
	    		throw new WebBusinessException(paramName, message);
	    	}
    	}
    }
    
    public static void throwException(String paramName, String msg){
    	throw new WebBusinessException(paramName, msg);
    }
    
    /**
     * 
     * 成功返回json<BR>
     * 方法名：successReturen<BR>
     * 创建人：wangbeidou <BR>
     * 时间：2017年1月9日-下午1:45:52 <BR>
     * @return String<BR>
     * @exception <BR>
     * @since  2.0
     */
    public static String successReturn(){
    	return JsonResult.newInstance().setData(new HashMap<>()).json();
    }
    
    /**
     * 
     * 成功返回并设置数据<BR>
     * 方法名：successReturn<BR>
     * 创建人：wangbeidou <BR>
     * 时间：2018年5月14日-下午6:09:42 <BR>
     * @param data
     * @return String<BR>
     * @exception <BR>
     * @since  2.0
     */
    public static String successReturn(Object data){
    	return JsonResult.newInstance().setData(data).json();
    }
    
    /**
     * 
     * 返回成功及描述<BR>
     * 方法名：successReturn<BR>
     * 创建人：wangbeidou <BR>
     * 时间：2018年2月2日-下午3:39:48 <BR>
     * @param msg
     * @return String<BR>
     * @exception <BR>
     * @since  2.0
     */
    public static String successReturn(String msg){
    	return JsonResult.newInstance().msg(msg).setData(new HashMap<>()).json();
    }
    
    /**
     * 
     * 无权限json<BR>
     * 方法名：successReturen<BR>
     * 创建人：wangbeidou <BR>
     * 时间：2017年1月9日-下午1:45:52 <BR>
     * @return String<BR>
     * @exception <BR>
     * @since  2.0
     */
    public static String noAuth(){
    	return JsonResult.newInstance().error(ResponseMsgEnum.SESSION_OUT_DATE).setData(new HashMap<>()).json();
    }
    
    /**
     * 
     * 操作失败json<BR>
     * 方法名：failureReturn<BR>
     * 创建人：wangbeidou <BR>
     * 时间：2017年1月9日-下午1:48:27 <BR>
     * @return String<BR>
     * @exception <BR>
     * @since  2.0
     */
    public static String failureReturn(){
    	return JsonResult.newInstance().error(ResponseMsgEnum.FAILURE).setData(new HashMap<>()).json();
    }
    
    /**
     * 
     * 操作失败json<BR>
     * 方法名：failureReturn<BR>
     * 创建人：wangbeidou <BR>
     * 时间：2017年1月9日-下午1:48:27 <BR>
     * @return String<BR>
     * @exception <BR>
     * @since  2.0
     */
    public static String failureReturn(String msg){
    	return JsonResult.newInstance().error(msg).setData(new HashMap<>()).json();
    }
    
    /**
     * 
     * 内部异常<BR>
     * 方法名：innerException<BR>
     * 创建人：wangbeidou <BR>
     * 时间：2017年1月17日-上午10:15:27 <BR>
     * @return String<BR>
     * @exception <BR>
     * @since  2.0
     */
    public static String innerException(){
    	return JsonResult.newInstance().error(ResponseMsgEnum.INNER_EXCEPTION).setData(new HashMap<>()).json();
    }
    
    /**
     * 
     * 返回错误json<BR>
     * 方法名：respErrMsg<BR>
     * 创建人：wangbeidou <BR>
     * 时间：2016年12月20日-下午5:03:51 <BR>
     * @param paramName
     * @param msg
     * @return String<BR>
     * @exception <BR>
     * @since  2.0
     */
    public static String respParamErrMsg(String paramName, String msg){
    	return JsonResult.newInstance().error(ResponseMsgEnum.ERROR_PARAM).param(paramName).msg(msg).setData(new HashMap<>()).json();
    }
    /**
     * 
     * 返回内部异常错误json<BR>
     * 方法名：respErrMsg<BR>
     * 创建人：wangbeidou <BR>
     * 时间：2016年12月20日-下午5:03:51 <BR>
     * @param paramName
     * @param msg
     * @return String<BR>
     * @exception <BR>
     * @since  2.0
     */
    public static String respExceptionErrMsg(String msg){
    	return JsonResult.newInstance().error(ResponseMsgEnum.INNER_EXCEPTION).msg(msg).setData(new HashMap<>()).json();
    }


}
