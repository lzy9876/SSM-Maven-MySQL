package cn.lzy.util;

import cn.lzy.entity.ConsoleData;

import java.text.SimpleDateFormat;

/**
 * @ClassName Demo
 * @Author:Liziy
 * @Date 2019/12/3 18:05
 * @Description:
 **/
public class Demo {


    public static void main(String[] args) {
//        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
//        RedisTemplate redisTemplate = app.getBean("redisTemplate", RedisTemplate.class);
//        System.out.println(redisTemplate);
//        redisTemplate.boundValueOps("name").set("李贺");
//        redisTemplate.boundValueOps("age").set("21");
//
//        String name = (String) redisTemplate.boundValueOps("name").get();
//        System.out.println(name);
//        String age = (String) redisTemplate.boundValueOps("age").get();
//        System.out.println(age);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //获取当前时间
        //String time = dateFormat.format( now );


        String time = DateUtil.getTime();
        String substring = time.substring(0, 10);
        System.out.println(substring+" :substring");
        System.out.println(time);
        String  time1 = " 00:00:00";
        String  time2 = " 23:59:59";
        System.out.println(substring+time1);
        String[] split = time.split("2");

        String a = ",";




    }




}
