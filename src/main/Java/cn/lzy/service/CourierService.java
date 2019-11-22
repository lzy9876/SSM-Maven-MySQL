package cn.lzy.service;

import cn.lzy.util.Result;

import java.util.Map;

/**
 * @Author liziyang
 * @Description //快递员service接口
 * @Date 0:55 2019/11/14
 * @Param
 * @return
 **/
public interface CourierService {

    /**
     * @Author liziyang
     * @Description //跟俊用户手机号查询用户信息
     * @Date 16:10 2019/11/21
     * @Param [phone]
     * @return cn.lzy.util.Result
     **/
    Result queryUserByPhone(String phone);

    /**
     * @Author liziyang
     * @Description //增加管理员
     * @Date 16:10 2019/11/21
     * @Param [map]
     * @return cn.lzy.util.Result
     **/
    Result saveUser(Map map);

}
