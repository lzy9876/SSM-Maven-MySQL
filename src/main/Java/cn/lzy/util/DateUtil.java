package cn.lzy.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName DateUtil
 * @Author:Liziy
 * @Date 2019/11/jquery 22:16
 * @Description: 获取当前时间
 **/
public class DateUtil {

    public static  String  time1 = " 00:00:00";
    public static  String  time2 = " 23:59:59";

    public static String  getTime(){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取当前时间
        String time = dateFormat.format( now );

        return time;
    }

    public static int  getTime2(){
        return 1;
    }


    public static void main(String[] args) {
        System.out.println("时间："+getTime());
    }
}
