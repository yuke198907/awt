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
import com.simpo.tracker.web.annotation.entity.Xtgl_Operate_Info;
import com.simpo.tracker.web.annotation.service.Xtgl_Operate_Service;

import net.sf.json.JSONArray;


@Controller
@RequestMapping("/operate")
public class Xtgl_Operate_Controller {

	@Autowired
	private Xtgl_Operate_Service operateService;

	/**
	 * 操作日志查询列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/list.do")
	@SystemControllerLog(description = "操作日志查询/列表查询")
	public void list(Xtgl_Operate_Info info, HttpServletRequest request, HttpServletResponse response){
		String pageNo = request.getParameter("start");
		String pageSize = request.getParameter("length");
		/*String sort = request.getParameter("sort");
		String direction = request.getParameter("direction");
		UserInfo user = (UserInfo) request.getSession().getAttribute("USERINFO");
		*/
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
		List<Xtgl_Operate_Info> list = operateService.list(((IntegerTools.parseInt(pageNo)/IntegerTools.parseInt(pageSize))+1)+"",pageSize,info);
		JSONArray json = JSONArray.fromObject(list);
		
		int count =operateService.count(info);
		
		output = "{\"recordsTotal\":" + count + ",\"rows\":" + json.toString() + "}";
		SystemUtil.writeJson(output, response);
	}
	
}
