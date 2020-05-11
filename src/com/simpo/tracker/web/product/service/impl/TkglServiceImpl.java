package com.simpo.tracker.web.product.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.simpo.tracker.common.IntegerTools;
import com.simpo.tracker.web.product.dao.TkglDao;
import com.simpo.tracker.web.product.entity.TkglInfo;
import com.simpo.tracker.web.product.service.TkglService;

@Service
@Transactional  //此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class TkglServiceImpl implements TkglService {

	@Resource
	private TkglDao tkglDao;
	
	@Override
	public List<TkglInfo> list(String pageNo, String pageSize, boolean isPage, TkglInfo info) {
		// TODO Auto-generated method stub
		if(isPage){
			PageHelper.startPage(IntegerTools.parseInt(pageNo),IntegerTools.parseInt(pageSize));
		}
		return tkglDao.list(info);
	}

	@Override
	public int count(TkglInfo info) {
		// TODO Auto-generated method stub
		return tkglDao.count(info);
	}

	@Override
	public TkglInfo findInfoById(long id) {
		// TODO Auto-generated method stub
		if(id > 0){
			return tkglDao.findInfoById(id);
		}
		return null;
	}

	@Override
	public int add(TkglInfo info) {
		// TODO Auto-generated method stub
		try {
			tkglDao.add(info);
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		try {
			tkglDao.delete(id);
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateVideo(TkglInfo info) {
		// TODO Auto-generated method stub
		try {
			tkglDao.updateVideo(info);
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
