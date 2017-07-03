package com.meihaifeng.common;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/3/7.
 */
public class DateUtils {

    /**
     * 时间格式符：yyyy-MM-dd HH:mm:ss
     */
    public static final String PATTERN_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    /**
     * 精简时间格式符：yyyyMMddHHmmss
     */
    public static final String PATTERN_DATE_TIME_SIMPLE = "yyyyMMddHHmmss";

    /**
     * 毫秒格式符：yyyyMMddhhmmssSSS
     * */
    public static  final  String PATTERN_DATE_TIME_ALL ="yyyyMMddhhmmssSSS";


    /**
     * 时间格式符：yyyy-MM-dd
     */
    public static final String PATTERN_DATE = "yyyy-MM-dd";

    /**
     * 精简时间格式符：yyyyMM dd
     */
    public static final String PATTERN_DATE_SIMPLE = "yyyyMMdd";

    /**
     * 精简时间格式符：yyMM
     */
    public static final String PATTERN_DATE_SIMPLE_yymm = "yyMM";

    /**
     * 时间格式符：HH:mm:ss
     */
    public static final String PATTERN_TIME = "HH:mm:ss";

    /**
     * 精简时间格式符：HHmmss
     */
    public static final String PATTERN_TIME_SIMPLE = "HHmmss";

    /**
     * 时间格式符：yyyy
     */
    public static final String PATTERN_YYYY = "yyyy";

    /**
     * 时间格式符：MM
     */
    public static final String PATTERN_MM = "MM";

    /**
     * 时间格式符：dd
     */
    public static final String PATTERN_DAY = "dd";

    /**
     * 时间格式符：E
     */
    public static final String PATTERN_WEEK = "E";

    /**
     * 取得系统毫秒数,返回Long.
     * @return
     */
    public static Long getTimeMillisLong(){
        return System.currentTimeMillis();
    }

    /**
     * 取得系统秒数,返回Long.
     * @return
     */
    public static Long getTimeSecondLong(){
        return System.currentTimeMillis()/1000;
    }

    /**
     * 取得系统毫秒数,返回字符串.
     * @return
     */
    public static String getTimeMillisString(){
        return System.currentTimeMillis() + "";
    }

    /**
     * 取得现在年月日时分秒yyyy-MM-dd HH:mm:ss.
     * @return
     */
    public static String getNowDateTime(){
        return formateDateTime(PATTERN_DATE_TIME);
    }

    /**
     * 取得现在年月日时分秒yyyyMMddHHmmss.
     * @return
     */
    public static String getNowDateTimeSimple(){
        return formateDateTime(PATTERN_DATE_TIME_SIMPLE);
    }


    /**
     * 取得现在年月日时分秒yyyyMMddHHmmss.
     * @return
     */
    public static String getNowDateTimeAll(){
        return formateDateTime(PATTERN_DATE_TIME_ALL);
    }

    /**
     * 取得现在年月日yyyy-MM-dd.
     * @return
     */
    public static String getNowDate(){
        return formateDateTime(PATTERN_DATE);
    }

    /**
     * 取得现在年月日yyyyMMdd.
     * @return
     */
    public static String getNowDateSimple(){
        return formateDateTime(PATTERN_DATE_SIMPLE);
    }

    /**
     * 取得现在年月yyMM.
     * @return
     */
    public static String getNowDateSimple_yymm(){
        return formateDateTime(PATTERN_DATE_SIMPLE_yymm);
    }

    /**
     * 取得现在时分秒HH:mm:ss.
     * @return
     */
    public static String getNowTime(){
        return formateDateTime(PATTERN_TIME);
    }

    /**
     * 取得现在时分秒HHmmss.
     * @return
     */
    public static String getNowTimeSimple(){
        return formateDateTime(PATTERN_TIME_SIMPLE);
    }

    /**
     * 取得现在年yyyy.
     * @return
     */
    public static String getNowYear(){
        return formateDateTime(PATTERN_YYYY);
    }

    /**
     * 取得现在月MM.
     * @return
     */
    public static String getNowMonth(){
        return formateDateTime(PATTERN_MM);
    }

    /**
     * 取得现在天dd.
     * @return
     */
    public static String getNowDay(){
        return formateDateTime(PATTERN_DAY);
    }

    /**
     * 取得现在星期,格式为星期一.
     * @return
     */
    public static String getNowWeek(){
        return formateDateTime(PATTERN_WEEK);
    }

    /**
     * 根据自定义格式取得现在时间.
     * @return
     */
    public static String getNowDateTime(String pattern){
        if(StringUtils.isNotBlank(pattern)){
            return formateDateTime(pattern);
        }else{
            return "";
        }
    }

    /**
     * 取得当月第一天.
     * @return
     */
    public static String getStartDayOfMonth(){
        DateTime dateTime = new DateTime();
        return dateTime.dayOfMonth().withMinimumValue().withTimeAtStartOfDay().toString(PATTERN_DATE);
    }

