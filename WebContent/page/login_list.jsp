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
                        <header><i class="fa fa-fw fa-file"></i>登录日志查询</header>
                        <section>
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="alert alert-success" role="alert">
                                            <form class="form-inline" role="form" method="get">
                                                <div class="form-group">
                                                    <label>登录人</label>
                                                    <input type="text" class="form-control" placeholder="登录人"
                                                           id="username" name="username"/>
                                                </div>
                                                <span class="gap"></span>
                                                <button type="button" class="btn btn-primary" id="on-search">查询</button>

                                            </form>
                                        </div>
                                        <table id="example" class="table table-striped table-bordered table-hover"
                                               cellspacing="0" width="100%">
                                            <thead>
                                            <tr>
                                                <th>登录名</th>
                                                <th>登录人</th>
                                                <th>登录时间</th>
                                                <th>IP地址</th>
                                                <th>是否成功</th>
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
                    url: "<%=contextPath %>/login/list.do",
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
                    {"data": "logintime", "bSortable": false},
                    {"data": "ipaddress", "bSortable": false},
                    {
                        "data": null, "bSortable": false,
                        "render": function (data, type, row) {
                            if (data.issuccess == "1") {
                                return "成功";
                            } else {
                                return "失败";
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

        });

    </script>
    </body>
</html>