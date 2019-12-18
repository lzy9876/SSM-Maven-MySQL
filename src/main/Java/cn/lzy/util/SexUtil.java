package cn.lzy.util;

/**
 * @ClassName SexUtil
 * @Author:Liziy
 * @Date 2019/11/22 15:35
 * @Description: 性别转换工具类
 **/
public class SexUtil {

    /**
     * @Author liziyang
     * @Description 输入男女 返回1(男)/2(女)
     * @Date 15:43 2019/11/22
     * @Param [sex]
     * @return int
     **/
    public static  int  getSexInt(String sex){
        if(sex.equals("男")){
            return 1;
        }else if(sex.equals("女")){
            return 2;
        }
        return 400;
    }
}
