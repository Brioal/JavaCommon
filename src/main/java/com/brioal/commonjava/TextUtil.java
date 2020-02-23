package com.brioal.commonjava;


/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by brioa on 2018/1/11.
 */

public class TextUtil {

    /**
     * 进行toString操作，若为空，返回默认值
     *
     * @param object
     * 				要进行toString操作的对象
     * @param nullStr
     * 				返回的默认值
     * @return
     */
    public static String toString(Object object,String nullStr){
        return object == null ? nullStr : object.toString();
    }

    /**
     * 将某个字符重复N次
     *
     * @param ch
     * 			需要循环的字符
     * @param count
     * 			循环的次数
     * @return
     */
    public static String repeatChar(char ch, int count) {
        char[] buf = new char[count];
        for (int i = count - 1; i >= 0; i--) {
            buf[i] = ch;
        }
        return new String(buf);
    }

    /**
     * 判断字符串是否全部都为小写
     *
     * @param value
     * 				待判断的字符串
     * @return
     */
    public static boolean isAllLowerCase(String value){
        if(value == null || "".equals(value)){
            return false;
        }
        for (int i = 0; i < value.length(); i++) {
            if (Character.isLowerCase(value.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否全部大写
     *
     * @param value 待判断的字符串
     * @return
     */
    public static boolean isAllUpperCase(String value){
        if(value == null || "".equals(value)){
            return false;
        }
        for (int i = 0; i < value.length(); i++) {
            if (Character.isUpperCase(value.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * 反转字符串
     *
     * @param value 待反转的字符串
     */
    public static String reverse(String value) {
        if (value == null) {
            return null;
        }
        return new StringBuffer(value).reverse().toString();
    }

    /**
     * 判断字符串是否可用
     *
     * @param str
     * @return
     */
    public static String getValue(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    /**
     * 判断字符串是否可用
     *
     * @param str
     * @return
     */
    public static boolean isStringAvailableAddNotNull(String str) {
        if (str == null) {
            return false;
        }
        if (str.equals("")) {
            return false;
        }
        if (str.equals("null")) {
            return false;
        }
        return true;
    }

    /**
     * 判断字符串是否不可用
     *
     * @param str
     * @return
     */
    public static boolean isNotValid(String str) {
        if (str == null) {
            return true;
        }
        if (str.equals("")) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否不可用
     *
     * @param str
     * @return
     */
    public static boolean isValid(String str) {
        if (str == null) {
            return false;
        }
        if (str.equals("")) {
            return false;
        }
        return true;
    }

    /**
     * 判断字符串是否可用
     *
     * @param str
     * @return
     */
    public static boolean isStringAvailable(String str) {
        if (str == null) {
            return false;
        }
        if (str.equals("")) {
            return false;
        }
        return true;
    }

    /**
     * 判断字符串是否错误
     *
     * @param str
     * @return
     */
    public static boolean isStringError(String str) {
        if (str == null) {
            return true;
        }
        if (str.equals("")) {
            return true;
        }
        return false;
    }


    /**
     * 是否全是英文
     *
     * @param str
     * @return
     */
    public boolean isAllEn(String str) {
        if (!isStringAvailable(str)) {
            return false;
        }
        return str.matches("[a-zA-Z]+");
    }

    /**
     * 是否全是中文
     *
     * @param str
     * @return
     */
    public boolean isAllChinese(String str) {
        if (!isStringAvailable(str)) {
            return false;
        }
        char[] chs = str.toCharArray();
        for (char ch : chs) {
            if (!isChinese(ch)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否是中文字符
     *
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(TextUtil.reverse("text"));
    }
}
