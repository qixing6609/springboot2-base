package com.ts.api;

/**
 * Created by shan on 16/7/24.
 */
public enum ErrorCode {
    SUCCESS(0, "成功"),
    FAIL(-1, "失败"),
    INVALID_PARAMS(40000,"参数有误!"),
    VERIFY_CODE_FAIL(40001, "验证码验证失败"),
    DATABASE_ERROR(40002, "数据库错误"),
    USER_NOT_EXIST(40003, "用户不存在"),
    PASSWORD_ERROR(40004, "密码错误"),
    USER_FREEZED(40005, "账号被冻结"),
    MOBILE_ALREADY_REGIST(40006, "手机号已被注册"),
    SMS_CODE_TYPE_FAIL(40007, "验证码发送类型错误"),
    USER_ADDRESS_NUM_OVER(40008,"客户收货地址添加超过了最大数量"),
    STORE_NOT_EXIST(40009, "药店不存在"),
    STORE_FREEZED(40010, "账号被冻结"),
    MEDICINE_NOT_EXIST(40011, "药品不存在"),
    MOBILE_NOT_EXIST(40012, "手机号尚未注册"),
    ORDER_NOT_EXIST(40013, "订单号不存在"),
    ORDER_STATUS_FAIL(40014, "订单状态码不对"),
    USER_ADDRESS_NOT_EXIST(40015, "客户收货地址不存在"),
    ORDER_COMPLAIN_AGINE(40016, "您已投诉过该笔订单");

    ErrorCode(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    private int errorCode;
    private String errorMsg;

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
