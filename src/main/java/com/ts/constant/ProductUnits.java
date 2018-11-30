package com.ts.constant;

public enum ProductUnits {

	SERVERAL(1,"台");
	
    private int value;
    private String desc;

    private ProductUnits(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
    public static String getDesc(int value) {
        for (ProductUnits status : ProductUnits.values()) {
            if (status.getValue()== value) {
                return status.getDesc();
            }
        }
        return "";
    }
    
    
}
