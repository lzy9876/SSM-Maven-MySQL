package cn.lzy.controller;

import cn.lzy.entity.Express;
import cn.lzy.entity.User;
import cn.lzy.service.CourierService;
import cn.lzy.service.ExpressService;
import cn.lzy.util.Constant;
import cn.lzy.util.DateUtil;
import cn.lzy.util.DeleteCookie;
import cn.lzy.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName WXController
 * @Author:Liziy
 * @Date 2019/12/5 11:22
 * @Description:
 **/

@Controller
public class WXController {

    @Autowired
    CourierService courierService;

    @Autowired
    ExpressService expressService;

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("wx/index")
    public String toWxIndex(){
        return "wx/index";
    }

    @GetMapping("wxlogin")
    public String toWxLogin(){
        return "wx/login";
    }


    @GetMapping("userQuitlogin")
    public String  adminQuit(HttpServletResponse response, HttpServletRequest request){
        DeleteCookie.delete(response,"userId");
        return "wx/login";
    }

    @PostMapping("/wxlogin")
    public @ResponseBody
    Result login(String username, String password, HttpServletResponse response){
        Result result = new Result();
        //判断username & password
        if(username == null || username == "" || username == null || username == ""){
            result.setCode(Constant.ERROR1);
            result.setMsg(Constant.DATA_ERROR_MSG);
            return  result;
        }
        result = courierService.login(username,password,response);
        //账号密码错误直接return
        if(result.getCode() != Constant.SUCCESS){
            return result;
        }
        User user = (User) result.getData();
        Integer role = user.getRole();
        //保存用户登录的角色
        Cookie cookie1 = new Cookie("role",role.toString());//创建新cookie
        cookie1.setMaxAge(30*24*60*60);// 设置存在时间为30天
        cookie1.setPath("/");//设置作用域
        response.addCookie(cookie1);//将cookie添加到response的cookie数组中返回给客户端
        return  result;
    }

    /**
     * @Author liziyang
     * @Description 注册页面跳转
     * @Date 21:26 2019/12/5
     * @Param []
     * @return java.lang.String
     **/
    @GetMapping("wxreg")
    public String toWxReg(){
        return "wx/reg";
    }

    /**
     * @Author liziyang
     * @Description 我的快递页面跳转
     * @Date 21:27 2019/12/5
     * @Param []
     * @return java.lang.String
     **/
    @GetMapping("wx/elist")
    public String toElist(){
        return "wx/elist";
    }

    @PostMapping("wxExpressList")
    public @ResponseBody Result wxExpressList(String userId){
        Result result = new Result();
        //参数数据异常
        if(userId == null || userId == ""){
            result.setCode(Constant.ERROR2);
            //用户登录信息失效
            result.setMsg(Constant.LOGIN_USER_STATUS);
            return  result;
        }
        Map map = new HashMap();
        map.put("userId",userId);
        return expressService.queryExpressByuPhone(map);
    }

    /**
     * @Author liziyang
     * @Description 快递员扫码取件页面跳转
     * @Date 18:50 2019/12/6
     * @Param []
     * @return java.lang.String
     **/
    @GetMapping("pickExpress")
    public String toPickExpress(){
        return "wx/pickExpress";
    }

    @GetMapping("personQRcode")
    public String toPersonQRcode(){
        return "wx/personQRcode";
    }

    @GetMapping("expressAssist")
    public String expressAssist(){
        return "wx/expressAssist";
    }
    /**
     * @Author liziyang
     * @Description 用户取件码页面的 轮询访问服务器的请求
     *              根据取件码访问数据库 判断是否完成取件操作
     * @Date 23:55 2019/12/6
     * @Param []
     * @return cn.lzy.util.Result
     **/
    @PostMapping("ecoderesult")
    public @ResponseBody Result ecoderesult(Integer code){
        if(code == null ){
            //取件码为空 返回错误提示
            return new Result(Constant.ERROR1,Constant.DATA_ERROR_MSG,null,0);
        }
        Map map = new HashMap();
        map.put("code",code);
       return expressService.queryExpressByCodeORuPhone(map);
    }

    /**
     * @Author liziyang
     * @Description 扫码取件 修改取件码
     * @Date 14:23 2019/12/7
     * @Param []
     * @return cn.lzy.util.Result
     **/
    @GetMapping("ecode")
    public @ResponseBody Result ecode(Integer code){
        if(code == null ){
            //取件码为空 返回错误提示
            return new Result(Constant.ERROR1,Constant.DATA_ERROR_MSG,null,0);
        }
        Map map = new HashMap();
        map.put("code",code);
        //根据code 查询快件信息
        List<Express> expressList = (List<Express>) expressService.queryExpressByCodeORuPhone(map).getData();
        if(expressList.size() == 0){
            return new Result(Constant.ERROR1,Constant.DATA_ERROR_MSG,null,0);
        }
        //获取快件id
        for(Express express:expressList){
            map.put("id",express.getId());
        }
        String time = DateUtil.getTime();
        map.put("outTime",time );
        return expressService.updateExpressCodeById(map);
    }

    @GetMapping("redis")
    public @ResponseBody String redis(){

        return redisTemplate.toString();
    }
}
