package com.black.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @description: localdate工具类
 * @author: duanwei
 * @create: 2020-05-28 13:57
 **/
public class LocalDateTimeUtils {


    static DateTimeFormatter inputFormat1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    static DateTimeFormatter inputFormat2 = DateTimeFormatter.ofPattern("yyyy/MM/dd H:mm:ss");


    private static ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal = ThreadLocal
            .withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public static String format(Date date) {
        return simpleDateFormatThreadLocal.get().format(date);
    }

    public static String getNow() {
        return format(new Date());
    }

    public static Date parse(String dateString) throws ParseException {
        return simpleDateFormat().parse(dateString);
    }

    public static final String DATEFORMAT1 = "yyyy-MM-dd HH:mm:ss";

    public static final String DATEFORMAT2 = "yyyy-MM-dd HH:mm";

    public static final String DATEFORMAT3 = "yyyy-MM-dd";


    /**
     * 获取隔日日期(时间类型不同)
     *
     * 比如今天是2019-02-01，调用这个方法会返回2019-02-02
     *
     * @return
     */
    public static Date alternativeDateTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }

    /**
     * 获取隔日日期(时间类型不同)
     *
     * 比如今天是2019-02-02，调用这个方法会返回2019-02-01
     *
     * @return
     */
    public static Date alternativeBeforeDateTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    /**
     * Date计算两个时间相差几天
     * 备注：跨月的测试过，在DateUtilsTest类中
     * @param startDate
     * @param endDate
     * @return 正数，负数
     */
    public static long timeIntervalInDays(Date startDate, Date endDate) {
        return timeIntervalInDayTimes(date2LocalDateTime(startDate), date2LocalDateTime(endDate));
    }

    /**
     * 返回当前日期是星期几
     * 用org.apache.commons.lang3.time.DateFormatUtils工具类
     * @param date
     * @return
     */
    public static String getWeekOfDate(Date date) {
        return DateFormatUtils.format(date, "E");
    }

    /**
     * 获取两个日期之间的所有日期
     * 备注：包含开始和结束时间
     * @param begin
     * @param end
     * @return
     */
    public static List<Date> getBetweenDate(Date begin, Date end) {
        List<Date> result = new ArrayList<Date>();
        //用Calendar 进行日期比较判断
        Calendar calendar = Calendar.getInstance();
        while (begin.getTime() <= end.getTime()) {
            // 把日期添加到集合
            result.add(begin);
            // 设置日期
            calendar.setTime(begin);
            //把日期增加一天
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            // 获取增加后的日期
            begin = calendar.getTime();// NOSONAR
        }
        return result;
    }

    private static SimpleDateFormat simpleDateFormat() {
        return simpleDateFormatThreadLocal.get();
    }

    private static long timeIntervalInDayTimes(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return Duration.between(startDateTime, endDateTime).toDays();
    }

    private static Date localDateTime2Date(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    private static LocalDateTime date2LocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }


    public static LocalDateTime strToLocalDateTime(String date, String pattern) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(date, df);
    }

    /**
     * 判断两个时间是否相隔day天
     */
    public static boolean isIntervalDays(Date startTime, Date endTime, Integer day) {
        long between = endTime.getTime() - startTime.getTime();
        if (between > 24L * 3600000 * day) {
            return true;
        }
        return false;
    }


    public static LocalDateTime strToLocalDate(String date){
        LocalDateTime parse = null;
        try{
            parse = LocalDateTime.parse(date, inputFormat1);
        }catch (Exception e){
            String[] s = date.split(" ");
            //处理年月日
            String[] day = s[0].split("/");
            String nian = null;
            if(Integer.parseInt(day[1])<10){
                nian=day[0]+"/"+"0"+day[1];
            }else{
                nian=day[0]+"/"+day[1];
            }
            if(Integer.parseInt(day[2])<10){
                nian=nian+"/"+"0"+day[2];
            }else{
                nian=nian+"/"+day[2];
            }
            //处理时分秒
            String[] time = s[1].split(":");
            if(Integer.parseInt(time[0])<10){
                nian=nian+" "+"0"+time[0]+":"+time[1]+":"+time[2];
            }else{
                nian=nian+" "+time[0]+":"+time[1]+":"+time[2];
            }
            parse = LocalDateTime.parse(nian, inputFormat1);
        }
        return parse;
    }

    public static void main(String[] args) {
        String s="2019/10/9 23:57:00";
        LocalDateTime localDateTime = strToLocalDate(s);
        System.out.println(localDateTime.toString());
    }


    public static LocalDateTime generateLocalDateTime(Long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    public static Long getMillisecond(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zone);
        return zdt.toInstant().toEpochMilli();
    }

    //获取当前时间的LocalDateTime对象
    //LocalDateTime.now();

    //根据年月日构建LocalDateTime
    //LocalDateTime.of();

    //比较日期先后
    //LocalDateTime.now().isBefore(),
    //LocalDateTime.now().isAfter(),

    //Date转换为LocalDateTime
    public static LocalDateTime convertDateToLDT(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    //LocalDateTime转换为Date
    public static Date convertLDTToDate(LocalDateTime time) {
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }


    //获取指定日期的毫秒
    public static Long getMilliByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    //获取指定日期的秒
    public static Long getSecondsByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    //获取指定时间的指定格式
    public static String formatTime(LocalDateTime time,String pattern) {
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }

    //获取当前时间的指定格式
    public static String formatNow(String pattern) {
        return  formatTime(LocalDateTime.now(), pattern);
    }

    //日期加上一个数,根据field不同加不同值,field为ChronoUnit.*
    public static LocalDateTime plus(LocalDateTime time, long number, TemporalUnit field) {
        return time.plus(number, field);
    }

    //日期减去一个数,根据field不同减不同值,field参数为ChronoUnit.*
    public static LocalDateTime minu(LocalDateTime time, long number, TemporalUnit field){
        return time.minus(number,field);
    }

    /**
     * 获取两个日期的差  field参数为ChronoUnit.*
     * @param startTime
     * @param endTime
     * @param field  单位(年月日时分秒)
     * @return
     */
    public static long betweenTwoTime(LocalDateTime startTime, LocalDateTime endTime, ChronoUnit field) {
        Period period = Period.between(LocalDate.from(startTime), LocalDate.from(endTime));
        if (field == ChronoUnit.YEARS) {
            return period.getYears();
        }
        if (field == ChronoUnit.MONTHS) {
            return period.getYears() * 12 + period.getMonths();
        }
        return field.between(startTime, endTime);
    }

    //获取一天的开始时间，2017,7,22 00:00
    public static LocalDateTime getDayStart(LocalDateTime time) {
        return time.withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    //获取一天的结束时间，2017,7,22 23:59:59.999999999
    public static LocalDateTime getDayEnd(LocalDateTime time) {
        return time.withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(999999999);
    }





}
