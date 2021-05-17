package com.simpo.tracker.web.annotation.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.simpo.tracker.common.IntegerTools;
import com.simpo.tracker.web.annotation.dao.Xtgl_Login_Dao;
import com.simpo.tracker.web.annotation.entity.Xtgl_Login_Info;
import com.simpo.tracker.web.annotation.service.Xtgl_Login_Service;

@Service
@Transactional  //此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class Xtgl_Login_ServiceImpl implements Xtgl_Login_Service {
    @Resource
    private Xtgl_Login_Dao loginDao;

    @Override
    public void save(Xtgl_Login_Info info) {
        // TODO Auto-generated method stub
        loginDao.save(info);
    }

    @Override
    public List<Xtgl_Login_Info> list(String pageNo, String pageSize, Xtgl_Login_Info info) {
        // TODO Auto-generated method stub
        PageHelper.startPage(IntegerTools.parseInt(pageNo),IntegerTools.parseInt(pageSize));
        return loginDao.list(info);
    }

    @Override
    public int count(Xtgl_Login_Info info) {
        // TODO Auto-generated method stub
        return loginDao.count(info);
    }

}
