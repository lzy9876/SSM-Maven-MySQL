package cn.lzy.dao;

import cn.lzy.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @Author liziyang
 * @Date 0:51 2019/11/14
 * @Description  快递员数据访问层接口
 **/

public interface CourierDao {

    /**
     * @Author liziyang
     * @Description //根据手机号查询用户信息
     * @Date 16:09 2019/11/21
     * @Param [phone]
     * @return cn.lzy.entity.User
     **/
    User queryUserByPhone(String phone);

    /**
     * @Author liziyang
     * @Description //用户新增
     * @Date 16:09 2019/11/21
     * @Param [map]
     * @return int
     **/
    int saveUser(Map map);

    /**
     * @Author liziyang
     * @Description //根据role查询用户信息
     * @Date 16:58 2019/11/21
     * @Param [role]
     * @return java.util.List<cn.lzy.entity.User>
     **/
    List<User> queryUserByRole(int role);
}
