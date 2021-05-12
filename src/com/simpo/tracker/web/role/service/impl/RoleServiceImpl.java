package com.simpo.tracker.web.role.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.simpo.tracker.common.IntegerTools;
import com.simpo.tracker.web.role.dao.RoleDao;
import com.simpo.tracker.web.role.entity.RoleInfo;
import com.simpo.tracker.web.role.service.RoleService;

@Service
@Transactional  //此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;

    @Override
    public List<RoleInfo> list(boolean ispage, String pageNo, String pageSize, RoleInfo info) {
        // TODO Auto-generated method stub
        if (ispage) {
            //分页
            PageHelper.startPage(IntegerTools.parseInt(pageNo), IntegerTools.parseInt(pageSize));
        }
        return roleDao.list(info);
    }

    @Override
    public int count(RoleInfo info) {
        // TODO Auto-generated method stub
        return roleDao.count(info);
    }

    @Override
    public int add(RoleInfo info) {
        // TODO Auto-generated method stub
        try {
            roleDao.add(info);
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
            roleDao.delete(id);
            return 1;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(RoleInfo info) {
        // TODO Auto-generated method stub
        try {
            roleDao.update(info);
            return 1;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public RoleInfo findInfoById(long id) {
        // TODO Auto-generated method stub
        if (id > 0) {
            return roleDao.findInfoById(id);
        }
        return null;
    }

}
