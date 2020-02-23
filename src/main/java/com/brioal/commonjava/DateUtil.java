package com.brioal.commonjava;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    /** yyyy:年 */
    public static final String DATE_YEAR = "yyyy";

    /** MM：月 */
    public static final String DATE_MONTH = "MM";

    /** DD：日 */
    public static final String DATE_DAY = "dd";

    /** HH：时 */
    public static final String DATE_HOUR = "HH";

    /** mm：分 */
    public static final String DATE_MINUTE = "mm";

    /** ss：秒 */
    public static final String DATE_SECONDES = "ss";

    /** yyyy-MM-dd */
    public static final String DATE_FORMAT1 = "yyyy-MM-dd";

    /** yyyy-MM-dd hh:mm:ss */
    public static final String DATE_FORMAT2 = "yyyy-MM-dd HH:mm:ss";

    /** yyyy-MM-dd hh:mm:ss|SSS */
    public static final String TIME_FORMAT_SSS = "yyyy-MM-dd HH:mm:ss|SSS";

    /** yyyyMMdd */
    public static final String DATE_NOFUll_FORMAT = "yyyyMMdd";

    /** yyyyMMddhhmmss */
    public static final String TIME_NOFUll_FORMAT = "yyyyMMddHHmmss";

    /**
     *
     * 格式转换<br>
     * yyyy-MM-dd hh:mm:ss 和 yyyyMMddhhmmss 相互转换<br>
     * yyyy-mm-dd 和yyyymmss 相互转换
     * @author chenssy
     * @date Dec 26, 2013
     * @param value
     * 				日期
     * @return String
     */
    public static String formatString(String value) {
        String sReturn = "";
        if (value == null || "".equals(value))
            return sReturn;
        if (value.length() == 14) {   //长度为14格式转换成yyyy-mm-dd hh:mm:ss
            sReturn = value.substring(0, 4) + "-" + value.substring(4, 6) + "-" + value.substring(6, 8) + " "
                    + value.substring(8, 10) + ":" + value.substring(10, 12) + ":" + value.substring(12, 14);
            return sReturn;
        }
        if (value.length() == 19) {   //长度为19格式转换成yyyymmddhhmmss
            sReturn = value.substring(0, 4) + value.substring(5, 7) + value.substring(8, 10) + value.substring(11, 13)
                    + value.substring(14, 16) + value.substring(17, 19);
            return sReturn;
        }
        if(value.length() == 10){     //长度为10格式转换成yyyymmhh
            sReturn = value.substring(0, 4) + value.substring(5,7) + value.substring(8,10);
        }
        if(value.length() == 8){      //长度为8格式转化成yyyy-mm-dd
            sReturn = value.substring(0, 4) + "-" + value.substring(4, 6) + "-" + value.substring(6, 8);
        }
        return sReturn;
    }

    public static String formatDate(String date, String format) {
        if (date == null || "".equals(date)){
            return "";
        }
        Date dt = null;
        SimpleDateFormat inFmt = null;
        SimpleDateFormat outFmt = null;
        ParsePosition pos = new ParsePosition(0);
        date = date.replace("-", "").replace(":", "");
        if ((date == null) || ("".equals(date.trim())))
            return "";
        try {
            if (Long.parseLong(date) == 0L)
                return "";
        } catch (Exception nume) {
            return date;
        }
        try {
            switch (date.trim().length()) {
                case 14:
                    inFmt = new SimpleDateFormat("yyyyMMddHHmmss");
                    break;
                case 12:
                    inFmt = new SimpleDateFormat("yyyyMMddHHmm");
                    break;
                case 10:
                    inFmt = new SimpleDateFormat("yyyyMMddHH");
                    break;
                case 8:
                    inFmt = new SimpleDateFormat("yyyyMMdd");
                    break;
                case 6:
                    inFmt = new SimpleDateFormat("yyyyMM");
                    break;
                case 7:
                case 9:
                case 11:
                case 13:
                default:
                    return date;
            }
            if ((dt = inFmt.parse(date, pos)) == null)
                return date;
            if ((format == null) || ("".equals(format.trim()))) {
                outFmt = new SimpleDateFormat("yyyy年MM月dd日");
            } else {
                outFmt = new SimpleDateFormat(format);
            }
            return outFmt.format(dt);
        } catch (Exception ex) {
        }
        return date;
    }

    /**
     * 格式化日期
     *
     * @author chenming
     * @date 2016年5月31日
     *
     * @param date
     * @param format
     * @return
     */
    public static String formatDate(Date date,String format){
        return formatDate(DateUtils.date2String(date), format);
    }

    /**
     * @desc:格式化是时间，采用默认格式（yyyy-MM-dd HH:mm:ss）
     * @autor:chenssy
     * @date:2014年8月6日
     *
     * @param value
     * @return
     */
    public static String formatDate(String value){
        return getFormat(DATE_FORMAT2).format(DateUtils.string2Date(value, DATE_FORMAT2));
    }

    /**
     * 格式化日期
     *
     * @author : chenssy
     * @date : 2016年5月31日 下午5:40:58
     *
     * @param value
     * @return
     */
    public static String formatDate(Date value){
        return formatDate(DateUtils.date2String(value));
    }

    /**
     * 获取日期显示格式，为空默认为yyyy-mm-dd HH:mm:ss
     * @author chenssy
     * @date Dec 30, 2013
     * @param format
     * @return
     * @return SimpleDateFormat
     */
    protected static SimpleDateFormat getFormat(String format){
        if(format == null || "".equals(format)){
            format = DATE_FORMAT2;
        }
        return new SimpleDateFormat(format);
    }

    private static final String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
    /**
     * 根据指定格式获取当前时间
     * @author chenssy
     * @date Dec 27, 2013
     * @param format
     * @return String
     */
    public static String getCurrentTime(String format){
        SimpleDateFormat sdf = DateFormatUtils.getFormat(format);
        Date date = new Date();
        return sdf.format(date);
    }

    /**
     * 获取当前时间，格式为：yyyy-MM-dd HH:mm:ss
     * @author chenssy
     * @date Dec 27, 2013
     * @return String
     */
    public static String getCurrentTime(){
        return getCurrentTime(DateFormatUtils.DATE_FORMAT2);
    }

    /**
     * 获取指定格式的当前时间：为空时格式为yyyy-mm-dd HH:mm:ss
     * @author chenssy
     * @date Dec 30, 2013
     * @param format
     * @return Date
     */
    public static Date getCurrentDate(String format){
        SimpleDateFormat sdf = DateFormatUtils.getFormat(format);
        String dateS = getCurrentTime(format);
        Date date = null;
        try {
            date = sdf.parse(dateS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取当前时间，格式为yyyy-MM-dd HH:mm:ss
     * @author chenssy
     * @date Dec 30, 2013
     * @return Date
     */
    public static Date getCurrentDate(){
        return getCurrentDate(DateFormatUtils.DATE_FORMAT2);
    }

    /**
     * 给指定日期加入年份，为空时默认当前时间
     * @author chenssy
     * @date Dec 30, 2013
     * @param year 年份  正数相加、负数相减
     * @param date 为空时，默认为当前时间
     * @param format 默认格式为：yyyy-MM-dd HH:mm:ss
     * @return String
     */
    public static String addYearToDate(int year,Date date,String format){
        Calendar calender = getCalendar(date,format);
        SimpleDateFormat sdf = DateFormatUtils.getFormat(format);

        calender.add(Calendar.YEAR, year);

        return sdf.format(calender.getTime());
    }

    /**
     * 给指定日期加入年份，为空时默认当前时间
     * @author chenssy
     * @date Dec 30, 2013
     * @param year 年份  正数相加、负数相减
     * @param date 为空时，默认为当前时间
     * @param format 默认格式为：yyyy-MM-dd HH:mm:ss
     * @return String
     */
    public static String addYearToDate(int year,String date,String format){
        Date newDate = new Date();
        if(null != date && !"".equals(date)){
            newDate = string2Date(date, format);
        }

        return addYearToDate(year, newDate, format);
    }

    /**
     * 给指定日期增加月份 为空时默认当前时间
     * @author chenssy
     * @date Dec 30, 2013
     * @param month  增加月份  正数相加、负数相减
     * @param date 指定时间
     * @param format 指定格式 为空默认 yyyy-mm-dd HH:mm:ss
     * @return String
     */
    public static String addMothToDate(int month,Date date,String format) {
        Calendar calender = getCalendar(date,format);
        SimpleDateFormat sdf = DateFormatUtils.getFormat(format);

        calender.add(Calendar.MONTH, month);

        return sdf.format(calender.getTime());
    }

    /**
     * 给指定日期增加月份 为空时默认当前时间
     * @author chenssy
     * @date Dec 30, 2013
     * @param month  增加月份  正数相加、负数相减
     * @param date 指定时间
     * @param format 指定格式 为空默认 yyyy-mm-dd HH:mm:ss
     * @return String
     */
    public static String addMothToDate(int month,String date,String format) {
        Date newDate = new Date();
        if(null != date && !"".equals(date)){
            newDate = string2Date(date, format);
        }

        return addMothToDate(month, newDate, format);
    }

    /**
     * 给指定日期增加天数，为空时默认当前时间
     * @author chenssy
     * @date Dec 31, 2013
     * @param day 增加天数 正数相加、负数相减
     * @param date 指定日期
     * @param format 日期格式 为空默认 yyyy-mm-dd HH:mm:ss
     * @return String
     */
    public static String addDayToDate(int day,Date date,String format) {
        Calendar calendar = getCalendar(date, format);
        SimpleDateFormat sdf = DateFormatUtils.getFormat(format);

        calendar.add(Calendar.DATE, day);

        return sdf.format(calendar.getTime());
    }

    /**
     * 给指定日期增加天数，为空时默认当前时间
     * @author chenssy
     * @date Dec 31, 2013
     * @param day 增加天数 正数相加、负数相减
     * @param date 指定日期
     * @param format 日期格式 为空默认 yyyy-mm-dd HH:mm:ss
     * @return String
     */
    public static String addDayToDate(int day,String date,String format) {
        Date newDate = new Date();
        if(null != date && !"".equals(date)){
            newDate = string2Date(date, format);
        }

        return addDayToDate(day, newDate, format);
    }

    /**
     * 给指定日期增加小时，为空时默认当前时间
     * @author chenssy
     * @date Dec 31, 2013
     * @param hour 增加小时  正数相加、负数相减
     * @param date 指定日期
     * @param format 日期格式 为空默认 yyyy-mm-dd HH:mm:ss
     * @return String
     */
    public static String addHourToDate(int hour,Date date,String format) {
        Calendar calendar = getCalendar(date, format);
        SimpleDateFormat sdf = DateFormatUtils.getFormat(format);

        calendar.add(Calendar.HOUR, hour);

        return sdf.format(calendar.getTime());
    }

    /**
     * 给指定日期增加小时，为空时默认当前时间
     * @author chenssy
     * @date Dec 31, 2013
     * @param hour 增加小时  正数相加、负数相减
     * @param date 指定日期
     * @param format 日期格式 为空默认 yyyy-mm-dd HH:mm:ss
     * @return String
     */
    public static String addHourToDate(int hour,String date,String format) {
        Date newDate = new Date();
        if(null != date && !"".equals(date)){
            newDate = string2Date(date, format);
        }

        return addHourToDate(hour, newDate, format);
    }

    /**
     * 给指定的日期增加分钟，为空时默认当前时间
     * @author chenssy
     * @date Dec 31, 2013
     * @param minute 增加分钟  正数相加、负数相减
     * @param date 指定日期
     * @param format 日期格式 为空默认 yyyy-mm-dd HH:mm:ss
     * @return String
     */
    public static String addMinuteToDate(int minute,Date date,String format) {
        Calendar calendar = getCalendar(date, format);
        SimpleDateFormat sdf = DateFormatUtils.getFormat(format);

        calendar.add(Calendar.MINUTE, minute);

        return sdf.format(calendar.getTime());
    }

    /**
     * 给指定的日期增加分钟，为空时默认当前时间
     * @author chenssy
     * @date Dec 31, 2013
     * @param minute 增加分钟  正数相加、负数相减
     * @param date 指定日期
     * @param format 日期格式 为空默认 yyyy-mm-dd HH:mm:ss
     * @return String
     */
    public static String addMinuteToDate(int minute,String date,String format){
        Date newDate = new Date();
        if(null != date && !"".equals(date)){
            newDate = string2Date(date, format);
        }

        return addMinuteToDate(minute, newDate, format);
    }

    /**
     * 给指定日期增加秒，为空时默认当前时间
     * @author chenssy
     * @date Dec 31, 2013
     * @param second 增加秒 正数相加、负数相减
     * @param date 指定日期
     * @param format 日期格式 为空默认 yyyy-mm-dd HH:mm:ss
     * @return String
     */
    public static String addSecondToDate(int second,Date date,String format){
        Calendar calendar = getCalendar(date, format);
        SimpleDateFormat sdf = DateFormatUtils.getFormat(format);

        calendar.add(Calendar.SECOND, second);

        return sdf.format(calendar.getTime());
    }

    /**
     * 给指定日期增加秒，为空时默认当前时间
     * @author chenssy
     * @date Dec 31, 2013
     * @param second 增加秒 正数相加、负数相减
     * @param date 指定日期
     * @param format 日期格式 为空默认 yyyy-mm-dd HH:mm:ss
     * @return String
     * @throws Exception
     */
    public static String addSecondToDate(int second,String date,String format){
        Date newDate = new Date();
        if(null != date && !"".equals(date)){
            newDate = string2Date(date, format);
        }

        return addSecondToDate(second, newDate, format);
    }

    /**
     * 获取指定格式指定时间的日历
     * @author chenssy
     * @date Dec 30, 2013
     * @param date 时间
     * @param format 格式
     * @return Calendar
     */
    public static Calendar getCalendar(Date date,String format){
        if(date == null){
            date = getCurrentDate(format);
        }

        Calendar calender = Calendar.getInstance();
        calender.setTime(date);

        return calender;
    }

    /**
     * 字符串转换为日期，日期格式为
     *
     * @author : chenssy
     * @date : 2016年5月31日 下午5:20:22
     *
     * @param value
     * @return
     */
    public static Date string2Date(String value){
        if(value == null || "".equals(value)){
            return null;
        }

        SimpleDateFormat sdf = DateFormatUtils.getFormat(DateFormatUtils.DATE_FORMAT2);
        Date date = null;

        try {
            value = DateFormatUtils.formatDate(value, DateFormatUtils.DATE_FORMAT2);
            date = sdf.parse(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 将字符串(格式符合规范)转换成Date
     * @author chenssy
     * @date Dec 31, 2013
     * @param value 需要转换的字符串
     * @param format 日期格式
     * @return Date
     */
    public static Date string2Date(String value,String format){
        if(value == null || "".equals(value)){
            return null;
        }

        SimpleDateFormat sdf = DateFormatUtils.getFormat(format);
        Date date = null;

        try {
            value = DateFormatUtils.formatDate(value, format);
            date = sdf.parse(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 将日期格式转换成String
     * @author chenssy
     * @date Dec 31, 2013
     *
     * @param value 需要转换的日期
     * @param format 日期格式
     * @return String
     */
    public static String date2String(Date value,String format){
        if(value == null){
            return null;
        }

        SimpleDateFormat sdf = DateFormatUtils.getFormat(format);
        return sdf.format(value);
    }

    /**
     * 日期转换为字符串
     *
     * @author : chenssy
     * @date : 2016年5月31日 下午5:21:38
     *
     * @param value
     * @return
     */
    public static String date2String(Date value){
        if(value == null){
            return null;
        }

        SimpleDateFormat sdf = DateFormatUtils.getFormat(DateFormatUtils.DATE_FORMAT2);
        return sdf.format(value);
    }

    /**
     * 获取指定日期的年份
     * @author chenssy
     * @date Dec 31, 2013
     * @param value 日期
     * @return int
     */
    public static int getCurrentYear(Date value){
        String date = date2String(value, DateFormatUtils.DATE_YEAR);
        return Integer.valueOf(date);
    }

    /**
     * 获取指定日期的年份
     * @author chenssy
     * @date Dec 31, 2013
     * @param value 日期
     * @return int
     */
    public static int getCurrentYear(String value) {
        Date date = string2Date(value, DateFormatUtils.DATE_YEAR);
        Calendar calendar = getCalendar(date, DateFormatUtils.DATE_YEAR);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取指定日期的月份
     * @author chenssy
     * @date Dec 31, 2013
     * @param value 日期
     * @return int
     */
    public static int getCurrentMonth(Date value){
        String date = date2String(value, DateFormatUtils.DATE_MONTH);
        return Integer.valueOf(date);
    }

    /**
     * 获取指定日期的月份
     * @author chenssy
     * @date Dec 31, 2013
     * @param value 日期
     * @return int
     */
    public static int getCurrentMonth(String value) {
        Date date = string2Date(value, DateFormatUtils.DATE_MONTH);
        Calendar calendar = getCalendar(date, DateFormatUtils.DATE_MONTH);

        return calendar.get(Calendar.MONTH);
    }

    /**
     * 获取指定日期的天份
     * @author chenssy
     * @date Dec 31, 2013
     * @param value 日期
     * @return int
     */
    public static int getCurrentDay(Date value){
        String date = date2String(value, DateFormatUtils.DATE_DAY);
        return Integer.valueOf(date);
    }

    /**
     * 获取指定日期的天份
     * @author chenssy
     * @date Dec 31, 2013
     * @param value 日期
     * @return int
     */
    public static int getCurrentDay(String value){
        Date date = string2Date(value, DateFormatUtils.DATE_DAY);
        Calendar calendar = getCalendar(date, DateFormatUtils.DATE_DAY);

        return calendar.get(Calendar.DATE);
    }

    /**
     * 获取当前日期为星期几
     * @author chenssy
     * @date Dec 31, 2013
     * @param value 日期
     * @return String
     */
    public static String getCurrentWeek(Date value) {
        Calendar calendar = getCalendar(value, DateFormatUtils.DATE_FORMAT1);
        int weekIndex = calendar.get(Calendar.DAY_OF_WEEK) - 1 < 0 ? 0 : calendar.get(Calendar.DAY_OF_WEEK) - 1;

        return weeks[weekIndex];
    }

    /**
     * 获取当前日期为星期几
     * @author chenssy
     * @date Dec 31, 2013
     * @param value 日期
     * @return String
     */
    public static String getCurrentWeek(String value) {
        Date date = string2Date(value, DateFormatUtils.DATE_FORMAT1);
        return getCurrentWeek(date);
    }

    /**
     * 获取指定日期的小时
     * @author chenssy
     * @date Dec 31, 2013
     * @param value 日期
     * @return int
     */
    public static int getCurrentHour(Date value){
        String date = date2String(value, DateFormatUtils.DATE_HOUR);
        return Integer.valueOf(date);
    }

    /**
     * 获取指定日期的小时
     * @author chenssy
     * @date Dec 31, 2013
     * @param value 日期
     * @return
     * @return int
     */
    public static int getCurrentHour(String value) {
        Date date = string2Date(value, DateFormatUtils.DATE_HOUR);
        Calendar calendar = getCalendar(date, DateFormatUtils.DATE_HOUR);

        return calendar.get(Calendar.DATE);
    }

    /**
     * 获取指定日期的分钟
     * @author chenssy
     * @date Dec 31, 2013
     * @param value 日期
     * @return int
     */
    public static int getCurrentMinute(Date value){
        String date = date2String(value, DateFormatUtils.DATE_MINUTE);
        return Integer.valueOf(date);
    }

    /**
     * 获取指定日期的分钟
     * @author chenssy
     * @date Dec 31, 2013
     * @param value 日期
     * @return int
     */
    public static int getCurrentMinute(String value){
        Date date = string2Date(value, DateFormatUtils.DATE_MINUTE);
        Calendar calendar = getCalendar(date, DateFormatUtils.DATE_MINUTE);

        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 比较两个日期相隔多少天(月、年) <br>
     * 例：<br>
     * &nbsp;compareDate("2009-09-12", null, 0);//比较天 <br>
     * &nbsp;compareDate("2009-09-12", null, 1);//比较月 <br>
     * &nbsp;compareDate("2009-09-12", null, 2);//比较年 <br>
     *
     * @author chenssy
     * @date Dec 31, 2013
     * @param startDay 需要比较的时间 不能为空(null),需要正确的日期格式 ,如：2009-09-12
     * @param endDay 被比较的时间  为空(null)则为当前时间
     * @param stype 返回值类型   0为多少天，1为多少个月，2为多少年
     * @return int
     */
    public static int compareDate(String startDay,String endDay,int stype) {
        int n = 0;
        startDay = DateFormatUtils.formatDate(startDay, "yyyy-MM-dd");
        endDay = DateFormatUtils.formatDate(endDay, "yyyy-MM-dd");

        String formatStyle = "yyyy-MM-dd";
        if(1 == stype){
            formatStyle = "yyyy-MM";
        }else if(2 == stype){
            formatStyle = "yyyy";
        }

        endDay = endDay==null ? getCurrentTime("yyyy-MM-dd") : endDay;

        DateFormat df = new SimpleDateFormat(formatStyle);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(df.parse(startDay));
            c2.setTime(df.parse(endDay));
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (!c1.after(c2)) {                   // 循环对比，直到相等，n 就是所要的结果
            n++;
            if(stype==1){
                c1.add(Calendar.MONTH, 1);          // 比较月份，月份+1
            }
            else{
                c1.add(Calendar.DATE, 1);           // 比较天数，日期+1
            }
        }
        n = n-1;
        if(stype==2){
            n = (int)n/365;
        }
        return n;
    }

    /**
     * 比较两个时间相差多少小时(分钟、秒)
     * @author chenssy
     * @date Jan 2, 2014
     * @param startTime 需要比较的时间 不能为空，且必须符合正确格式：2012-12-12 12:12:
     * @param endTime 需要被比较的时间 若为空则默认当前时间
     * @param type 1：小时   2：分钟   3：秒
     * @return int
     */
    public static int compareTime(String startTime , String endTime , int type) {
        //endTime是否为空，为空默认当前时间
        if(endTime == null || "".equals(endTime)){
            endTime = getCurrentTime();
        }

        SimpleDateFormat sdf = DateFormatUtils.getFormat("");
        int value = 0;
        try {
            Date begin = sdf.parse(startTime);
            Date end = sdf.parse(endTime);
            long between = (end.getTime() - begin.getTime()) / 1000;  //除以1000转换成豪秒
            if(type == 1){   //小时
                value = (int) (between % (24 * 36000) / 3600);
            }
            else if(type == 2){
                value = (int) (between % 3600 / 60);
            }
            else if(type == 3){
                value = (int) (between % 60 / 60);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 比较两个日期的大小。<br>
     * 若date1 > date2 则返回 1<br>
     * 若date1 = date2 则返回 0<br>
     * 若date1 < date2 则返回-1
     * @autor:chenssy
     * @date:2014年9月9日
     *
     * @param date1
     * @param date2
     * @param format  待转换的格式
     * @return 比较结果
     */
    public static int compare(String date1, String date2,String format) {
        DateFormat df = DateFormatUtils.getFormat(format);
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取指定月份的第一天
     *
     * @author : chenssy
     * @date : 2016年5月31日 下午5:31:10
     *
     * @param date
     * @return
     */
    public static String getMonthFirstDate(String date){
        date = DateFormatUtils.formatDate(date);
        return DateFormatUtils.formatDate(date, "yyyy-MM") + "-01";
    }

    /**
     * 获取指定月份的最后一天
     *
     * @author : chenssy
     * @date : 2016年5月31日 下午5:32:09
     *
     * @param strdate
     * @return
     */
    public static String getMonthLastDate(String date) {
        Date strDate = DateUtils.string2Date(getMonthFirstDate(date));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(strDate);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return DateFormatUtils.formatDate(calendar.getTime());
    }

    /**
     * 获取所在星期的第一天
     *
     * @author : chenssy
     * @date : 2016年6月1日 下午12:38:53
     *
     * @param date
     * @return
     */
    @SuppressWarnings("static-access")
    public static Date getWeekFirstDate(Date date) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        int today = now.get(Calendar.DAY_OF_WEEK);
        int first_day_of_week = now.get(Calendar.DATE) + 2 - today; // 星期一
        now.set(now.DATE, first_day_of_week);
        return now.getTime();
    }

    /**
     * 获取所在星期的最后一天
     *
     * @author : chenssy
     * @date : 2016年6月1日 下午12:40:31
     *
     * @param date
     * @return
     */
    @SuppressWarnings("static-access")
    public static Date geWeektLastDate(Date date) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        int today = now.get(Calendar.DAY_OF_WEEK);
        int first_day_of_week = now.get(Calendar.DATE) + 2 - today; // 星期一
        int last_day_of_week = first_day_of_week + 6; // 星期日
        now.set(now.DATE, last_day_of_week);
        return now.getTime();
    }

    /**
     * String转换为TimeStamp
     * @param value
     *              待转换的String，格式必须为 yyyy-mm-dd hh:mm:ss[.f...] 这样的格式，中括号表示可选，否则报错
     * @return java.sql.Timestamp
     *
     * @author chenssy
     * @date 2016-09-24
     * @since v1.0.0
     */
    public static Timestamp string2Timestamp(String value){
        if(value == null && !"".equals(value.trim())){
            return null;
        }
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        ts = Timestamp.valueOf(value);
        return ts;
    }

    /**
     * 将Timestamp 转换为String类型，format为null则使用默认格式 yyyy-MM-dd HH:mm:ss
     *
     * @param value
     *              待转换的Timestamp
     * @param format
     *              String的格式
     * @return java.lang.String
     *
     * @author chenssy
     * @date 2016-09-24
     * @since v1.0.0
     */
    public static String timestamp2String(Timestamp value,String format){
        if(null == value){
            return "";
        }
        SimpleDateFormat sdf = DateFormatUtils.getFormat(format);

        return sdf.format(value);
    }

    /**
     * Date转换为Timestamp
     *
     * @param date
     *              待转换的Date
     * @return java.sql.Timestamp
     *
     * @author chenssy
     * @date 2016-09-24
     * @since v1.0.0
     */
    public static Timestamp date2Timestamp(Date date){
        if(date == null){
            return null;
        }
        return new Timestamp(date.getTime());
    }

    /**
     * Timestamp转换为Date
     *
     * @param time
     *              待转换的Timestamp
     * @return java.util.Date
     *
     * @author chenssy
     * @date 2016-09-24
     * @since v1.0.0
     */
    public static Date timestamp2Date(Timestamp time){
        return time == null ? null : time;
    }

    /**
     * 格式化时间
     *
     * @param formatStr
     * @param time
     * @return
     */
    public static String formatTime(String formatStr, long time) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(formatStr);
            String result = format.format(time);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "未知";
    }

    /**
     * 日期转换
     * 格式为 yyyy-MM-dd HH:mm:ss
     *
     * @param str
     * @return
     */
    public static Timestamp convertFromFullTimeStr(String str) {
        return convertFromStr("yyyy-MM-dd HH:mm:ss", str);
    }

    /**
     * 日期转换
     * 自定义格式
     *
     * @param str
     * @return
     */
    public static Timestamp convertFromStr(String format, String str) {
        if (!TextUtil.isStringAvailableAddNotNull(str)) {
            return null;
        }
        try {
            return new Timestamp(new SimpleDateFormat(format).parse(str).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Timestamp(System.currentTimeMillis());
    }


    /**
     * 返回当前的时间
     *
     * @return
     */
    public static Timestamp getCurrentTime() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 返回今天凌晨的时间
     *
     * @return
     */
    public static Timestamp getTodayZeroTime() {
        Date date = new Date();
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        Timestamp timeStamp = new Timestamp(date.getTime());
        return timeStamp;
    }

    /**
     * 获取当前年份
     *
     * @return
     */
    public static int currentYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 格式转换
     *
     * @param timestamp
     * @param format
     * @return
     */
    public static String formatDate(Timestamp timestamp, String format) {
        SimpleDateFormat util = new SimpleDateFormat(format);
        return util.format(timestamp);
    }

    /**
     * 格式转换
     *
     * @param timestamp
     * @return
     */
    public static String formatDate(Timestamp timestamp) {
        if (timestamp == null) {
            return "";
        }
        SimpleDateFormat util = new SimpleDateFormat("yyyy-MM-dd");
        return util.format(timestamp);
    }

    /**
     * 格式转换
     *
     * @param str
     * @param format
     * @return
     */
    public static Timestamp parseDate(String str, String format) {
        try {
            SimpleDateFormat util = new SimpleDateFormat(format);
            Date date = util.parse(str);
            return new Timestamp(date.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 格式转换
     *
     * @param str
     * @return
     */
    public static Timestamp parseDate(String str) {
        return parseDate(str, "yyyy-MM-dd");
    }

    /**
     * 解析日期
     *
     * @param str
     * @return
     */
    public static Timestamp parseDateAndTime(String str) {
        return parseDate(str, "yyyy-MM-dd hh:mm:ss");
    }


    /**
     * 返回完整的日期
     *
     * @param timestamp
     * @return
     */
    public static String formatFullDate(Timestamp timestamp) {
        if (timestamp == null) {
            return "";
        }
        return formatDate(timestamp, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 返回今天的日期用作文件的路径
     *
     * @return
     */
    public static String getTodayDateForFilePath() {
        String result = formatDate(new Timestamp(System.currentTimeMillis()), "yyyy_MM_dd");
        return result;
    }

    /**
     * 获取当前的完整时间 yyyy-MM-dd mm:HH:ss
     *
     * @return
     */
    public static String getDetailDate() {
        return formatFullDate(getCurrentTime());
    }

    /**
     * 增加时间
     *
     * @param startTime
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static Timestamp addTime(Timestamp startTime, int year, int month, int day) {
        if (startTime == null) {
            return null;
        }
        long startTimeMill = startTime.getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(startTimeMill);
        // 添加
        calendar.add(Calendar.YEAR, year);
        calendar.add(Calendar.MONTH, month);
        calendar.add(Calendar.DATE, day);
        long endTimeMils = calendar.getTimeInMillis();
        Timestamp endTime = new Timestamp(endTimeMils);
        return endTime;
    }

}
