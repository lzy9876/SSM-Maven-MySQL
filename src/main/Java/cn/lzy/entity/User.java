package cn.lzy.entity;

import lombok.Data;


/**
 * @ClassName User
 * @Author:Liziy
 * @Date 2019/11/6 23:47
 * @Description:
 **/

@Data
public class User {
    //ID
    private int id;

    //手机号
    private String phone;

    //密码
    private String pass;

    //姓名
    private String name;

    //盐 --加密
    private String salt;

    //注册时间

    private String register_data;

    //身份证
    private String id_card;

    //角色状态
    private int role;

    //派件数量
    private int count;

    //性别
    private int sex;
}
