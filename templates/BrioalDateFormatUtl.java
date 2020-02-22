


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BrioalDateFormatUtl {

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
