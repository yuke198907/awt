package com.simpo.tracker.web.annotation.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simpo.tracker.common.IntegerTools;
import com.simpo.tracker.common.PagerUtils;
import com.simpo.tracker.common.SystemUtil;
import com.simpo.tracker.web.annotation.SystemControllerLog;
import com.simpo.tracker.web.annotation.entity.Xtgl_Login_Info;
import com.simpo.tracker.web.annotation.service.Xtgl_Login_Service;

import net.sf.json.JSONArray;


@Controller
@RequestMapping("/login")
public class Xtgl_Login_Controller {

    @Autowired
    private Xtgl_Login_Service loginService;

    /**
     * 登录日志查询列表
     *
     * @param request
     * @return
     */
    @RequestMapping("/list.do")
    @SystemControllerLog(description = "登录日志查询/列表查询")
    public void list(Xtgl_Login_Info info, HttpServletRequest request, HttpServletResponse response) {
        String pageNo = request.getParameter("start");
        String pageSize = request.getParameter("length");

        //UserInfo user = (UserInfo) request.getSession().getAttribute("USERINFO");

        if (pageNo == null) {
            pageNo = PagerUtils.pageNo;
        }
        if (pageSize == null) {
            pageSize = PagerUtils.pageSize;
        }

        if (info.getUsername() != null && !"".equals(info.getUsername())) {
            info.setUsername("%" + info.getUsername() + "%");
        }

        String output = "";
        List<Xtgl_Login_Info> list = loginService.list(((IntegerTools.parseInt(pageNo) / IntegerTools.parseInt(pageSize)) + 1) + "", pageSize, info);
        JSONArray json = JSONArray.fromObject(list);

        int count = loginService.count(info);

        output = "{\"recordsTotal\":" + count + ",\"rows\":" + json.toString() + "}";
        SystemUtil.writeJson(output, response);
    }

}
