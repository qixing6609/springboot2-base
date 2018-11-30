package com.ts.constant;


public class Constant {

	/**
	 * 支付方式：0：余额支付、 1：微信、 2：支付宝、 3：H5微信、4：线下支付
	 */
	public final static byte PAY_TYPE_ACCOUNT = 0;
	public final static byte PAY_TYPE_WEIXIN = 1;
	public final static byte PAY_TYPE_ALIPAY = 2;
	public final static byte PAY_TYPE_H5_WEIXIN = 3;
	public final static byte PAY_TYPE_OFF_LINE = 4;
	
	/**
	 * 业务类型：1：支付、2：充值
	 */
	public final static int BUSINESS_TYPE_ORDER = 1;
	public final static int BUSINESS_TYPE_RECHARGE = 2;
	
	/**
	 * 充值状态：1：成功、0：处理中、-1：失败
	 */
	public final static int RECHARGE_STATUS_SUCCESS = 1;
	public final static int RECHARGE_STATUS_PROCESS = 0;
	public final static int RECHARGE_STATUS_FAILURE = -1;
	
	/**
	 * 账户余额交易类型  0订单支付 1充值收入  2订单退款收入
	 */
	public final static byte DEAL_TYPE_OUT = 0;
	public final static byte DEAL_TYPE_IN = 1;
	public final static byte DEAL_TYPE_REFUND = 2;
	
	
	/**
	 * 产品相关表  0服务大类表  1服务分类表 2产品表 3产品型号表
	 */
	public final static byte TYPE_CATEGORY = 0;
	public final static byte TYPE_PRODUCT_GROUP = 1;
	public final static byte TYPE_PRODUCT = 2;
	public final static byte TYPE_PRODUCT_MODEL = 3;
	public final static byte TYPE_PRODUCT_PACKAGE = 4;
	/**
	 * 用户状态  1正常  2冻结	 
	 */
	public final static byte USER_STATUS_NORMAL = 1;
	public final static byte TYPE_PRODUCT_UNNORMAL = 2;
	
	/**
	 * 订单状态-1：订单取消 |0退款|1：订单逻辑删除 |100：待付款 |101：支付成功（待出发）|102：支付失败|103：申请退款|104：部分退款| |201：已出发|202：开始工作|203：已完成  
	 */
	public final static int ORDER_STATUS_CANCEL = -1;
	public final static int ORDER_STATUS_REFUND = 0;
	public final static int ORDER_STATUS_DEL = 1;
	public final static int ORDER_STATUS_PAYING = 100;
	public final static int ORDER_STATUS_PAY_SUCCESS = 101;
	public final static int ORDER_STATUS_PAY_FAILURE = 102;
	public final static int ORDER_STATUS_PAY_REFUND = 103;
	public final static int ORDER_STATUS_PAY_PART_REFUND = 104;
//	public final static int ORDER_STATUS_GOING = 200;
	public final static int ORDER_STATUS_GO = 201;
	public final static int ORDER_STATUS_BIGEN_WORK = 202;
	public final static int ORDER_STATUS_FINISH_WORK = 203;
//	public final static int USER_STATUS_CONFRIM = 300;
//	public final static int TYPE_PRODUCT_FINISH = 400;
	
	/**
	 * 订单退款状态  10:退款申请;20退款完成
	 */
	public final static Byte ORDER_REFUNDSTATUS_APPLY = 10;
	public final static Byte ORDER_REFUNDSTATUS_REFUND = 20;
	/**
	 * 优惠券状态 0正常 1已使用  2已过期
	 */
	public final static byte COUPON_STATUS_NORMAL = 0;
	public final static byte COUPON_STATUS_USED = 1;
	public final static byte COUPON_STATUS_OVERDUE = 2;
	
	
	/**
	 * 订单待支付剩余时间单位为分钟
	 */
	public final static int PAY_SURPLUS_TIMES = 900000; 
	
