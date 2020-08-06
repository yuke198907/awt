package com.simpo.tracker.web.annotation.service;

import java.util.List;

import com.simpo.tracker.web.annotation.entity.Xtgl_Login_Info;

/**
 * 登录日志Service
 *
 * @author AndaYu
 */
public interface Xtgl_Login_Service {
    //列表查询
    public List<Xtgl_Login_Info> list(String pageNo, String pageSize, Xtgl_Login_Info info);

    //条数查询
    public int count(Xtgl_Login_Info info);

    /**
     * 新增日志
     *
     * @param info
     * @return
     */
    public void save(Xtgl_Login_Info info);

}
