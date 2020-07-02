package cn.lzy.interceptors;

import cn.lzy.util.CookieUtil;
import cn.lzy.util.JWTUtil;
import cn.lzy.util.RedisUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName UserLoginInterceptor
 * @Author:Liziy
 * @Date 2020/5/20 8:47
 * @Description:
 **/
public class UserLoginInterceptor implements HandlerInterceptor {

    private static final Log logger = LogFactory.getLog(UserLoginInterceptor.class);

    private List<String> exceptUrls;

    public List<String> getExceptUrls() {
        return exceptUrls;
    }

    public void setExceptUrls(List<String> exceptUrls) {
        this.exceptUrls = exceptUrls;
    }

    @Autowired
    RedisUtil redisUtil;


    /**
     * @Author liziyang
     * @Description 检查当前会话是token是否正确，正确放行，错误拦截
     * @Date 8:58 2020/5/20
     * @Param [request, response, handler]
     * @return boolean
     **/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入preHandle");
        //flag 用于判断用户是否登录,默认为false
        boolean flag = false;
        //获取请求的路径进行判断
        //String servletPath = request.getServletPath();
        String url = request.getRequestURI();
        System.out.println("获取到的URL "+url);

        //过滤不需要拦截的页面
        for(String urls : exceptUrls){
            /*
             * String.contains()方法比较的是 是否包含
             * 当urls放行请求集合列表中有值为register url的值只要包含了r e g i s t e r 这六个字符 请求就会放行
             * 我认为不不很合理 但是使用如果equals 在获取controller层页面跳转的页面地址 请求会带/WEB-INF/WEB/** 的全路径
             * 会造成拦截器的死循环 所以这里使用contains 需要在程序设计时 避开 urls 列表的请求
             * */
            if(url.contains(urls)){
                flag = true;
                break;
            }
        }
        System.out.println("第一次拦截 "+flag);

        /*
         * 1.拿到客户端的cookie中的userId
         * 2.通过key = userIdtoken 获取 Redis 的 value
         * 3.验证value(令牌)是否过期
         *
         * 当页面被拦截时进入 flag = false
         * */
        if(!flag){

            //1.检查cookie
            String userId = CookieUtil.getCookie("userId",request);
            System.out.println("用户cookie "+userId);
            if(userId != null){
                //2.验证令牌是否存在
                if(redisUtil.hasKey(userId+"token")){
                    //令牌存在
                    //3.拿到Redis中的token令牌 验证是否过期
                    if(JWTUtil.verify(redisUtil.get( userId + "token").toString())){
                        //令牌未过期 检验合格 设置flag = trtue
                        flag = true;
                    }
                }
            }

        }
        //登录被拦截 重定向到拦截页面
//        if(!flag){
//            response.sendRedirect("/loginInterceptor");
//            //request.getRequestDispatcher("/loginInterceptor").forward(request,response);
//        }
        System.out.println("是否允许通过 "+flag);
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
       // System.out.println("进入postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //System.out.println("进入afterCompletion");
    }
}
