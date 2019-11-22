package cn.lzy.service.impl;

import cn.lzy.entity.User;
import cn.lzy.dao.CourierDao;
import cn.lzy.service.CourierService;
import cn.lzy.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @ClassName CourierServiceImpl
 * @Author:Liziy
 * @Date 2019/11/14 0:53
 * @Description:
 **/
@Service
public class CourierServiceImpl implements CourierService {

    @Autowired
    CourierDao courierDao;

    @Override
    public Result queryUserByPhone(String phone) {
        Result result = new Result();
        User user = courierDao.queryUserByPhone(phone);
        if(user == null){
            result.setData(Constant.ERROR1);
            result.setMsg(Constant.PHONE_ERROR_MSG);
            return result;
        }
        result.setData(Constant.SUCCESS);
        result.setMsg(Constant.SUCCESS_SUCCESS_MSG);
        result.setData(user);
        return null;
    }

    /**
     * @Author liziyang
     * @Description 添加快递员-
     *              1.根据手机号查询该手机是否被注册过
     *              2.添加用户信息
     * @Date 15:26 2019/11/22
     * @Param [map]
     * @return cn.lzy.util.Result
     **/
    @Override
    public Result saveUser(Map map) {
        Result result = new Result();

        User user = courierDao.queryUserByPhone((String) map.get("phone"));

        //手机号是否被注册
        if(user != null){
            result.setCode(Constant.ERROR1);
            result.setMsg(Constant.PHONE_ERROR_MSG);
            return result;
        }

        //随机生成盐
        String salt = PasswordUtil.salt();
        //密码加密
        map.put("pass",PasswordUtil.md5(map.get("pass")+salt));
        map.put("salt",salt);
        //添加管理员状态0
        map.put("role",0);
        //添加注册时间
        map.put("register_data", DataUtil.getTime());
        //派件初始化
        map.put("count",0);
        if(map.get("sex") .equals("男")){
            //男
            map.put("sex",1);
        }else{
            map.put("sex",2);
        }
        int save = courierDao.saveUser(map);
        //用户新增失败
        if(save == 0 ){
            result.setCode(Constant.ERROR1);
            result.setMsg(Constant.SAVEUSER_ERROR_MSG);
            return result;
        }
        result.setCode(Constant.SUCCESS);
        result.setMsg(Constant.SUCCESS_SUCCESS_MSG);
        return result;
    }
}
