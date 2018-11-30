package com.ts.constant;

/**
 * 系统核心配置类
 * 
 * Created by shan on 16/10/17.
 * 
 */
public class ContextConfig {
	/**
	 * 邮寄药店默认名字
	 */
	public static final String DEFAILT_STORE_NAME = "仁和堂天猫旗舰店";
	/**
	 * 药品展示分页大小
	 */
	public static final int MEDICINE_PAGE_SIZE = 20;
	/**
	 * 订单展示分页大小
	 */
	public static final int ORDER_PAGE_SIZE = 10;
	/**
	 * 优惠券展示分页大小
	 */
	public static final int COUPON_PAGE_SIZE = 10;
	/**
	 * 可添加收货地址大小
	 */
	public static final int ADDRESS_NUM_SIZE = 10;
	/**
	 * 可选择的药店地址大小
	 */
	public static final int STORE_NUM_SIZE = 5;
	/**
	 * 可选择的药店派送最远距离5km
	 */
	public static final Double STORE_DELIVERY_DIS = 5000.00;
	/**
	 * 信息分页大小
	 */
	public static final int NO_LIMIT_PAGE_SIZE = 100000;
	/**
	 * 缓存天数
	 */
	public static final Integer DAYS_7 = 7;
	/**
	 * 缓存分钟
	 */
	public static final Integer MIN_10 = 10;
	
	
	
	
	/**
	 *  创建人类型:  1用户 2业务员
	 */
	public static final int create_user_type_user = 1;
	public static final int create_user_type_salesman = 2;
	
	/**
	 * 是否删除(0#未删除;1#已删除)
	 */
	public static final Byte DEL_FLG_FALSE = 0;
	public static final Byte DEL_FLG_TRUE = 1;
}
