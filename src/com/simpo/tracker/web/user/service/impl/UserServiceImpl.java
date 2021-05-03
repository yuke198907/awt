package com.simpo.tracker.web.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.simpo.tracker.common.IntegerTools;
import com.simpo.tracker.web.user.dao.UserDao;
import com.simpo.tracker.web.user.entity.UserInfo;
import com.simpo.tracker.web.user.service.UserService;

@Service
@Transactional  //此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public UserInfo findInfoByLoginname(String loginname) {
        // TODO Auto-generated method stub
        if (loginname != null && !"".equals(loginname)) {
            return userDao.findInfoByLoginname(loginname);
        }
        return null;
    }

    @Override
    public List<UserInfo> list(String pageNo, String pageSize, UserInfo info) {
        // TODO Auto-generated method stub
        PageHelper.startPage(IntegerTools.parseInt(pageNo),IntegerTools.parseInt(pageSize));
        return userDao.list(info);
    }

    @Override
    public int count(UserInfo info) {
        // TODO Auto-generated method stub
        return userDao.count(info);
    }

    @Override
    public int add(UserInfo info) {
        // TODO Auto-generated method stub
        try {

            UserInfo user = userDao.findInfoByLoginname(info.getLoginname());
            if (user == null) {
                userDao.add(info);
                return 1;
            } else {
                //登录名已存在
                return -1;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(UserInfo info) {
        // TODO Auto-generated method stub
        try {
            UserInfo user = userDao.findInfoByLoginname(info.getLoginname());
            if (user == null) {
                userDao.update(info);
                return 1;
            } else {
                if (user.getId() == info.getId()) {
                    userDao.update(info);
                    return 1;
                } else {
                    return -1;
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateStatus(long id, String status) {
        // TODO Auto-generated method stub
        try {
            userDao.updateStatus(id, status);
            return 1;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public UserInfo findInfoById(long id) {
        // TODO Auto-generated method stub
        if (id > 0) {
            return userDao.findInfoById(id);
        }
        return null;
    }

    @Override
    public int updatePwd(long id, String pwd) {
        // TODO Auto-generated method stub
        try {
            userDao.updatePwd(id, pwd);
            return 1;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

}