	/**
	 * 默认头像
	 */
	public final static String HEAD_IMG = "/resources/icon/headImg.png";
	
	/**
	 * 是否删除(0#未删除;1#已删除)
	 */
	public final static Byte DEL_FLG_FALSE = 0;
	public final static Byte DEL_FLG_TRUE = 1;
	
	/**
	 * 是否默认(0#否;1#默认)
	 */
	public final static Byte DEFAULT_FALSE = 0;
	public final static Byte DEFAULT_TRUE = 1;
	
	/**
	 * 产品是否上架  1上架  2下架,是否开通城市服务 1开通  2未开通,用户状态是否正常 1正常 2冻结
	 */
	public final static Byte IS_UP_OR_OPEN = 1;
	public final static Byte IS_DOWN_OR_CLOSE = 2;
	
	/**
	 * 卡片类型 1 会员卡 2充值卡
	 */
	public final static Byte CARD_TYPE_MEMBER = 1;
	public final static Byte CARD_TYPE_RECHARGE = 2;
	
	/**
	 * 业务员状态：1：正常，2：休息，3：离职，4:休闲
	 */
	public final static Byte SALESMAN_STATUS_ON = 1;
	public final static Byte SALESMAN_STATUS_REST = 2;
	public final static Byte SALESMAN_STATUS_LEAVE = 3;
	public final static Byte SALESMAN_STATUS_LEISURE = 4;
	
	/**
	 * 业务员是否奖励 0未奖励 1奖励 2没获得奖励
	 */
	public final static Byte SALESMAN_ISREWARD_FALSE = 0;
	public final static Byte SALESMAN_ISREWARD_TRUE = 1;
	public final static Byte SALESMAN_ISREWARD_ZERO = 2;
	
	/**
	 * 订单创建类型 1：用户创建、2：业务员创建
	 */
	public final static Byte CREATE_ORDER_TYPE_USER = 1;
	public final static Byte CREATE_ORDER_TYPE_SALESMAN = 2;
	
	/**
	 * 查询业务员订单类型 distribution：分配 create：创建  add 加单
	 */
	public final static String ORDER_DISTRIBUTION = "distribution";
	public final static String ORDER_CREATE = "create";
	public final static String ORDER_ADD = "add";
	
	
	/**
	 * 生成编号的第二位和第三位（10：清洗类）
	 */
	public static final String type_1 = "10";
	public static final String type_2 = "20";
	public static final String type_3 = "30";
	public static final String type_4 = "40";
	
	/**
	 * 生成编号的第一位和第二位（00：下单生成的订单编号、10：充值时的编号、20：h5微信请求流水号）
	 */
	public static final String ORDER_NO_FIRST_ORDER = "00";
	public static final String ORDER_NO_FIRST_RECHARGE = "10";
	public static final String ORDER_NO_FIRST_WX_H5 = "20";
	//与H5服务端通信需要加密的盐
	public static final String salt = "";
	
	/**
	 * 订单提成比例 ORDER_INCOME_PLATFORM比例  ORDER_INCOME_SALESMAN业务员比例
	 */
	public static final double ORDER_INCOME_PLATFORM = 0.35;
	public static final double ORDER_INCOME_SALESMAN = 0.65;
	
	/**
	 * 指定派单类型：1：指定自己、2：系统自动分配	
	 */
	public static final int ORDER_SENDTYPE_SALESMAN = 1;
	public static final int ORDER_SENDTYPE_SYSTEM = 2;
	
	
	/**
	 * 派单规则启用状态：0：未启用、1：启用
	 */
	public static final int SEND_ORDER_STATUS_N = 0;
	public static final int SEND_ORDER_STATUS_Y = 1;
	
	
	/**
	 * 派单规则
	 */
	//按照技能匹配
	public static final String ALLOT_RULE_SKILL = "skill";
	//按照业务员目前处于休闲状态匹配
	public static final String ALLOT_RULE_SALESMAN_STATUS = "salesman_status";
	//按照业务员没有订单或者距离下一单的服务时间在N分钟内
	public static final String ALLOT_RULE_SALESMAN_SERVICETIME = "salesman_servicetime";
	//距离下单地点在N公里内
	public static final String ALLOT_RULE_DISTANCE = "distance";
	//随机分配、按照距离由近及远
	public static final String ALLOT_RULE_END = "end";
	
