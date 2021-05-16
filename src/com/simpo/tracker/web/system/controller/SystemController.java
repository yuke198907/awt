package com.simpo.tracker.web.system.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simpo.tracker.common.MD5Util;
import com.simpo.tracker.common.SystemUtil;
import com.simpo.tracker.web.annotation.SystemControllerLog;
import com.simpo.tracker.web.annotation.entity.Xtgl_Login_Info;
import com.simpo.tracker.web.annotation.service.Xtgl_Login_Service;
import com.simpo.tracker.web.user.entity.UserInfo;
import com.simpo.tracker.web.user.service.UserService;


@Controller
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private UserService userService;
    @Autowired
    private Xtgl_Login_Service loginService;

    private static final String Issuccess_0 = "0";
    private static final String Issuccess_1 = "1";

    /**
     * 登录
     *
     * @param request
     * @return
     */
    @RequestMapping("/login.do")
    public String login(String loginname, String pwd, HttpServletRequest request, HttpServletResponse response) {
        String result = "";

        UserInfo user = userService.findInfoByLoginname(loginname);

        if (user != null) {
            Xtgl_Login_Info login = new Xtgl_Login_Info();
            login.setIpaddress(getIpAddress(request));
            login.setLoginname(loginname);
            login.setLogintime(new Date());
            login.setUserid(user.getId());
            login.setIssuccess(Issuccess_0);

            if (user.getPassword().equals(MD5Util.MD5(pwd))) {
                if ("0".equals(user.getStatus())) {
                    login.setIssuccess(Issuccess_1);
                    request.getSession().setAttribute("USERINFO", user);
                    result = "1";
                } else {
                    result = "-2";
                }
            } else {
                result = "-1";
            }
            loginService.save(login);
        } else {
            result = "0";
        }

        if ("1".equals(result)) {
            return "redirect:/system/main/main.jsp";
        }

        return "redirect:/system/login/login.jsp?result=" + result;
    }

    @RequestMapping("/logout.do")
    @SystemControllerLog(description = "退出系统")
    public String logout(String loginname, String pwd, HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("USERINFO");

        return "redirect:/system/login/login.jsp";
    }

    @RequestMapping("/remark.do")
    @SystemControllerLog(description = "修改密码")
    public void remark(HttpServletRequest request, HttpServletResponse response) {
        UserInfo user = (UserInfo) request.getSession().getAttribute("USERINFO");
        String pwd = request.getParameter("npw");

        int row = userService.updatePwd(user.getId(), MD5Util.MD5(pwd));

        SystemUtil.writeHTML(row + "", response);
    }

    /**
     * 获取登录人的IP地址
     *
     * @param request
     * @return
     */
    public String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
