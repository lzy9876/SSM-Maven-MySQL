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

    /**
     * @Author liziyang
     * @Description 根据id查询用户信息
     * @Date 17:22 2019/11/25
     * @Param [id]
     * @return cn.lzy.entity.User
     **/
    User queryUserById(Integer id);

    /**
     * @Author liziyang
     * @Description 用户信息更新
     * @Date 16:10 2019/11/28
     * @Param [map]
     * @return int
     **/
    int updateCourierById(Map map);

    /**
     * @Author liziyang
     * @Description 用户信息删除
     * @Date 17:19 2019/11/28
     * @Param [map]
     * @return int
     **/
    int deleteCourierById(Map map);

   /**
    * @Author liziyang
    * @Description //TODO
    * @Date 16:22 2019/12/17
    * @Param [map] 参数列表
    *（role）用户角色              role： 1-管理员 2-普通用户
    *（register_data）注册时间     toDate -- doDate
    * @return int 查询到的数据数量
    **/
    int queryUserCount(Map map);

}
