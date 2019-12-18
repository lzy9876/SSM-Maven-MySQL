package cn.lzy.controller;

import cn.lzy.entity.wx.SignatureUtil;
import cn.lzy.util.Constant;
import cn.lzy.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName WXConfigController
 * @Author:Liziy
 * @Date 2019/12/7 14:03
 * @Description:
 **/
@Controller
public class WXConfigController {

    /**
     * @Author liziyang
     * @Description 扫码请求
     * @Date 14:11 2019/12/7
     * @Param [xurl]
     * @return cn.lzy.util.Result
     **/
    @GetMapping("wxconf")
    public void wxconf(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        String urlText = request.getParameter("xurl");
        try {
            String json = SignatureUtil.getConfig(urlText ).toJSON();
            pw.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        pw.close();
    }
}
