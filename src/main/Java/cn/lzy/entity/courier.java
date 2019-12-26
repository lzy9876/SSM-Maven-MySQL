package cn.lzy.entity;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName courier
 * @Author:Liziy
 * @Date 2019/11/14 0:33
 * @Description: 该类项目中暂未使用 使用请修改注释
 **/
@Data
public class courier {

    private int id;
    private String username;
    private String phonenumber;
    private String idcard;
    private String password;
    private int sex;
    private Date register_data;
}
