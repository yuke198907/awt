package com.simpo.tracker.web.user.entity;

public class UserInfo {
    private long id = 0;
    private String loginname = "";
    private String password = "";
    private String username = "";//用户名称
    private String orgname = "";
    private String phone = "";
    private String status = "0";//用户状态:0-正常,1-离职或其他
    private String remark = "";
    private String statusname = "";

    private long roleid = 0;
    private String rolename = "";
    private String permission = "";

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatusname() {
        if ("0".equals(status)) {
            return "正常";
        } else if ("1".equals(status)) {
            return "已停用";
        }
        return statusname;
    }

    public void setStatusname(String statusname) {
        this.statusname = statusname;
    }

    public long getRoleid() {
        return roleid;
    }

    public void setRoleid(long roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getPermission() {
        if (loginname.equals("admin")) {
            return "ABCDEFGHIJ";
        }
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
