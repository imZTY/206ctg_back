package io.renren.modules.CTG.utils;

import java.text.SimpleDateFormat;

/**
 * @author tianyi
 * @date 2018-10-19 10:25
 */
public class TimestampUtil {

    public static SimpleDateFormat CHECK_FMT = new SimpleDateFormat("MM/dd/yy HH:mm");
    public static SimpleDateFormat JUDGE_FMT = new SimpleDateFormat("yyyyMMdd");

    public static String getAgeStringFromTimaStamp(Long birthday){
        StringBuffer rt = new StringBuffer();
        birthday *= 1000;
        long now = System.currentTimeMillis();
        long age = now - birthday;

//        Date date = new Date(age);
        System.out.println("birthday: "+birthday+", now: "+now+", age: "+age);
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//        String rt = df.format(date);

        int year = (int) (age/31536000000L);
        int month = (int) (age%31536000000L/2419200000L);
        if (year > 0){
            rt.append(year+"岁"+month+"月");
        }else{
            int week = (int) (age%31536000000L%2419200000L/604800000L);
            int day = (int) (age%31536000000L%2419200000L%604800000L/86400000);
            if (month > 0){
                rt.append(month+"月"+week+"周");
            }else if (week > 0){
                rt.append(week+"周"+day+"天");
            }else {
                int hour = (int) (age%31536000000L%2419200000L%604800000L%86400000/3600000);
                rt.append(day+"天"+hour+"小时");
            }
        }

        return rt.toString();
    }



}
