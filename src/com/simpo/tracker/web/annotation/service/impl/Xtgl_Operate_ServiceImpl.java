package com.simpo.tracker.web.annotation.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.simpo.tracker.common.IntegerTools;
import com.simpo.tracker.web.annotation.dao.Xtgl_Operate_Dao;
import com.simpo.tracker.web.annotation.entity.Xtgl_Operate_Info;
import com.simpo.tracker.web.annotation.service.Xtgl_Operate_Service;

@Service
@Transactional  //此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class Xtgl_Operate_ServiceImpl extends SqlSessionDaoSupport implements Xtgl_Operate_Service {
	@Resource
	private Xtgl_Operate_Dao operateDao;
	private SqlSessionFactory sqlSessionFactory;
	@Resource
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
		super.setSqlSessionFactory(sqlSessionFactory);
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	@Override
	public void save(Xtgl_Operate_Info info) {
		// TODO Auto-generated method stub
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.SIMPLE);
		try {
			sqlSession.insert("com.simpo.tracker.web.annotation.dao.Xtgl_Operate_Dao.save", info);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			sqlSession.close(); 
		}
	}

	@Override
	public List<Xtgl_Operate_Info> list(String pageNo, String pageSize,
			Xtgl_Operate_Info info) {
		// TODO Auto-generated method stub
		//PageHelper.startPage(IntegerTools.parseInt(pageNo),IntegerTools.parseInt(pageSize));
		return operateDao.list(info);
	}

	@Override
	public int count(Xtgl_Operate_Info info) {
		// TODO Auto-generated method stub
		return operateDao.count(info);
	}

}
