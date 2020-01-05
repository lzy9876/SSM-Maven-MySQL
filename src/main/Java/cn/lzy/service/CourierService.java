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

    /**
     * @Author liziyang
     * @Description 根据role查询用户信息
     * @Date 16:14 2019/11/22
     * @Param [role]
     * @return cn.lzy.util.Result
     **/
    Result queryUserByRole(Integer role, int page, int limit);

    /**
     * @Author liziyang
     * @Description 根据id 查询用户信息
     * @Date 17:22 2019/11/25
     * @Param [id]
     * @return cn.lzy.util.Result
     **/
    Result queryUserById(Integer id);


    /**
     * @Author liziyang
     * @Description 用户信息更新
     * @Date 17:20 2019/11/28
     * @Param [map]
     * @return cn.lzy.util.Result
     **/
    Result updateCourierById(Map map);

    /**
     * @Author liziyang
     * @Description 用户信息删除
     * @Date 17:20 2019/11/28
     * @Param [map]
     * @return cn.lzy.util.Result
     **/
    Result deleteCourierById(Map map);

   /**
    * @Author liziyang
    * @Description 用户登录
    * @Date 12:53 2019/12/5
    * @Param [name, password]
    * @return cn.lzy.util.Result
    **/
    Result login(String phone, String password);


    /**
     * @Author liziyang
     * @Description
     * @Date 16:30 2019/12/17
     * @Param [map] 参数列表
     *（role）用户角色              role： 1-管理员 2-普通用户
     *（register_data）注册时间     toDate: 从-时间
     *                             doDate；到-时间
     * @return int 查询到的数据数量
     **/
    Result queryUserCount(Map map);

    Result queryUserByPhoneAndRole(int page, int limit, String Phone,int role);

}
