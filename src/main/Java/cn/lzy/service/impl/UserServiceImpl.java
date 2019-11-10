package cn.lzy.service.impl;

import cn.lzy.dao.UserDao;
import cn.lzy.entity.User;
import cn.lzy.service.UserService;
import cn.lzy.util.Constant;
import cn.lzy.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Author:Liziy
 * @Date 2019/11/7 0:28
 * @Description:
 **/
@Service
public class UserServiceImpl implements UserService {



    @Autowired
    UserDao userDao;

    @Override
    public Result queryUserAll() {
        Result result = new Result();
        List<User> userList = userDao.queryUserAll();

        //判断是否查询到数据
        if(userList.size() == 0 ){
            result.setCode(Constant.ERROR1);
            result.setMsg(Constant.DATA_ERROR_MSG);
            return  result;
        }

        result.setCode(Constant.SUCCESS);
        result.setMsg(Constant.SUCCESS_SUCCESS_MSG);
        result.setData(userList);
        return  result;
    }
}
