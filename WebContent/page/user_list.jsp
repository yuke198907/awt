<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="common/includes.jsp" %>
<%

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <title></title>
    <link rel="stylesheet" href="<%=contextPath %>/bgui/public/js/vendor/DataTables/css/dataTables.bootstrap.css">
    <script src="<%=contextPath %>/bgui/public/js/vendor/jquery.min.js"></script>
    <script src="<%=contextPath %>/bgui/public/js/vendor/DataTables/js/jquery.dataTables.min.js"></script>
    <script src="<%=contextPath %>/bgui/public/js/vendor/DataTables/js/dataTables.bootstrap.min.js"></script>

    <div class="content_wrapper">

        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-12">
                    <aside>
                        <header><i class="fa fa-fw fa-file"></i>系统用户管理</header>
                        <section>
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="alert alert-success" role="alert">
                                            <form class="form-inline" role="form" method="get">
                                                <div class="form-group">
                                                    <label>用户名</label>
                                                    <input type="text" class="form-control" placeholder="用户名"
                                                           id="username" name="username"/>
                                                </div>
                                                <span class="gap"></span>
                                                <button type="button" class="btn btn-primary" id="on-search">查询</button>

                                                <button type="button" class="btn btn-default" id="on-add">新增</button>
                                            </form>
                                        </div>
                                        <table id="example" class="table table-striped table-bordered table-hover"
                                               cellspacing="0" width="100%">
                                            <thead>
                                            <tr>
                                                <th>登录名</th>
                                                <th>用户名</th>
                                                <th>联系方式</th>
                                                <th>状态</th>
                                                <th>所属角色</th>
                                                <th>说明</th>
                                                <th>操作</th>
                                            </tr>
                                            </thead>
                                        </table>
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
        var tbl;
        $(document).ready(function () {
            tbl = $('#example').DataTable({
                "ajax": {
                    url: "<%=contextPath %>/user/list.do",
                    type: "get",
                    dataType: "json",
                    data: function (d) {
                        d.username = $("#username").val();
                    },
                    dataSrc: function (data) {
                        if (data.recordsTotal == null) {
                            data.recordsTotal = 0;
                        }
                        //查询结束取消按钮不可用
                        return data.rows;//自定义数据源，默认为data
                    }
                },
                searching: false,
                "columns": [
                    {"data": "loginname", "bSortable": false},
                    {"data": "username", "bSortable": false},
                    {"data": "phone", "bSortable": false},
                    {"data": "statusname", "bSortable": false},
                    {"data": "rolename", "bSortable": false},
                    {"data": "remark", "bSortable": false},
                    {
                        "data": null, "bSortable": false,
                        "render": function (data, type, row) {
                            if (data.loginname == "admin") {
                                return "";
                            }
                            if (data.status == "0") {
                                var id = '"' + data.id + '"';
                                var html = "<a href='javascript:;' class='btn btn-warning btn-xs' onclick='updateStatus(" + id + ")'>停用</a>";
                                html += "<a href='javascript:;' class='btn btn-danger btn-xs' onclick='remarkOne(" + id + ")'>重置密码</a>";
                                return html;
                            } else {
                                var id = '"' + data.id + '"';
                                var html = "<a href='javascript:;' class='btn btn-info btn-xs' onclick='editOne(" + id + ")'>编辑</a>";
                                html += "<a href='javascript:;' class='btn btn-success btn-xs' onclick='updateStatus(" + id + ")'>启用</a>";
                                html += "<a href='javascript:;' class='btn btn-danger btn-xs' onclick='remarkOne(" + id + ")'>重置密码</a>";
                                return html;
                            }
                        }
                    }
                ],
                "language": {
                    url: '<%=contextPath %>/bgui/public/js/vendor/DataTables/Chinese.json'
                }
            });

            $("#on-search").click(function () {
                tbl.ajax.reload();
            });

            $("#on-add").click(function () {
                loadURL("/page/user_form.jsp");
            });
        });

        function editOne(id) {
            loadURL("/user/find.do?id=" + id);
        }

        function updateStatus(id) {
            var formUrl = "<%=contextPath%>/user/updateStatus.do";
            $.ajax({
                type: "post", //请求方式
                url: formUrl, //发送请求地址
                data: {id: id},
                //请求成功后的回调函数有两个参数
                success: function (data) {
                    if (data == 1) {
                        alert("操作成功！");
                        tbl.ajax.reload();
                    } else {
                        alert("操作失败！");
                    }
                }
            });
        }

        function remarkOne(id) {
            var formUrl = "<%=contextPath%>/user/remark.do";
            $.ajax({
                type: "post", //请求方式
                url: formUrl, //发送请求地址
                data: {id: id},
                //请求成功后的回调函数有两个参数
                success: function (data) {
                    if (data == 1) {
                        alert("操作成功！");
                        tbl.ajax.reload();
                    } else {
                        alert("操作失败！");
                    }
                }
            });
        }

    </script>
    </body>
</html>