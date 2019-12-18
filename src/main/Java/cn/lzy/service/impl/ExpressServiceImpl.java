package cn.lzy.service.impl;

import cn.lzy.dao.CourierDao;
import cn.lzy.dao.ExpressDao;
import cn.lzy.entity.Express;

import cn.lzy.service.CourierService;
import cn.lzy.entity.User;
import cn.lzy.service.ExpressService;
import cn.lzy.util.Constant;
import cn.lzy.util.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @ClassName ExpressServiceImpl
 * @Author:Liziy
 * @Date 2019/12/2 21:13
 * @Description:
 **/
@Service
public class ExpressServiceImpl implements ExpressService {
    @Autowired
    ExpressDao expressDao;

    @Autowired
    CourierDao courierDao;

    @Override
    public Result saveExpress(Map map) {
        Result result = new Result();
        //生成一个六位数的随机数
        Random r = new Random();
        int ran = -1;
        while((ran=r.nextInt(1000000))<100000);

        //判断验证码是否重复
        Map mapcode = new HashMap();
        mapcode.put("code",ran);
        mapcode.put("uPhone",null);
        List<Express> expressList = expressDao.queryExpressByCodeORuPhone(mapcode);
        if(expressList.size() > 0){
            //验证码重复回滚方法
            return saveExpress(map);
        }


        map.put("code",ran);
        int ex = expressDao.saveExpress(map);
        if(ex == 0){
            result.setCode(Constant.ERROR1);
            result.setMsg(Constant.SAVEEXPRESS_ERROR_MAG);
            return result;
        }
        result.setCode(Constant.SUCCESS);
        result.setMsg(Constant.SAVEEXPRESS_SUCCESS_MAG);
        return result;
    }

    @Override
    public Result queryExpressByCodeORuPhone(Map map) {
        List<Express> expressList = expressDao.queryExpressByCodeORuPhone(map);
        if(expressList.size() == 0){
            // 长度为0 取件成功
            return new Result(Constant.SUCCESS,Constant.QEXPRESS_SUCCESS_MAG,null,0);
        }
           //取件失败
        return new Result(Constant.ERROR1,Constant.QEXPRESS_ERROR_MAG,expressList,0);
    }

    @Override
    public Result queryExpressByeStatus(Integer eStatus, int page, int limit) {
        Result result = new Result();
        PageHelper.startPage(page,limit);
        List<Express> expressList = expressDao.queryExpressByeStatus(eStatus);
        if(expressList.size() == 0){
            result.setCode(Constant.ERROR1);
            result.setMsg(Constant.QUERY_DATA_NULL_MSG);
            return result;
        }
        result.setCode(Constant.LAYUI_SUCCESS);
        result.setMsg(Constant.SUCCESS_SUCCESS_MSG);
        result.setData(expressList);
        PageInfo<Express> pageInfo= new PageInfo<>(expressList);
        result.setCount(pageInfo.getTotal());
        return result;
    }

    @Override
    public Result queryExpressById(Integer id) {
        Result result = new Result();
        //根据快件id查询快件信息
        Express express = expressDao.queryExpressById(id);
        if(express == null){
            result.setCode(Constant.ERROR1);
            result.setMsg(Constant.QUERY_DATA_NULL_MSG);
            return  result;
        }

        result.setCode(Constant.SUCCESS);
        result.setMsg(Constant.SUCCESS_SUCCESS_MSG);
        result.setData(express);
        return result;
    }

    @Override
    public Result updateExpressById(Map map) {
        Result result = new Result();
        //快递信息更新
        System.out.println("ID："+map.get("id"));
        System.out.println("code："+map.get("code"));
        int expressUpdate = expressDao.updateExpressById(map);
        if(expressUpdate == 0){
            //更新失败
            result.setCode(Constant.ERROR1);
            result.setMsg(Constant.UPDATEEXPRESS_ERROR_MAG);
            return result;
        }

        result.setCode(Constant.SUCCESS);
        result.setMsg(Constant.UPDATEEXPRESS_SUCCESS_MAG);
        return result;
    }

    @Override
    public Result deleteExpressById(Integer id) {
        Result result = new Result();
        int expressDelete = expressDao.deleteExpressById(id);
        if(expressDelete == 0){
            //删除失败
            result.setCode(Constant.ERROR1);
            result.setMsg(Constant.DELETEEXPRESS_ERROR_MAG);
            return result;
        }
        result.setCode(Constant.SUCCESS);
        result.setMsg(Constant.DELETEEXPRESS_SUCCESS_MAG);
        return result;
    }

    @Override
    public Result queryExpressByuPhone(Map map) {
        Result result = new Result();
        //从map中获取userId 查询用户信息

        User user = courierDao.queryUserById(Integer.parseInt(map.get("userId").toString()));
        //想map中填入uPhone
        map.put("uPhone",user.getPhone());
        List<Express> expressList = expressDao.queryExpressByuPhone(map);
        if(expressList.size() == 0){
            //没有查询到数据
            result.setCode(Constant.ERROR1);
            result.setMsg(Constant.QUERY_DATA_NULL_MSG);
            result.setData(expressList);
            return  result;
        }
        result.setCode(Constant.SUCCESS);
        result.setMsg(Constant.SUCCESS_SUCCESS_MSG);
        result.setData(expressList);
        return  result;
    }

    @Override
    public Result updateExpressCodeById(Map map) {
       int expressUpdate = expressDao.updateExpressCodeById(map);
        if(expressUpdate == 0){
            //更新失败
            return new Result(Constant.ERROR1,Constant.UPDATEEXPRESS_ERROR_MAG,null,0);
        }
        return new Result(Constant.SUCCESS,Constant.UPDATEEXPRESS_SUCCESS_MAG,null,0);
    }

    @Override
    public Result queryExpress(int page, int limit) {
        PageHelper.startPage(page,limit);
        List<Express> expressList = expressDao.queryExpress();
        if(expressList.size() == 0){
            return new Result(Constant.ERROR1,Constant.QUERY_DATA_NULL_MSG,null,0);
        }

        PageInfo<Express> pageInfo= new PageInfo<>(expressList);
       //传入 expressList 和 pageInfo.getTotal()总数据条数
        return new Result(Constant.LAYUI_SUCCESS,Constant.SUCCESS_SUCCESS_MSG,expressList,pageInfo.getTotal());
    }

    @Override
    public Result queryExpressCount(Map map) {
        int i = expressDao.queryExpressCount(map);
        return new Result(Constant.SUCCESS,Constant.SUCCESS_SUCCESS_MSG,i,0);
    }


}
