package com.simpo.tracker.web.role.controller;

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
import com.simpo.tracker.web.role.entity.RoleInfo;
import com.simpo.tracker.web.role.service.RoleService;

import net.sf.json.JSONArray;


@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/list.do")
    @SystemControllerLog(description = "列表")
    public void list(RoleInfo info, HttpServletRequest request, HttpServletResponse response) {
        String pageNo = request.getParameter("start");
        String pageSize = request.getParameter("length");

        //UserInfo user = (UserInfo) request.getSession().getAttribute("USERINFO");

        if (pageNo == null) {
            pageNo = PagerUtils.pageNo;
        }
        if (pageSize == null) {
            pageSize = PagerUtils.pageSize;
        }

        if (info.getRolename() != null && !"".equals(info.getRolename())) {
            info.setRolename("%" + info.getRolename() + "%");
        }

        String output = "";
        List<RoleInfo> list = roleService.list(false, ((IntegerTools.parseInt(pageNo) / IntegerTools.parseInt(pageSize)) + 1) + "", pageSize, info);
        JSONArray json = JSONArray.fromObject(list);

        int count = roleService.count(info);

        output = "{\"recordsTotal\":" + count + ",\"rows\":" + json.toString() + "}";
        SystemUtil.writeJson(output, response);
    }

    @RequestMapping("/find.do")
    @SystemControllerLog(description = "查看")
    public String find(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        RoleInfo info = roleService.findInfoById(Long.valueOf(id));
        request.setAttribute("info", info);
        return "/page/role_form.jsp";
    }

    @RequestMapping("/delete.do")
    @SystemControllerLog(description = "删除")
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");

        int row = roleService.delete(Long.valueOf(id));

        SystemUtil.writeHTML(row + "", response);
    }

    @RequestMapping("/update.do")
    @SystemControllerLog(description = "更新")
    public void update(RoleInfo info, HttpServletRequest request, HttpServletResponse response) {

        int row = 0;

        if (info.getId() > 0) {
            row = roleService.update(info);
        } else {
            row = roleService.add(info);
        }

        SystemUtil.writeHTML(row + "", response);
    }

}
