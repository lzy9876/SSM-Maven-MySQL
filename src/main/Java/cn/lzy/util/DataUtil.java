package cn.lzy.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName DataUtil
 * @Author:Liziy
 * @Date 2019/11/jquery 22:16
 * @Description: 获取当前时间
 **/
public class DataUtil {

    public static String  getTime(){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取当前时间
        String time = dateFormat.format( now );

        return time;
    }

    public static void main(String[] args) {
        System.out.println("时间："+getTime());
    }
}
