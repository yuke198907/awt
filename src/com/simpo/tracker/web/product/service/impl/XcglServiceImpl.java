package com.simpo.tracker.web.product.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.simpo.tracker.common.IntegerTools;
import com.simpo.tracker.web.product.dao.XcglDao;
import com.simpo.tracker.web.product.entity.XcglInfo;
import com.simpo.tracker.web.product.service.XcglService;

@Service
@Transactional  //此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class XcglServiceImpl implements XcglService {

	@Resource
	private XcglDao xcglDao;
	
	@Override
	public List<XcglInfo> list(String pageNo, String pageSize, boolean isPage, XcglInfo info) {
		// TODO Auto-generated method stub
		if(isPage){
			PageHelper.startPage(IntegerTools.parseInt(pageNo),IntegerTools.parseInt(pageSize));
		}
		return xcglDao.list(info);
	}

	@Override
	public int count(XcglInfo info) {
		// TODO Auto-generated method stub
		return xcglDao.count(info);
	}

	@Override
	public XcglInfo findInfoById(long id) {
		// TODO Auto-generated method stub
		if(id > 0){
			return xcglDao.findInfoById(id);
		}
		return null;
	}

	@Override
	public int add(XcglInfo info) {
		// TODO Auto-generated method stub
		try {
			xcglDao.add(info);
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(XcglInfo info) {
		// TODO Auto-generated method stub
		try {
			xcglDao.update(info);
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
			xcglDao.delete(id);
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updatePic(long id, String pic) {
		// TODO Auto-generated method stub
		if(id > 0 && pic != null && !"".equals(pic)){
			try {
				xcglDao.updatePic(id, pic);
				return 1;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}

}
