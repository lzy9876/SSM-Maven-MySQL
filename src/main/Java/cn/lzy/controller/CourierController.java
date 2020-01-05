package cn.lzy.controller;

import cn.lzy.service.CourierService;
import cn.lzy.util.Constant;
import cn.lzy.util.Result;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @ClassName CourierController
 * @Author:Liziy
 * @Date 2019/11/13 22:53
 * @Description: Courier控制器
 **/
@Controller
public class CourierController {


    @Autowired
    CourierService courierService;

    /**
     * @Author liziyang
     * @Description //快递员列表展示
     * @Date 0:06 2019/11/14
     * @Param []
     * @return java.lang.String
     **/
    @GetMapping("views/courier/courierlist")
    public String toCourierlist(){
        return  "views/courier/courierlist";

    }

    /**
     * @Author liziyang
     * @Description 根据用户id 查询用户信息
     * @Date 16:08 2019/12/26
     * @Param [userId]
     * @return cn.lzy.util.Result
     **/
    @PostMapping("queryUserById")
    public @ResponseBody Result queryUserById(Integer userId){
        return courierService.queryUserById(userId);
    }


    /**
     * @Author liziyang
     * @Description //快递员新增页面跳转
     * @Date 0:06 2019/11/14
     * @Param []
     * @return java.lang.String
     **/
    @GetMapping("views/courier/addcourier")
    public String toAddcourier(){

        return  "views/courier/addcourier";
    }

    /**
     * @Author liziyang
     * @Description 快递员新增
     * @Date 0:05 2019/11/14
     * @Param [username, phonenumber, idcard, password, sex]
     * @return java.util.Map
     **/
    @PostMapping("views/courier/addcourier")
    public @ResponseBody
    Result addaddcourier(String username, String phone,
                      String idcard, String password, String sex){
       Map map = new HashMap<>();
        map.put("name",username);
        map.put("phone",phone);
        map.put("pass",password);
        map.put("sex",sex);
        map.put("id_card",idcard);
        //添加管理员状态1
        map.put("role",1);
        //派件初始化
        map.put("count",0);
     return courierService.saveUser(map);
    }

    /**
     * @Author liziyang
     * @Description 根据手机号查询用户信息
     * @Date 15:32 2019/11/24
     * @Param [phone]
     * @return cn.lzy.util.Result
     **/
    @GetMapping("/courier")
    public @ResponseBody
    Result courierBycourier(Integer id){

        if(id == null) {
            Result result = new Result();
            result.setCode(Constant.ERROR1);
            result.setMsg(Constant.DATA_ERROR_MSG);
            return  result;
        }
        return courierService.queryUserById(id);
    }


    @GetMapping("vuetest")
    public String tovuetest(HttpServletResponse response){
        Cookie cookie = new Cookie("name_test","1002");//创建新cookie
        cookie.setMaxAge(5 * 60);// 设置存在时间为5分钟
        cookie.setPath("/");//设置作用域
        response.addCookie(cookie);//将cookie添加到response的cookie数组中返回给客户端

        return "views/courier/vuetest";
    }

    /**
     * @Author liziyang
     * @Description 快递员列表查询
     * @Date 23:44 2019/11/23
     * @Param [role]
     * @return cn.lzy.util.Result
     **/
    @GetMapping("/courierlistByRole")
    public @ResponseBody Result courierlist(int page, int limit, String phone){
        return courierService.queryUserByPhoneAndRole(page,limit,phone,1);
    }

    @GetMapping("views/courier/updatecourier/{id}")
    public String toupdatecourier(HttpServletResponse response, @PathVariable Integer id){
        Cookie cookie = new Cookie("courier_id",id.toString());//创建新cookie
        cookie.setMaxAge( 30);// 设置存在时间为30秒
        cookie.setPath("/");//设置作用域
        response.addCookie(cookie);//将cookie添加到response的cookie数组中返回给客户端
        return "views/courier/updatecourier";
    }
    @PostMapping("updatecourier")
    public @ResponseBody Result updateCourier(Integer id, String username, String phone,
                                       String idcard, String password){
        Map map = new HashMap();
        if(username != null && username != ""){
            map.put("name",username);
        }else if(phone != null && phone != ""){
            map.put("phone",phone);
        }else if(idcard != null && idcard != ""){
            map.put("id_card",idcard);
        }else if(id == null){
            Result result = new Result();
            result.setCode(Constant.ERROR1);
            result.setMsg(Constant.DATA_ERROR_MSG);
            return result;
        }
        map.put("id",id);
        return courierService.updateCourierById(map);

    }

    @PostMapping("deletecourier")
    public @ResponseBody Result deleteCourier(Integer id){
        if(id == null){
            Result result = new Result();
            result.setCode(Constant.ERROR1);
            result.setMsg(Constant.DATA_ERROR_MSG);
            return result;
        }
        Map map = new HashMap();
        map.put("id",id);
        return  courierService.deleteCourierById(map);

    }

}
