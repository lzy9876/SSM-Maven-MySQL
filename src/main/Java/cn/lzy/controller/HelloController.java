package cn.lzy.controller;

import cn.lzy.service.UserService;
import cn.lzy.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName HelloController
 * @Author:Liziy
 * @Date 2019/11/6 23:33
 * @Description:
 **/

@Controller
public class HelloController {

    @Autowired(required = false)
    UserService userService;

    @GetMapping("hello")
    public @ResponseBody
    Result hello(){

        return userService.queryUserAll();
    }
}
