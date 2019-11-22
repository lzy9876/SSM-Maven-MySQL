package cn.lzy.controller;

import cn.lzy.service.CourierService;
import cn.lzy.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
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
     * @Description //快递员新增
     * @Date 0:05 2019/11/14
     * @Param [username, phonenumber, idcard, password, sex]
     * @return java.util.Map
     **/
    @PostMapping("views/courier/addcourier")
    public @ResponseBody
    Result addaddcourier(String username, String phone,
                      String idcard, String password, String sex){
        System.out.println(username +" "+phone+ ""+ idcard +" "+ password +" "+sex);
       Map map = new HashMap<>();
       map.put("name",username);
       map.put("phone",phone);
       map.put("id_card",idcard);
       map.put("pass",password);
       map.put("sex",sex);

     return courierService.saveUser(map);
    }

    @GetMapping("phone/{phone}")
    public @ResponseBody
    Result addaddcourier(@PathVariable String phone){

        if(phone == null || phone == "") {
            Result result = new Result();
            result.setCode(400);
            result.setMsg("查询失败");
            return  result;
        }
        return courierService.queryUserByPhone(phone);
    }
}
