package cn.lzy.service.impl;

import cn.lzy.entity.User;
import cn.lzy.dao.CourierDao;
import cn.lzy.service.CourierService;
import cn.lzy.util.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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
        return result;
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
        //添加注册时间
        map.put("register_data", DateUtil.getTime());
        //性别初始化
        map.put("sex",SexUtil.getSexInt((String) map.get("sex")));
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

    /**
     * @Author liziyang
     * @Description 根据role查询用户新
     * @Date 16:18 2019/11/22
     * @Param [role]
     * @return cn.lzy.util.Result
     **/
    @Override
    public  Result queryUserByRole(Integer role,int page, int limit) {
        Result result = new Result();
        PageHelper.startPage(page,limit);
        List<User> userList = courierDao.queryUserByRole(role);
        if(userList.size() == 0){
            //未查询到数据
            result.setCode(Constant.ERROR1);
            result.setMsg(Constant.QUERY_DATA_NULL_MSG);
            return  result;
        }
        result.setCode(Constant.LAYUI_SUCCESS);
        result.setMsg(Constant.SUCCESS_SUCCESS_MSG);
        result.setData(userList);
        PageInfo<User> pageInfo= new PageInfo<>(userList);
        //查询总数量
        result.setCount(pageInfo.getTotal());
        return  result;
    }

    @Override
    public Result queryUserById(Integer id) {
           Result result = new Result();
           User user = courierDao.queryUserById(id);
           if(user == null){
               result.setCode(Constant.ERROR1);
               result.setMsg(Constant.QUERY_DATA_NULL_MSG);
               return result;
           }
            result.setCode(Constant.SUCCESS);
            result.setMsg(Constant.SUCCESS_SUCCESS_MSG);
            result.setData(user);
        return result;
    }

    @Override
    public Result updateCourierById(Map map) {
        Result result = new Result();
        if(map == null){
            result.setCode(Constant.ERROR1);
            result.setMsg(Constant.DATA_ERROR_MSG);
            return result;
        }
        int courierUpdate = courierDao.updateCourierById(map);
        if(courierUpdate == 0){
            result.setCode(Constant.ERROR1);
            result.setMsg(Constant.UPDATE_DATA_ERROR_MAG);
            return result;
        }
        result.setCode(Constant.SUCCESS);
        result.setMsg(Constant.UPDATE_DATA_SUCCESS_MAG);
        return result;
    }

    @Override
    public Result deleteCourierById(Map map) {
        Result result = new Result();
        if(map.size() == 0){
            result.setCode(Constant.ERROR1);
            result.setMsg(Constant.DATA_ERROR_MSG);
            return result;
        }
        int courierDelete = courierDao.deleteCourierById(map);
        if(courierDelete == 0){
            result.setCode(Constant.ERROR1);
            result.setMsg(Constant.DELETE_DATA_ERROR_MAG);
            return result;
        }
        result.setCode(Constant.SUCCESS);
        result.setMsg(Constant.DELETE_DATA_SUCCESS_MAG);
        return result;
    }

    @Override
    public Result login(String phone, String password) {
        Result result = new Result();
        User user = courierDao.queryUserByPhone(phone);
        if(user == null ){
            result.setCode(Constant.ERROR1);
            result.setMsg(Constant.LOGIN_ERROR1_MSG);
            return result;
        }

        //用户输入的密码
        String userPass = PasswordUtil.md5(password+user.getSalt());
        //密码比对
        if(!userPass.equals(user.getPass())){
            result.setCode(Constant.ERROR1);
            result.setMsg(Constant.LOGIN_ERROR2_MSG);
            return result;
        }
        result.setCode(Constant.SUCCESS);
        result.setMsg(Constant.LOGIN_SUCCESS_MSG);
        result.setData(user);
        return result;
    }

    @Override
    public Result queryUserCount(Map map) {
        int i = courierDao.queryUserCount(map);
        return new Result(Constant.SUCCESS,Constant.SUCCESS_SUCCESS_MSG,i,0);
    }

    @Override
    public Result queryUserByPhoneAndRole(int page, int limit, String Phone,int role) {
        Map map = new HashMap();
        map.put("phone",Phone);
        map.put("role",role);
        List<User> userList = courierDao.queryUserByPhoneAndRole(map);
        if(userList.size() == 0){
            return new Result(Constant.ERROR1,Constant.QUERY_DATA_NULL_MSG,null,0);
        }

        PageInfo<User> pageInfo= new PageInfo<>(userList);
        //传入 userList 和 pageInfo.getTotal()总数据条数
        return new Result(Constant.LAYUI_SUCCESS,Constant.SUCCESS_SUCCESS_MSG,userList,pageInfo.getTotal());

    }

}
