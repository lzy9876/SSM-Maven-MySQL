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
    private int id;
    private String name;
    private String pass;
    private String nikName;
    private String salt;
}
