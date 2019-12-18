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
    public static final int LAYUI_SUCCESS = 0;
    public static final int ERROR1 = 1;
    public static final int ERROR2 = 2;
    public static final int ERROR3 = 3;
    public static final int ERROR4 = 4;
    public static final int ERROR5 = 5;

    public static final String SUCCESS_SUCCESS_MSG = "成功";
    public static final String LOGIN_SUCCESS_MSG = "用户登录成功";
    public static final String LOGIN_ERROR_MSG = "登录失败";
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
    public static final String QUERY_DATA_NULL_MSG = "查询结果为空";

    public static final String UPDATE_DATA_ERROR_MAG = "数据更新失败";
    public static final String UPDATE_DATA_SUCCESS_MAG = "数据更新成功";

    public static final String DELETE_DATA_ERROR_MAG = "数据删除失败";
    public static final String DELETE_DATA_SUCCESS_MAG = "数据删除成功";


    public static final String SAVEEXPRESS_ERROR_MAG = "快件增加失败";
    public static final String SAVEEXPRESS_SUCCESS_MAG = "快件增加成功";
    public static final String UPDATEEXPRESS_ERROR_MAG = "快件修改失败";
    public static final String UPDATEEXPRESS_SUCCESS_MAG = "快修改加成功";
    public static final String DELETEEXPRESS_ERROR_MAG = "快件删除失败";
    public static final String DELETEEXPRESS_SUCCESS_MAG = "快修删除成功";

    public static final String QEXPRESS_SUCCESS_MAG = "取件成功";
    public static final String QEXPRESS_ERROR_MAG = "取件失败";

}
