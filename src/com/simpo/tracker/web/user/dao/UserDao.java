package com.simpo.tracker.web.user.dao;

import java.util.List;

import com.simpo.tracker.web.user.entity.UserInfo;

public interface UserDao {
    public UserInfo findInfoByLoginname(String loginname);

    //列表查询
    public List<UserInfo> list(UserInfo info);

    //条数查询
    public int count(UserInfo info);

    //新增
    public void add(UserInfo info) throws Exception;

    public void update(UserInfo info) throws Exception;

    public void updateStatus(long id, String status) throws Exception;

    public void updatePwd(long id, String pwd) throws Exception;

    public UserInfo findInfoById(long id);
}
