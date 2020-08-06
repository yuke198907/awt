package com.simpo.tracker.web.annotation.dao;

import java.util.List;

import com.simpo.tracker.web.annotation.entity.Xtgl_Login_Info;

public interface Xtgl_Login_Dao {
    //列表查询
    public List<Xtgl_Login_Info> list(Xtgl_Login_Info info);

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
