package cn.lzy.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName CookieUtil
 * @Author:Liziy
 * @Date 2020/6/7 8:57
 * @Description: cookie获取工具类
 **/
public class CookieUtil {

    /**
     * @Author liziyang
     * @Description 获取cookie
     * @Date 20:26 2020/6/7
     * @Param [key 键, request]
     * @return java.lang.String
     **/
    public static String getCookie(String key, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