	/**
	 * 选项类型（1#验证码;2#预约成功短信;3#服务完成短信;4#发送短信给业务员和城市经理；5#支付验证码;6#预约提醒短信）
	 */
	public static final int PHONE_MSG_TEMPLATE_CODE = 1;
	public static final int PHONE_MSG_TEMPLATE_SUBSCRIBE = 2;
	public static final int PHONE_MSG_TEMPLATE_SUCCESS = 3;
	public static final int PHONE_MSG_TEMPLATE_SALESMAN = 4;
	public static final int PHONE_MSG_TEMPLATE_PAY_CODE = 5;
	public static final int PHONE_MSG_TEMPLATE_WARN = 6;
	/**
	 * 状态(1#启用;0#禁用)
	 */
	public static final int STATUS_TRUE = 1;
	public static final int STATUS_FALSE = 0;
	
	/*
	 * 奖励类型 0 优惠券  1红包 2现金
	 */
	public final static Byte REWARD_TYPE_COUPON = 0;
	public final static Byte REWARD_TYPE_RED = 1;
	public final static Byte REWARD_TYPE_CASH = 2;
	
	/**
	 * 是否有活动 0 没有 1有
	 */
	public final static Byte IS_ACTIVE_TRUE = 1;
	public final static Byte IS_ACTIVE_FALSE = 0;
	
	/**
	 * 业务员类型：1：城市经理，2：业务员
	 */
	public static final byte SALESMAN_TYPE_MANAGER = 1;
	public static final byte SALESMAN_TYPE_SALESMAN = 2;
	
	/**
	 * 商品价格类型：1：原价，2：会员价，3：活动价
	 */
	public static final byte PRODUCT_PRICE_TYPE_I = 1;
	public static final byte PRODUCT_PRICE_TYPE_M = 2;
	public static final byte PRODUCT_PRICE_TYPE_A = 3;
	
	/** 调整价格描述*/
	public static String CHANGE_PRICE_REMARK = "请选择优惠折扣";
	
	/** 订单分类  10拉新单  20业务员订单 30自然单 */
	public static final int ORDER_KIND_NEW = 10;
	public static final int ORDER_KIND_SALESMAN = 20;
	public static final int ORDER_KIND_USER = 30;
	
	/** 调整价格类型  1折扣  2价格 */
	public static final byte CHANGE_PRICE_TYPE_SALE = 1;
	public static final byte CHANGE_PRICE_TYPE_PRICE = 2;
	
	/** 订单详情中支付价格类型（1：原价、2：会员价、3：活动价） */
	public static final byte PRICE_TYPE_PRICE = 1;
	public static final byte PRICE_TYPE_MEMBER = 2;
	public static final byte PRICE_TYPE_ACTIVE = 3;
	
	/**
	 * 配置项
	 */
	public static final String CONFIG_PRODUCT_CODE = "PRODUCT_CODE";
	public static final String CONFIG_MODEL_CODE = "MODEL_CODE";
	public static final String SALES_ORDER_STATUS = "SALES_ORDER_STATUS";
	
	
	/**套餐icon*/
	public static final String TC_IMG = "/resources/icon/product/tc-icon.png";
	
	/** 产品类型 100：套餐  200单个产品*/
	public static final int PRODUCT_TYPE_PACKAGE = 200;
	public static final int PRODUCT_TYPE_PRODUCT = 100;
	
	/**
	 * 产品是否分享产品(分享：1；不分享：0)
	 */
	public static final Byte PRODUCT_IS_SHARE_TRUE = 1;
	public static final Byte PRODUCT_IS_SHARE_FALSE = 0;
}
