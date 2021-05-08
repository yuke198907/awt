<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="common/includes.jsp" %>
<%

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <title>相册管理</title>
    <link rel="stylesheet" href="<%=contextPath %>/bgui/public/js/vendor/DataTables/css/dataTables.bootstrap.css">
    <script src="<%=contextPath %>/bgui/public/js/vendor/jquery.min.js"></script>
    <script src="<%=contextPath %>/bgui/public/js/vendor/DataTables/js/jquery.dataTables.min.js"></script>
    <script src="<%=contextPath %>/bgui/public/js/vendor/DataTables/js/dataTables.bootstrap.min.js"></script>

    <div class="content_wrapper">

        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-12">
                    <aside>
                        <header><i class="fa fa-fw fa-file"></i>相册管理</header>
                        <section>
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="alert alert-success" role="alert">
                                            <form class="form-inline" role="form" method="get">
                                                <div class="form-group">
                                                    <label>相册名称</label>
                                                    <input type="text" class="form-control" placeholder="相册名称" id="xcmc"
                                                           name="xcmc"/>
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
                                                <th>相册名称</th>
                                                <th>相册编号</th>
                                                <th>创建时间</th>
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
                    url: "<%=contextPath %>/xcgl/list.do",
                    type: "get",
                    dataType: "json",
                    data: function (d) {
                        d.xcmc = $("#xcmc").val();
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
                    {"data": "xcmc", "bSortable": false},
                    {"data": "xcseq", "bSortable": false},
                    {"data": "cjsj", "bSortable": false},
                    {
                        "data": null, "bSortable": false,
                        "render": function (data, type, row) {
                            var id = '"' + data.id + '"';
                            var html = "<a href='javascript:;' class='btn btn-info btn-xs' onclick='editOne(" + id + ")'>编辑相册</a>";
                            html += "<a href='javascript:;' class='btn btn-warning btn-xs' onclick='deleteOne(" + id + ")'>删除相册</a>";
                            html += "<a href='javascript:;' class='btn btn-success btn-xs' onclick='uploadGallay(" + id + ",0)'>上传相册图片</a>";
                            html += "<a href='javascript:;' class='btn btn-danger btn-xs' onclick='deleteGallay(" + id + ",0)'>管理相册图片</a>";
                            return html;
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
                loadURL("/page/xcgl_form.jsp");
            });
        });

        function editOne(id) {
            loadURL("/xcgl/find.do?id=" + id);
        }

        function deleteOne(id) {
            if (confirm("确认删除该相册吗？")) {
                var formUrl = "<%=contextPath%>/xcgl/delete.do";
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
        }

        function uploadGallay(id, ftype) {
            loadURL("/page/tkgl_form.jsp?xcid=" + id + "&&ftype=" + ftype);
        }

        function deleteGallay(id, ftype) {
            loadURL("/page/tkgl_list.jsp?xcid=" + id + "&&ftype=" + ftype);
        }

    </script>
    </body>
</html>
