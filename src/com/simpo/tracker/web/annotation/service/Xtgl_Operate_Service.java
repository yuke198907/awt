package com.simpo.tracker.web.annotation.service;

import java.util.List;

import com.simpo.tracker.web.annotation.entity.Xtgl_Operate_Info;

/**
 * 操作日志Service
 *
 * @author AndaYu
 */
public interface Xtgl_Operate_Service {
    //列表查询
    public List<Xtgl_Operate_Info> list(String pageNo, String pageSize, Xtgl_Operate_Info info);

    //条数查询
    public int count(Xtgl_Operate_Info info);

    /**
     * 新增日志
     *
     * @param info
     * @return
     */
    public void save(Xtgl_Operate_Info info);

}
