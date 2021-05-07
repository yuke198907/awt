package com.simpo.tracker.web.annotation.entity;

import java.util.Date;

import com.simpo.tracker.common.DateTools;

/**
 * 系统操作日志
 *
 * @author AndaYu
 */
public class Xtgl_Operate_Info {
    private long id;
    private long czr;//操作人
    private Date czsj;//操作时间
    private String method;//方法名
    private String description;//方法描述

    private String username;//操作人

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCzr() {
        return czr;
    }

    public void setCzr(long czr) {
        this.czr = czr;
    }

    public String getCzsj() {
        if (czsj == null) {
            return "";
        }
        return DateTools.getDateString(czsj, "yyyy-MM-dd HH:mm:ss");
    }

    public void setCzsj(Date czsj) {
        this.czsj = czsj;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