    /**
     * 取得当月最后一天.
     * @return
     */
    public static String getEndDayOfMonth(){
        DateTime dateTime = new DateTime();
        return dateTime.dayOfMonth().withMaximumValue().millisOfDay().withMaximumValue().toString(PATTERN_DATE);
    }

    /**
     * 增加月价
     * @param month
     * @return
     * @throws Exception
     */
    public static String addMonth(int month) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat(PATTERN_DATE_TIME);//设置日期格式
        String validatetime = df.format(new Date());
        Date now = df.parse(validatetime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime().getTime()+"";
    }

    /**
     * 根据时间字符串转换成Long毫秒数.
     * 时间字符串的格式应该与Pattern的样式一致.
     * @return
     */
    public static Long getStringToLong(String date, String pattern){
        if(StringUtils.isBlank(date) || StringUtils.isBlank(pattern)){
            return 0L;
        }else{
            DateTime dateTime = DateTimeFormat.forPattern(pattern).parseDateTime(date);
            return dateTime.getMillis();
        }
    }

    /**
     * 根据时间字符串转换成Long秒数.
     * 时间字符串的格式应该与Pattern的样式一致.
     * @return
     */
    public static int getStringToInt_seconds(String date, String pattern){
        if(StringUtils.isBlank(date) || StringUtils.isBlank(pattern)){
            return 0;
        }else{
            DateTime dateTime = DateTimeFormat.forPattern(pattern).parseDateTime(date);
            return Integer.parseInt(dateTime.getMillis()/1000+"");
        }
    }

    /**
     * 根据String毫秒数转换成时间字符串.
     * @return
     */
    public static String getLongToString(String millis, String pattern){
        DateTime dateTime = new DateTime(Long.parseLong(millis));
        return dateTime.toString(pattern);
    }

    /**
     * 根据Long毫秒数转换成时间字符串.
     * @return
     */
    public static String getLongToString(long millis, String pattern){
        DateTime dateTime = new DateTime(millis);
        return dateTime.toString(pattern);
    }

    /**
     * 根据秒数获取天时分秒.
     * @param pattern
     * @return
     */
    public static String getRuntimeBySecond(int seconds){
        long diffSeconds = seconds % 60;
        long diffMinutes = seconds / 60 % 60;
        long diffHours = seconds / (60 * 60) % 24;
        long diffDays = seconds / (24 * 60 * 60);

        StringBuffer buffer = new StringBuffer();
        buffer.append(diffDays + "天" + diffHours + "小时" + diffMinutes + "分钟" + diffSeconds + "秒");
        return buffer.toString();
    }

    /**
     * 根据毫秒数获取天时分秒.
     * @param pattern
     * @return
     */
    public static String getRuntimeByMillis(int millis){
        long diffSeconds = millis / 1000 % 60;
        long diffMinutes = millis / (60 * 1000) % 60;
        long diffHours = millis / (60 * 60 * 1000) % 24;
        long diffDays = millis / (24 * 60 * 60 * 1000);

        StringBuffer buffer = new StringBuffer();
        buffer.append(diffDays + "天" + diffHours + "小时" + diffMinutes + "分钟" + diffSeconds + "秒");
        return buffer.toString();
    }

    /**
     * 根据毫秒数获取天时分秒.
     * @param pattern
     * @return
     */
    public static String getRuntimeByMillis(long millis){
        long diffSeconds = millis / 1000 % 60;
        long diffMinutes = millis / (60 * 1000) % 60;
        long diffHours = millis / (60 * 60 * 1000) % 24;
        long diffDays = millis / (24 * 60 * 60 * 1000);

        StringBuffer buffer = new StringBuffer();
        buffer.append(diffDays + "天" + diffHours + "小时" + diffMinutes + "分钟" + diffSeconds + "秒");
        return buffer.toString();
    }

    /**
     * 转换时间.
     * @param pattern
     * @return
     */
    private static String formateDateTime(String pattern){
        DateTime dateTime = new DateTime();
        return dateTime.toString(pattern);
    }

