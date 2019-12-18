package cn.lzy.controller;

import cn.lzy.service.CourierService;
import cn.lzy.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserController
 * @Author:Liziy
 * @Date 2019/11/29 17:02
 * @Description:
 **/
@Controller
public class UserController {
    @Autowired
    CourierService courierService;

    /**
     * @Author liziyang
     * @Description 用户列表页面跳转
     * @Date 15:27 2019/11/30
     * @Param []
     * @return java.lang.String
     **/
    @GetMapping("views/user/userlist")
    public String toUserList(){
        return "views/user/userlist";
    }

    /**
     * @Author liziyang
     * @Description 普通用户列表查询
     * @Date 15:26 2019/11/30
     * @Param [page, limit]
     * @return cn.lzy.util.Result
     **/
    @GetMapping("/userlistByRole")
    public @ResponseBody
    Result userlist(int page, int limit){
        return courierService.queryUserByRole(2, page, limit);
    }

    /**
     * @Author liziyang
     * @Description 用户信息更新
     * @Date 15:27 2019/11/30
     * @Param [response, id]
     * @return java.lang.String
     **/
    @GetMapping("views/user/updateuser/{id}")
    public String toupdateuser(HttpServletResponse response, @PathVariable Integer id){
        Cookie cookie = new Cookie("courier_id",id.toString());//创建新cookie
        cookie.setMaxAge(5 * 60);// 设置存在时间为5分钟
        cookie.setPath("/");//设置作用域
        response.addCookie(cookie);//将cookie添加到response的cookie数组中返回给客户端
        return "views/user/updateuser";
    }

    /**
     * @Author liziyang
     * @Description 用户信息增加页面跳转
     * @Date 15:28 2019/11/30
     * @Param []
     * @return java.lang.String
     **/
    @GetMapping("views/user/adduser")
    public String toAdduser(){

        return  "views/user/adduser";
    }

    /**
     * @Author liziyang
     * @Description 用户增加
     * @Date 15:28 2019/11/30
     * @Param [username, phone, password, sex]
     * @return cn.lzy.util.Result
     **/
    @PostMapping("views/user/adduser")
    public @ResponseBody
    Result addadduser(String username, String phone,
                        String password, String sex){
        Map map = new HashMap<>();
        map.put("name",username);
        map.put("phone",phone);
        map.put("pass",password);
        map.put("sex",sex);
        //添加普通用户状态2
        map.put("role",2);
        return courierService.saveUser(map);
    }

}
