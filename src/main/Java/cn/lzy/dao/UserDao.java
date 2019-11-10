package cn.lzy.dao;

import cn.lzy.entity.User;

import java.util.List;

/**
 * @ClassName UserDao
 * @Author:Liziy
 * @Date 2019/11/7 0:17
 * @Description: 数据访问层接口
 **/
public interface UserDao {


    List<User> queryUserAll();
}
