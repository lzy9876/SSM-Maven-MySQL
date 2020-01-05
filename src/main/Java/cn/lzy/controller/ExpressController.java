package cn.lzy.controller;

import cn.lzy.service.ExpressService;
import cn.lzy.util.Constant;
import cn.lzy.util.DateUtil;
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
 * @ClassName ExpressController
 * @Author:Liziy
 * @Date 2019/11/30 22:48
 * @Description:
 **/
@Controller
public class ExpressController {
    @Autowired
    ExpressService expressService;

    @GetMapping("views/express/expresslist")
    public String toExpressList(){
        return "views/express/expresslist";
    }

    @GetMapping("views/express/addexpress")
    public String toaddExpress(){
        return "views/express/addexpress";
    }

    @PostMapping("views/express/addexpress")
    public @ResponseBody
    Result addExpress(Integer code, String company, String username, String phone){
        Map map = new HashMap();
        if(code == null || company == null || company =="" || username == null || username == "" || phone == null || phone == ""){
            Result result = new Result();
            result.setCode(Constant.ERROR1);
            result.setMsg(Constant.DATA_ERROR_MSG);
            return result;
        }
        map.put("eNum",code);
        map.put("company",company);
        map.put("username",username);
        map.put("uPhone",phone);
        map.put("inTime", DateUtil.getTime());
        map.put("eStatus",1);
        return expressService.saveExpress(map);
    }

    @GetMapping("/expressslist")
    public @ResponseBody Result expressList(int page, int limit,String uPhone){
        if(uPhone == null || uPhone == ""){
            return expressService.queryExpress(page,limit);
        }
        return expressService.queryExpressByuPhoneLoss(page,limit,uPhone);
    }



    /**
     * @Author liziyang
     * @Description 快件修改页面跳转，将传入的express_id存入cookie中
     * @Date 10:39 2019/12/4
     * @Param [id, response]
     * @return java.lang.String
     **/
    @GetMapping("views/express/updateexpress/{id}")
    public String toUpdateexpress(@PathVariable Integer id, HttpServletResponse response){
        Cookie cookie = new Cookie("express_id",id.toString());//创建新cookie
        cookie.setMaxAge(5 * 60);// 设置存在时间为5分钟
        cookie.setPath("/");//设置作用域
        response.addCookie(cookie);//将cookie添加到response的cookie数组中返回给客户端
        return "views/express/updateexpress";
    }

    /**
     * @Author liziyang
     * @Description 根据快件id 查询快件信息
     * @Date 12:53 2019/12/4
     * @Param [id]
     * @return cn.lzy.util.Result
     **/
    @GetMapping("/express")
    public @ResponseBody Result expressById(Integer id){
        if(id == null){
            Result result = new Result();
            result.setCode(Constant.ERROR1);
            result.setMsg(Constant.DATA_ERROR_MSG);
            return result;
        }
        return expressService.queryExpressById(id);
    }

    /**
     * @Author liziyang
     * @Description 快件信息修改
     * @Date 12:53 2019/12/4
     * @Param [id, eNum, company, username, uPhone, inTime]
     * @return cn.lzy.util.Result
     **/
    @PostMapping("/updateexpress")
    public @ResponseBody Result updateexpress(Integer id, String eNum, String company,
                                              String username, String uPhone, String inTime){
        Result result = new Result();
        if(id == null){
            result.setCode(Constant.ERROR1);
            result.setMsg(Constant.DATA_ERROR_MSG);
            return  result;
        }
        Map map = new HashMap();
        map.put("id",id);
        map.put("eNum",eNum);
        map.put("company",company);
        map.put("username",username);
        map.put("uPhone",uPhone);
        map.put("inTime",inTime);
        return expressService.updateExpressById(map);
    }

    @PostMapping("/deleteexpress")
    public @ResponseBody Result deleteExpress(Integer id){
        if(id == null){
            Result result = new Result();
            result.setCode(Constant.ERROR1);
            result.setMsg(Constant.DATA_ERROR_MSG);
            return result;
        }
        return expressService.deleteExpressById(id);
    }


}


