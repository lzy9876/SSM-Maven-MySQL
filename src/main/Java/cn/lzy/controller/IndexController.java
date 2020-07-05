package cn.lzy.controller;

import cn.lzy.entity.ConsoleData;
import cn.lzy.service.CourierService;
import cn.lzy.service.ExpressService;
import cn.lzy.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.lzy.entity.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Console;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName AdminController
 * @Author:Liziy
 * @Date 2019/11/13 22:16
 * @Description: 后台主页页面控制器
 **/
@Controller
public class IndexController {

    @Autowired
    CourierService courierService;

    @Autowired
    ExpressService expressService;

    @Autowired
    RedisUtil redisUtil;

    @GetMapping("/toindex")
    public String toIndex(){
        return "index";
    }


    @GetMapping("views/console")
    public String toConsole(){
        return "views/console";
    }

    /**
     * @Author liziyang
     * @Description 管理员后台登录页面跳转
     * @Date 12:43 2019/12/5
     * @Param []
     * @return java.lang.String
     **/
    @GetMapping("/toadminLogin")
    public String  toLogin(){
        return "login";
    }


    /**
     * @Author liziyang
     * @Description 退出登录 删除对应用户的redis  key
     * @Date 16:00 2019/12/26
     * @Param [response]
     * @return java.lang.String
     **/
    @GetMapping("/quitLogin")
    public @ResponseBody
    Result adminQuit(HttpServletResponse response, HttpServletRequest request){
        System.out.println(request);
        boolean flag = false;
        String userId = CookieUtil.getCookie("userId", request);
        if(redisUtil.hasKey(userId+"token")) {
            redisUtil.del(userId + "token");
        }
        if(!redisUtil.hasKey(userId+"token")) {
            //if(false) 进入 表示未找到 删除成功 将flag = true
            flag = true;
        }
        Result result = new Result();
        //flag = true 表示删除成功
        if(flag){
            result.setCode(Constant.SUCCESS);
            result.setMsg(Constant.SUCCESS_SUCCESS_MSG);
        }else{
            result.setCode(Constant.ERROR1);
            result.setMsg("redis 删除失败");
        }

        return result;
    }


    /**
     * @Author liziyang
     * @Description  管理员登录控制器
     * @Date 17:05 2019/12/5
     * @Param [username, password, response]
     * @return cn.lzy.util.Result
     **/
    @PostMapping("/adminLogin")
    public @ResponseBody
    Result adminLogin(String username, String password, HttpServletResponse response, HttpServletRequest request){
        Result result = new Result();
        if(username == null && username == "" && password == "" &&  password == ""){
            result.setCode(Constant.ERROR1);
            result.setMsg(Constant.DATA_ERROR_MSG);
            return  result;
        }
        return  courierService.login(username,password,response,request);
    }

    /**
     * @Author liziyang
     * @Description 注册页面跳转
     * @Date 16:02 2019/12/5
     * @Param []
     * @return java.lang.String
     **/
    @GetMapping("/toRegister")
    public String  toRegister(){
        return "reg";
    }

    /**
     * @Author liziyang
     * @Description 控制台首页统计数据
     * @Date 19:59 2019/12/17
     * @Param []
     * @return cn.lzy.util.Result
     **/
    @GetMapping("console_statistics")
    public @ResponseBody Result consoleData(){

        Map map = new HashMap<>();
        //普通用户总数量 role = 2
        map.put("role",2);
        String userConuntRole2 = courierService.queryUserCount(map).getData().toString();

        //管理员总数量
        map.put("role",1);
        String userConuntRole1 = courierService.queryUserCount(map).getData().toString();

        //入库总快件数量
        String expressConunt = expressService.queryExpressCount(map).getData().toString();

        //快件总待取数量
        map.put("eStatus",1);
        String expressConunteStatus1 = expressService.queryExpressCount(map).getData().toString();

        //普通用户日注册数量
        map.put("role",2);
        map.put("toDate", DateUtil.getTime().substring(0,10)+DateUtil.time1);
        map.put("doDate", DateUtil.getTime().substring(0,10)+DateUtil.time2);
        String userConuntRole2Date = courierService.queryUserCount(map).getData().toString();

        //管理员户日注册数量
        map.put("role",1 );
        String userConuntRole1Date = courierService.queryUserCount(map).getData().toString();

        //快件日取件
        map.put("eStatus",2);
        String expressConunteStatus2Data = expressService.queryExpressCount(map).getData().toString();
        //日新增快件总数量
        map.put("eStatus",1);
        String expressConunteStatus1Data = expressService.queryExpressCount(map).getData().toString();

        //数据装填如consoleDate对象中
        ConsoleData consoleData = new ConsoleData(userConuntRole2,userConuntRole1,expressConunt,expressConunteStatus1,userConuntRole2Date,userConuntRole1Date,
                expressConunteStatus2Data,expressConunteStatus1Data);
//        ConsoleData consoleData = new ConsoleData();
//        consoleData.setUserConuntRole2(userConuntRole2);

        return new Result(Constant.SUCCESS,Constant.SUCCESS_SUCCESS_MSG,consoleData,0);
    }

    @GetMapping("/loginInterceptor")
    public String loginInterceptor(){
        return "interceptor/loginInterceptor";
    }
}
