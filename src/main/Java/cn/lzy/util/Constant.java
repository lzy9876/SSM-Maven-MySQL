package cn.lzy.util;

import lombok.Data;

/**
 * @ClassName Constant
 * @Author:Liziy
 * @Date 2019/10/11 17:06
 * @Description: 业务状态码 描述信息
 **/
@Data
public class Constant {

    public static final int SUCCESS = 200;
    public static final int ERROR1 = 1;
    public static final int ERROR2 = 2;
    public static final int ERROR3 = 3;
    public static final int ERROR4 = 4;
    public static final int ERROR5 = 5;

    public static final String SUCCESS_SUCCESS_MSG = "成功";
    public static final String LOGIN_SUCCESS_MSG = "用户登录成功";
    public static final String LOGIN_ERROR1_MSG = "用户名错误";
    public static final String LOGIN_ERROR2_MSG = "密码错误";

    public static final String REGISTER_SUCCESS_MSG = "注册成功";
    public static final String REGISTER_ERROR1_MSG = "用户名已存在";

    public static final String USERLIST_SUCCESS_MSG = "用户列表为空";

    public static final String QUERY_SUCCESS_MSG = "用户列表为空";

    public static final String PHONE_ERROR_MSG = "手机号已注册";
    public static final String SAVEUSER_ERROR_MSG = "用户添加失败";
    public static final String DATA_ERROR_MSG = "数据异常";
    public static final String DATA_ERROR1_MSG = "请正确输入";


}
