package cn.lzy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName AdminController
 * @Author:Liziy
 * @Date 2019/11/13 22:16
 * @Description: 后台主页页面控制器
 **/
@Controller
public class IndexController {

    @GetMapping("toindex")
    public String toIndex(){
        return "index";
    }


    @GetMapping("views/console")
    public String toConsole(){
        return "views/console";
    }
}