    /**
     * 转String为Calendar
     * @param time
     * @return
     * @throws ParseException
     */
    public static Calendar changecal(String time) throws ParseException{
        //转类型
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(time);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    /**
     * 转Calendar为String
     * @param cal
     * @return
     */
    public static String changestr(Calendar cal){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = sdf.format(cal.getTime());
        return time;
    }

    /**
     * 根据日期获取当月第一天和最后一天的索引
     * @param time
     * @return
     * @throws ParseException
     */
    public static List<Integer> flmonthdate(Calendar cal){
        //获取本月第一天和最后一天
        int monthMum = cal.get(Calendar.MONTH);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int firstDayInThisMonth = cal.get(Calendar.DAY_OF_YEAR);
        int nextMonth = monthMum + 1;
        cal.set(Calendar.MONTH, nextMonth);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int firstDayInNextMonth = cal.get(Calendar.DAY_OF_YEAR);
        int lastDayInThisMonth = firstDayInNextMonth - 1;
        List<Integer> list = new ArrayList<Integer>();
        list.add(0, firstDayInThisMonth);
        list.add(1, lastDayInThisMonth);
        return list;
    }
    /**
     * 根据日期获取当天和当月最后一天的索引
     * @param cal
     * @return
     */
    public static List<Integer> twoDay(Calendar cal){
        int firstDay = cal.get(Calendar.DAY_OF_YEAR);
        int monthMum = cal.get(Calendar.MONTH);
        int firstDayInThisMonth = cal.get(Calendar.DAY_OF_YEAR);
        int nextMonth = monthMum + 1;
        cal.set(Calendar.MONTH, nextMonth);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int firstDayInNextMonth = cal.get(Calendar.DAY_OF_YEAR);
        int lastDayInThisMonth = firstDayInNextMonth - 1;
        List<Integer> list = new ArrayList<Integer>();
        list.add(0, firstDay);
        list.add(1, lastDayInThisMonth);
        return list;
    }

    /**
     * 日期索引转毫秒数
     * @param i
     * @return
     */
    public static String changeday(int i){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        //赋值日期
        cal.set(Calendar.DAY_OF_YEAR, i);
        //日期转毫秒
        String time = Long.toString(DateUtils.getStringToLong(sdf.format(cal.getTime()), DateUtils.PATTERN_DATE));
        return time;
    }
    /**
     * 获取当月最大天数
     * @param date
     * @return
     * @throws ParseException
     */
    public static int getMaxDay(String date) throws ParseException{
        Calendar cal = changecal(date);
        int day = cal.getActualMaximum(Calendar.DATE);
        return day;
    }

    /**
     * 获取当月除星期天天数
     * @param date
     * @return
     * @throws ParseException
     */
    public static int getDayNoSunday(String date) throws ParseException{
        Calendar cal = changecal(date);
        List<Integer> day = flmonthdate(cal);
        int days = 0;
        for(int i = day.get(0);i<= day.get(1);i++){
            cal.set(Calendar.DAY_OF_YEAR, i);
            int weekDay = cal.get(Calendar.DAY_OF_WEEK);
            if(weekDay != 1){
                days++;
            }
        }
        return days;
    }

    /**
     * 获取当月某日到月底中除星期天的天数
     * @param date
     * @return
     * @throws ParseException
     */
    public static int getDayNoSundayBewToDay(String date) throws ParseException{
        Calendar cal = changecal(date);
        List<Integer> day = twoDay(cal);
        int days = 0;
        for(int i = day.get(0);i<= day.get(1);i++){
            cal.set(Calendar.DAY_OF_YEAR, i);
            int weekDay = cal.get(Calendar.DAY_OF_WEEK);
            if(weekDay != 1){
                days++;
            }
        }
        return days;
    }
    /**
     * 获取当月某日到月底中除星期六星期天的天数
     * @param date
     * @return
     * @throws ParseException
     */
    public static int getDayNoWeekendBewToDay(String date) throws ParseException{
        Calendar cal = changecal(date);
        List<Integer> day = twoDay(cal);
        int days = 0;
        for(int i = day.get(0);i<= day.get(1);i++){
            cal.set(Calendar.DAY_OF_YEAR, i);
            int weekDay = cal.get(Calendar.DAY_OF_WEEK);
            if(weekDay != 1 && weekDay != 7){
                days++;
            }
        }
        return days;
    }
    /**
     * 获取当月某日到月底中的天数
     * @param date
     * @return
     * @throws ParseException
     */
    public static int getDayNoWeekend(String date)throws ParseException{
        Calendar cal = changecal(date);
        List<Integer> day = twoDay(cal);
        int days = 0;
        for(int i = day.get(0);i<= day.get(1);i++){
            days++;
        }
        return days;
    }
    /**
     * 日期索引转String
     * @param i
     * @return
     */
    public static String getday(int i){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        //赋值日期
        cal.set(Calendar.DAY_OF_YEAR, i);
        String day = changestr(cal);
        return day;
    }
    /**
     * 时间 转 毫秒
     * time格式：yyyy-MM-dd hh:mm:ss
     * @param i
     * @return
     * @throws ParseException
     */
    public static long dateChangeMillisecond(String time) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        long millionSeconds = sdf.parse(time).getTime();//毫秒
        return millionSeconds;
    }
    /**
     * 时间 转 毫秒
     * time格式：yyyyMMddhhmmss
     * @param i
     * @return
     * @throws ParseException
     */
    public static long dateChangeMillisecond1(String time) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        long millionSeconds = sdf.parse(time).getTime();//毫秒
        return millionSeconds;
    }

    /**
     *  计算当前日期的开始时间
     * time格式：yyyyMMdd
     * @param i
     * @return
     * @throws ParseException
     */
    public static long  getTodayStartLongSecond() throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
         String nowDate =  sdf.format(new Date()) + "000000" ;//毫秒
        return  dateChangeMillisecond1(nowDate)/1000;
    }

    /**
     *  计算当前日期的开始时间
     * time格式：yyyyMMdd
     * @param i
     * @return
     * @throws ParseException
     */
    public static long  getTodayEndLongSecond() throws ParseException{
        return  getTodayStartLongSecond() + 24*60*60;
    }




}
