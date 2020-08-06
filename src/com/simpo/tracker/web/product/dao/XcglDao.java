package com.simpo.tracker.web.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.simpo.tracker.web.product.entity.XcglInfo;

public interface XcglDao {

    public List<XcglInfo> list(XcglInfo info);

    public int count(XcglInfo info);

    public XcglInfo findInfoById(long id);

    public void add(XcglInfo info) throws Exception;

    public void delete(long id) throws Exception;

    public void update(XcglInfo info) throws Exception;

    public void updatePic(@Param("id") long id, @Param("pic") String pic) throws Exception;

}
