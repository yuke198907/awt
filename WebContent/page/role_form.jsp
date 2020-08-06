<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:directive.page import="com.simpo.tracker.web.role.entity.RoleInfo"/>
<%@ include file="common/includes.jsp" %>
<%
    RoleInfo info = (RoleInfo) request.getAttribute("info");
    if (info == null) {
        info = new RoleInfo();
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <title></title>
    <link rel="stylesheet" href="<%=contextPath %>/bgui/public/css/vendor/bootstrap/dist/bootstrap.css">
    <link rel="stylesheet" href="<%=contextPath %>/bgui/public/css/vendor/Animate.css">
    <link rel="stylesheet" href="<%=contextPath %>/bgui/public/css/basic.css">
    <link rel="stylesheet" href="<%=contextPath %>/bgui/public/css/vendor/font_awesome/css/font-awesome.css">
    <script src="<%=contextPath %>/bgui/public/js/vendor/jquery.min.js"></script>


    <div class="content_wrapper">

        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-12">
                    <aside>
                        <header><i class="fa fa-fw fa-file"></i>编辑角色</header>
                        <section>
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <form class="form-horizontal" role="form" id="form1" method="post" action="">
                                            <div class="title_bar">编辑角色<input type="hidden" value="<%=info.getId() %>"
                                                                              name="id"/></div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">角色编号</label>
                                                <div class="col-sm-4">
                                                    <input type="text" class="form-control" placeholder="角色编号"
                                                           id="roleno" name="roleno" value="<%=info.getRoleno() %>">
                                                </div>
                                                <p class="col-sm-6 help-block">必填</p>
                                            </div>
                                            <hr/>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">角色名称</label>
                                                <div class="col-sm-4">
                                                    <input type="text" class="form-control" placeholder="角色名称"
                                                           id="rolename" name="rolename"
                                                           value="<%=info.getRolename() %>">
                                                </div>
                                                <p class="col-sm-6 help-block">必填</p>
                                            </div>
                                            <hr/>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">角色描述</label>
                                                <div class="col-sm-4">
                                                    <textarea class="form-control"
                                                              name="remark"><%=info.getRemark() %></textarea>
                                                </div>
                                                <p class="col-sm-6 help-block">简单叙述一下角色的功能，200字以内</p>
                                            </div>
                                            <div class="title_bar">编辑该角色权限</div>
                                            <div class="form-group role_permission">
                                                <div class="col-sm-2 control-label"><input type="hidden"
                                                                                           value="<%=info.getPermission() %>"
                                                                                           name="permission"
                                                                                           id="permission"/></div>
                                                <div class="col-sm-4">
                                                    <div class="panel panel-default">
                                                        <div class="panel-heading">
                                                            <div class="checkbox"><label><input type="checkbox"
                                                                                                <%if(info.getPermission().contains("A")){ %>checked="checked"<%} %>
                                                                                                name="mid" value="A">信息管理</label>
                                                            </div>
                                                        </div>
                                                        <div class="panel-body">
                                                            <div class="checkbox"><label><input type="checkbox"
                                                                                                <%if(info.getPermission().contains("D")){ %>checked="checked"<%} %>
                                                                                                name="mid" value="D">设备管理</label>
                                                            </div>
                                                            <div class="checkbox"><label><input type="checkbox"
                                                                                                <%if(info.getPermission().contains("M")){ %>checked="checked"<%} %>
                                                                                                name="mid" value="M">数据检测管理</label>
                                                            </div>
                                                            <div class="checkbox"><label><input type="checkbox"
                                                                                                <%if(info.getPermission().contains("B")){ %>checked="checked"<%} %>
                                                                                                name="mid" value="B">电动机报警管理</label>
                                                            </div>
                                                            <div class="checkbox"><label><input type="checkbox"
                                                                                                <%if(info.getPermission().contains("C")){ %>checked="checked"<%} %>
                                                                                                name="mid" value="C">温度控制管理</label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="panel panel-default">
                                                        <div class="panel-heading">
                                                            <div class="checkbox"><label><input type="checkbox"
                                                                                                <%if(info.getPermission().contains("E")){ %>checked="checked"<%} %>
                                                                                                name="mid" value="E">系统管理</label>
                                                            </div>
                                                        </div>
                                                        <div class="panel-body">
                                                            <div class="checkbox"><label><input type="checkbox"
                                                                                                <%if(info.getPermission().contains("F")){ %>checked="checked"<%} %>
                                                                                                name="mid" value="F">系统用户管理</label>
                                                            </div>
                                                            <div class="checkbox"><label><input type="checkbox"
                                                                                                <%if(info.getPermission().contains("G")){ %>checked="checked"<%} %>
                                                                                                name="mid" value="G">系统角色管理</label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="panel panel-default">
                                                        <div class="panel-heading">
                                                            <div class="checkbox"><label><input type="checkbox"
                                                                                                <%if(info.getPermission().contains("K")){ %>checked="checked"<%} %>
                                                                                                name="mid" value="K">系统监控</label>
                                                            </div>
                                                        </div>
                                                        <div class="panel-body">
                                                            <div class="checkbox"><label><input type="checkbox"
                                                                                                <%if(info.getPermission().contains("L")){ %>checked="checked"<%} %>
                                                                                                name="mid" value="L">登录日志查询</label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <p class="col-sm-6 help-block"></p>
                                            </div>
                                    </div>
                                    <hr/>
                                    <div class="form-group">
                                        <div class="col-sm-12">
                                            <button type="button" onclick="save();" class="btn btn-primary">提交</button>
                                        </div>
                                    </div>
                                    </form>
                                </div>
                            </div>
                </div>
                </section>
                </aside>
            </div>
        </div>
    </div>
    </div>
    <script>

        function save() {
            var roleno = $("#roleno").val();
            var rolename = $("#rolename").val();
            if (roleno == "" || rolename == "") {
                alert("角色编号或名称为空，请检查！");
            } else {
                var str1 = "";

                $('input[name="mid"]:checked').each(function () {
                    str1 += $(this).val();
                });

                $("#permission").val(str1);

                var formUrl = "<%=contextPath%>/role/update.do";
                var str = $("#form1").serialize();
                $.ajax({
                    type: "post", //请求方式
                    url: formUrl, //发送请求地址
                    data: str,
                    //请求成功后的回调函数有两个参数
                    success: function (data) {
                        if (data == 1) {
                            alert("操作成功！");
                            loadURL("/page/role_list.jsp");
                        } else {
                            alert("操作失败！");
                        }
                    }
                });

            }
        }

    </script>
    </body>
</html>