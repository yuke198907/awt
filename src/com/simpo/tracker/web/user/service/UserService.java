package com.simpo.tracker.web.user.service;

import java.util.List;

import com.simpo.tracker.web.user.entity.UserInfo;

public interface UserService {
    public UserInfo findInfoByLoginname(String loginname);

    //列表查询
    public List<UserInfo> list(String pageNo, String pageSize, UserInfo info);

    //条数查询
    public int count(UserInfo info);

    public int add(UserInfo info);

    public int update(UserInfo info);

    public int updateStatus(long id, String status);

    public int updatePwd(long id, String pwd);

    public UserInfo findInfoById(long id);
}
