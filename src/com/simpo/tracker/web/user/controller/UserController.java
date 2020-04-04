package com.simpo.tracker.web.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simpo.tracker.common.IntegerTools;
import com.simpo.tracker.common.MD5Util;
import com.simpo.tracker.common.PagerUtils;
import com.simpo.tracker.common.SystemUtil;
import com.simpo.tracker.web.annotation.SystemControllerLog;
import com.simpo.tracker.web.role.entity.RoleInfo;
import com.simpo.tracker.web.role.service.RoleService;
import com.simpo.tracker.web.user.entity.UserInfo;
import com.simpo.tracker.web.user.service.UserService;

import net.sf.json.JSONArray;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/list.do")
	@SystemControllerLog(description = "用户管理/列表查询")  
	public void list(UserInfo info, HttpServletRequest request, HttpServletResponse response){
		String pageNo = request.getParameter("start");
		String pageSize = request.getParameter("length");
		UserInfo user = (UserInfo) request.getSession().getAttribute("USERINFO");
		
		if(pageNo == null){
			pageNo = PagerUtils.pageNo;
		}
		if(pageSize == null){
			pageSize = PagerUtils.pageSize;
		}
		
		if(info.getUsername() != null && !"".equals(info.getUsername())){
			info.setUsername("%"+info.getUsername()+"%");
		}
		
		String output = "";
		List<UserInfo> list = userService.list(((IntegerTools.parseInt(pageNo)/IntegerTools.parseInt(pageSize))+1)+"",pageSize,info);
		JSONArray json = JSONArray.fromObject(list);
		
		int count =userService.count(info);
		
		List<RoleInfo> roles = roleService.list(false, null, null, null);
		request.getSession().setAttribute("ROLES", roles);
		
		output = "{\"recordsTotal\":" + count + ",\"rows\":" + json.toString() + "}";
		SystemUtil.writeJson(output, response);
	}
	
	@RequestMapping("/updateStatus.do")
	@SystemControllerLog(description = "用户管理/启用停用")  
	public void updateStatus(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id");
		UserInfo info = userService.findInfoById(Long.valueOf(id));
		int row = 0;
		if("0".equals(info.getStatus())){
			row = userService.updateStatus(Long.valueOf(id), "1");
		}else{
			row = userService.updateStatus(Long.valueOf(id), "0");
		}
		SystemUtil.writeHTML(row+"", response);
	}
	
	@RequestMapping("/remark.do")
	@SystemControllerLog(description = "用户管理/重置密码")  
	public void remark(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id");
		//UserInfo info = userService.findInfoById(Long.valueOf(id));
		int row = userService.updatePwd(Long.valueOf(id), MD5Util.MD5("000000"));
		SystemUtil.writeHTML(row+"", response);
	}
	
	@RequestMapping("/add.do")
	@SystemControllerLog(description = "用户管理/新增信息")  
	public void add(UserInfo info, HttpServletRequest request, HttpServletResponse response){
		//username,loginname,password,orgname,phone,remark
		/*String username = request.getParameter("username");
		String loginname = request.getParameter("loginname");
		String orgname = request.getParameter("orgname");
		String phone = request.getParameter("phone");
		String remark = request.getParameter("remark");
		
		UserInfo info = new UserInfo();
		
		info.setLoginname(loginname);
		info.setOrgname(orgname);
		info.setPhone(phone);
		info.setUsername(username);
		info.setRemark(remark);*/
		info.setPassword(MD5Util.MD5("000000"));
		
		int row = userService.add(info);
		SystemUtil.writeHTML(row+"", response);
	}
	
	@RequestMapping("/update.do")
	@SystemControllerLog(description = "用户管理/编辑信息")  
	public void update(UserInfo info, HttpServletRequest request, HttpServletResponse response){
		int row = 0;
		
		if(info.getId() > 0){
			
			row = userService.update(info);
		}else{
			
			info.setPassword(MD5Util.MD5("000000"));
			row = userService.add(info);
		}
		
		SystemUtil.writeHTML(row+"", response);
	}
	
	@RequestMapping("/find.do")
	@SystemControllerLog(description = "用户管理/表单查看")  
	public String find(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id");
		
		UserInfo info = userService.findInfoById(Long.valueOf(id));
		
		List<RoleInfo> roles = roleService.list(false, null, null, null);
		request.getSession().setAttribute("ROLES", roles);
		
		request.setAttribute("info", info);
		
		return "/page/user_form.jsp";
	}
}
