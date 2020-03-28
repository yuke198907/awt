package com.simpo.tracker.web.annotation.entity;

import java.util.Date;

import com.simpo.tracker.common.DateTools;

/**
 * 系统登录日志
 * 
 * @author Administrator
 *
 */
public class Xtgl_Login_Info {
	private long id;
	private String loginname;
	private Date logintime;
	private String ipaddress;
	private long userid;
	private String issuccess;//0-失败,1-成功
	
	private String username;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getLogintime() {
		if(logintime == null){
			return "";
		}
		return DateTools.getDateString(logintime, "yyyy-MM-dd HH:mm:ss");
	}
	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getIssuccess() {
		return issuccess;
	}
	public void setIssuccess(String issuccess) {
		this.issuccess = issuccess;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
