package cn.lzy.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName DeleteCookie
 * @Author:Liziy
 * @Date 2019/12/26 17:36
 * @Description: cookie清除
 **/
public class DeleteCookie {


     public static void  delete(HttpServletResponse response,String key){
        Cookie cookie = new Cookie(key,"");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

    }
}
