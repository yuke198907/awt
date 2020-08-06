package com.simpo.tracker.web.role.dao;

import java.util.List;

import com.simpo.tracker.web.role.entity.RoleInfo;

public interface RoleDao {

    public List<RoleInfo> list(RoleInfo info);

    public int count(RoleInfo info);

    public RoleInfo findInfoById(long id);

    public void add(RoleInfo info) throws Exception;

    public void delete(long id) throws Exception;

    public void update(RoleInfo info) throws Exception;

}
