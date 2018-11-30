package com.ts.constant;

public enum OrderStatus {
    CANCEL( -1, "订单取消"),REFUND( 0, "退款"), DEL( 1, "订单删除"), PAYING( 100, "待付款"),
    PAY_SUCCESS( 101, "支付成功"), PAY_FAILURE( 102, "支付失败"),PAY_REFUND( 103, "申请退款"),
    PAY_PART_REFUND( 104, "部分退款"), GO( 201, "已出发"),BIGEN_WORK( 202, "开始工作"),
    FINISH_WORK( 203, "工作完成"), FINISH( 400, "已完成");
	
    private int value;
    private String desc;

    public static String getOrderStatusDes(int status) {
        for (OrderStatus sta : OrderStatus.values()) {
            if (sta.getValue()== status) {
                return sta.getDesc();
            }
        }
        return null;
    }
    
    private OrderStatus(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }


    public String getDesc() {
        return desc;
    }


}
