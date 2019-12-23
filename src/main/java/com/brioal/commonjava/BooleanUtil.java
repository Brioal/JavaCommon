package com.brioal.commonjava;

import java.text.DecimalFormat;

/**
 * Boolean工具类
 */
public class BooleanUtil {


    /**
     * 是否是不可用的
     * @param value
     * @return
     */
    public static boolean isNotValid(Boolean value) {
        return !isValid(value);
    }
    /**
     * 是否可用
     * @param value
     * @return
     */
    public static boolean isValid(Boolean value) {
        if (value == null) {
            return false;
        }
        return true;
    }

    /**
     * 获取可用于的值
     *
     * @param value
     * @return
     */
    public static boolean getValue(Boolean value) {
        if (value == null) {
            return false;
        }
        return value.booleanValue();
    }

}
