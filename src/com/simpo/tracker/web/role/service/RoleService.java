package com.simpo.tracker.web.role.service;

import java.util.List;

import com.simpo.tracker.web.role.entity.RoleInfo;

public interface RoleService {

    public List<RoleInfo> list(boolean ispage, String pageNo, String pageSize, RoleInfo info);

    public int count(RoleInfo info);

    public RoleInfo findInfoById(long id);

    public int add(RoleInfo info);

    public int delete(long id);

    public int update(RoleInfo info);

}
