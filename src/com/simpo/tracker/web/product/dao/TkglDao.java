package com.simpo.tracker.web.product.dao;

import java.util.List;

import com.simpo.tracker.web.product.entity.TkglInfo;

public interface TkglDao {
	
	public List<TkglInfo> list(TkglInfo info);
	public int count(TkglInfo info);
	
	public TkglInfo findInfoById(long id);
	
	public void add(TkglInfo info) throws Exception;
	
	public void delete(long id) throws Exception;
}
