package com.simpo.tracker.common;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class SystemUtil {

	public static void writeJson(String result, HttpServletResponse response){
		PrintWriter writer = null;
		try{
			response.setContentType("application/json;charset=utf-8");
			writer = response.getWriter();
			writer.write(result);
			writer.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void writeXML(String result, HttpServletResponse response){
		PrintWriter writer = null;
		try{
			response.setContentType("text/xml;charset=UTF-8");
			writer = response.getWriter();
			writer.write(result);
			writer.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void writeHTML(String result, HttpServletResponse response){
		PrintWriter writer = null;
		try{
			response.setContentType("text/html;charset=utf-8");
			writer = response.getWriter();
			writer.write(result);
			writer.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static String buildResultJson(Boolean status, String msg){
		if(status == null){
			status = false;
		}
		if(msg == null){
			msg = "执行失败！";
		}
		return "{\"resultData\":{\"status\":" + status + ",\"msg\":\"" + msg + "\"}}";
	}
	
}
