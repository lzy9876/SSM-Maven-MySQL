package cn.lzy.util;

import lombok.Data;

/**
 * @ClassName Result
 * @Author:Liziy
 * @Date 2019/10/11 17:03
 * @Description: 控制器返回值的封装对象
 **/

@Data
public class Result {

    //状态码
    private int code;

    //状态信息
    private String msg;

    //返回数据
    private Object data;


}
