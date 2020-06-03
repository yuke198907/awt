package com.simpo.tracker.web.product.service;

import java.util.List;

import com.simpo.tracker.web.product.entity.XcglInfo;

public interface XcglService {
	//列表查询
	public List<XcglInfo> list(String pageNo, String pageSize, boolean isPage, XcglInfo info);
	//条数查询
	public int count(XcglInfo info);
	
	public XcglInfo findInfoById(long id);
	
	public int add(XcglInfo info);
	
	public int update(XcglInfo info);
	
	public int delete(long id);
	
	public int updatePic(long id, String pic);

}
