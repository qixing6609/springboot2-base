package com.ts.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.GsonBuilder;

/**
 * 
 * Json返回处理工具
 * JsonResult<BR>
 * 创建人:wangbeidou <BR>
 * 时间：2016年12月8日-上午10:50:45 <BR>
 * @version 2.0
 *
 */
public class JsonResult {
	private static Logger logger = LoggerFactory.getLogger(JsonResult.class);
	
	//版本
	private String version;
	//返回码
	private int code;
	//参数名称
	private String paramName;
	//返回信息
	private String msg;
	//返回数据
	private Object data;
	//分页
	private Pagination page;
	//返回Page信息
	private Map<String, Object> paging; 
	//返回数据暂存缓冲区
	private transient Map<String, Object> dataTemp ;
	//Gson实例
	private static GsonBuilder GSON_BUILDER = new GsonBuilder();
	
	public static JsonResult newInstance() {
		JsonResult instance = new JsonResult();
		//默认返回信息
		instance.version = "1";
		instance.code = ResponseMsgEnum.SUCCESS.getCode();
		instance.msg = ResponseMsgEnum.SUCCESS.getMsg();
//		instance.data = new Object();

//		if (ThreadContent.pagination() != null) {
//			instance.put("pager", ThreadContent.pagination());
//		}
		return instance;
	}

	public int getCode() {
		return this.code;
	}

	public String getMsg() {
		return this.msg;
	}

	public Object getData() {
		return this.data;
	}
	
	public Map<String, Object> getPaging() {
		return paging;
	}

	/**
	 * 
	 * 设置返回数据，该方法只能单独使用一次，多次调用只保存最后一次数据，并且不能与put方法混用<BR>
	 * 方法名：setData<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2017年5月22日-上午11:22:42 <BR>
	 * @param data
	 * @return JsonResult<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	public JsonResult setData(Object data){
		this.data = data;
		return this;
	}

	public JsonResult code(int code) {
		this.code = code;
		return this;
	}

	public JsonResult msg(String msg) {
		this.msg = msg;
		return this;
	}
	
	public JsonResult page(Pagination page){
		this.setPage(page);
		return this;
	}

	public JsonResult setPaging(Map<String, Object> paging) {
		this.paging = paging;
		return this;
	}

	/**
	 * 
	 * 按key-value的方式设置返回数据，可多次使用，最终是多次使用的数据集合，但不能与setData方法混用<BR>
	 * 方法名：put<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2017年5月22日-上午11:25:47 <BR>
	 * @param key
	 * @param value
	 * @return JsonResult<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	public JsonResult put(String key, Object value) {
		if (this.dataTemp == null) {
			this.dataTemp = new HashMap<String, Object>();
		}
		this.dataTemp.put(key, value);
		return this;
	}
	
	/**
	 * 
	 * 按设置集合的方式设置返回数据，可多次使用，最终是多次使用的数据集合，但不能与setData方法混用<BR>
	 * 方法名：put<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2017年5月22日-上午11:27:37 <BR>
	 * @param data
	 * @return JsonResult<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	public JsonResult put(Map<String, Object> data) {
		if (this.dataTemp == null) {
			this.dataTemp = new HashMap<String, Object>();
		}
		this.dataTemp.putAll(data);
		return this;
	}
	
	//将该对象转换为json格式
	public String json() {
		//如果使用put设置返回数据，将使用put设置的数据，放置到data区
		if (this.dataTemp != null) {
			this.data = this.dataTemp;
		}
		String result = GSON_BUILDER.setDateFormat(DateUtil.DATE_TIME_LOCALE_TIMESTAMP).create().toJson(this);
//		logger.info("请求返回数据:{}", result);
		return result;
	}
	
	//token过期信息
	public JsonResult tokenFailure() {
		this.code = ResponseMsgEnum.SESSION_OUT_DATE.getCode();
		this.msg = ResponseMsgEnum.SESSION_OUT_DATE.getMsg();
		return this;
	}

	//默认失败
	public JsonResult error() {
		this.code = ResponseMsgEnum.FAILURE.getCode();
		this.msg = ResponseMsgEnum.FAILURE.getMsg();
		return this;
	}
	
	//自定义返回信息失败
	public JsonResult error(String msg) {
		this.code = ResponseMsgEnum.FAILURE.getCode();
		if (StringUtils.isEmpty(msg)) {
			this.msg = "未知错误！";
		}else {
			this.msg = msg;
		}
		return this;
	}
	
	//自定义返回码和返回信息的失败
	public JsonResult error(int code, String msg) {
		this.code = code;
		this.msg = msg;
		return this;
	}
	
	//参数名称和返回信息
	public JsonResult error(String paramName, String msg) {
		this.paramName = paramName;
		this.msg = msg;
		return this;
	}
	
	//返回常用枚举返回信息
	public JsonResult error(ResponseMsgEnum respMsg){
		this.code = respMsg.getCode();
		this.msg = respMsg.getMsg();
		return this;
	}
	
	//返回参数名称
	public JsonResult param(String paramName){
		this.paramName = paramName;
		return this;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public Pagination getPage() {
		return page;
	}

	public void setPage(Pagination page) {
		this.page = page;
	}
}
