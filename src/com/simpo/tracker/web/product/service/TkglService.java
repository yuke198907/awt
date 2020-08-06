package com.simpo.tracker.web.product.service;

import java.util.List;

import com.simpo.tracker.web.product.entity.TkglInfo;

public interface TkglService {
    //列表查询
    public List<TkglInfo> list(String pageNo, String pageSize, boolean isPage, TkglInfo info);

    //条数查询
    public int count(TkglInfo info);

    public TkglInfo findInfoById(long id);

    public int add(TkglInfo info);

    public int delete(long id);
}
