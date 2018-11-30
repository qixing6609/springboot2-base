package com.ts.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {
    private ValidateUtil() {
    }

    /**
     * 是否为邮箱
     *
     * @param mail
     * @return
     */
    public static boolean isMail(String mail) {
        if (!isEmpty(mail)) {
            String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
            return matText(regex, mail);
        }
        return false;
    }

    /**
     * 是否为手机号码
     *
     * @param mobile
     * @return
     */
    public static boolean isMobile(String mobile) {
        if (!isEmpty(mobile)) {
            String regex = "^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\\d{8}$";
            return matText(regex, mobile);
        }
        return false;
    }

    /**
     * 是否为数字, 只允许为整形
     *
     * @param num
     * @return
     */
    public static boolean isNum(String num) {
        if (!isEmpty(num)) {
            String regex = "[0-9]*";
            return matText(regex, num);
        }
        return false;
    }

    /**
     * 是否为数字 包括整形，小数，并且必须含几位小数 0 表示不含小数
     *
     * @param num
     * @param diag
     * @return
     */
    public static boolean isNum(String num, int diag) {
        int index = num.indexOf('.');
        if (index < 0 || diag == 0) {
            return isNum(num);
        } else {
            String num1 = num.substring(0, index);
            String num2 = num.substring(index + 1);
            if (num2.length() > diag) {
                return false;
            }
            return isNum(num1) && isNum(num2);
        }
    }

    /**
     * 验证是否为数字，并且该数字取值范围合法，包含小数位
     *
     * @param num
     * @param min
     * @param max
     * @param diag 小数位 0 表示无小数
     * @return
     */
    public static boolean isNum(String num, double min, double max, int diag) {
        if (!isNum(num, diag)) {
            return false;
        }
        if (Double.parseDouble(num) < min) {
            return false;
        }
        if (Double.parseDouble(num) > max) {
            return false;
        }
        return true;
    }

    /**
     * 验证传入参数，是否位于集合内
     *
     * @param param
     * @return
     */
    public static boolean isInArray(String param, String[] params) {
        if (isEmpty(param)) {
            return false;
        }
        for (String p : params) {
            if (param.equals(p)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 字符串是否小于传入长度
     *
     * @param str
     * @param min
     * @param max
     * @return
     */
    public static boolean isInLength(String str, int min, int max) {
        if (!isEmpty(str) && str.length() >= min && str.length() <= max) {
            return true;
        }
        return false;
    }


    /**
     * 字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str.trim())) {
            return true;
        }
        return false;
    }

    /**
     * 是否为布尔类型字符串
     *
     * @param str
     * @return
     */
    public static boolean isBoolean(String str) {
        if (!isEmpty(str) && "true".equalsIgnoreCase(str) || "false".equalsIgnoreCase(str)) {
            return true;
        }
        return false;
    }

    /**
     * 是否存在于枚举类型内
     *
     * @param str
     * @param c
     * @return
     */
    public static boolean isInEnum(String str, Class<?> c) {
        if (!isEmpty(str)) {
            for (Object o : c.getEnumConstants()) {
                if (o.toString().equals(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean matText(String expression, String text) {
        Pattern p = Pattern.compile(expression); // 正则表达式
        Matcher m = p.matcher(text); // 操作的字符串
        return m.matches();
    }

    /**
     * 是否位于Enum name
     *
     * @param str
     * @param c
     * @return
     */
    public static boolean isInEnumName(String str, Class<?> c) {
        if (!isEmpty(str)) {
            for (Object o : c.getEnumConstants()) {
                if (((Enum) o).name().equals(str)) {
                    return true;
                }
            }
        }
        return false;
    }
}
