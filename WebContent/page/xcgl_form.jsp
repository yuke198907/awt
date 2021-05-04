<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:directive.page import="com.simpo.tracker.web.product.entity.XcglInfo"/>
<%@ include file="common/includes.jsp" %>
<%
    XcglInfo info = (XcglInfo) request.getAttribute("info");
    if (info == null) {
        info = new XcglInfo();
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

    <link rel="stylesheet"
          href="<%=contextPath %>/bgui/public/js/vendor/bootstrap-datepicker/css/bootstrap-datepicker.standalone.min.css">
    <script src="<%=contextPath %>/bgui/public/js/vendor/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
    <script src="<%=contextPath %>/bgui/public/js/vendor/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js"></script>

    <!-- webuploader start -->
    <script type="text/javascript" src="<%=contextPath%>/bgui/webuploader/webuploader.nolog.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/bgui/webuploader/webuploader.css">
    <!-- webuploader end -->

    <div class="content_wrapper">

        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-12">
                    <aside>
                        <header><i class="fa fa-fw fa-file"></i>编辑相册信息</header>
                        <section>
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <form class="form-horizontal" role="form" id="form1" method="post" action="">
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">
                                                    相册图片
                                                    <input type="hidden" value="<%=info.getId() %>" name="id"/>
                                                    <input id="pic" type="hidden" value="<%=info.getPic() %>"
                                                           name="pic"/>
                                                </label>
                                                <div class="col-sm-4">
                                                    <%if (info.getPic() != null && !"".equals(info.getPic())) { %>
                                                    <img class="left" id="lpath"
                                                         src="<%=contextPath%>/awt/<%=info.getPic() %>" height="200px"
                                                         width="200px"></img>
                                                    <%} else { %>
                                                    <img class="left" id="lpath" src="<%=contextPath%>/awt/default.jpg"
                                                         height="200px" width="200px"></img>
                                                    <%} %>
                                                    <div id="uploader">
                                                        <!--用来存放文件信息-->
                                                        <div id="thelist"></div>
                                                        <div style="width:100px;">
                                                            <div id="picker">选择文件</div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <p class="col-sm-6 help-block" id="ttt"></p>
                                            </div>
                                            <hr/>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">相册名称</label>
                                                <div class="col-sm-4">
                                                    <input type="text" class="form-control" placeholder="相册名称" id="xcmc"
                                                           name="xcmc" value="<%=info.getXcmc() %>">
                                                </div>
                                                <p class="col-sm-6 help-block">必填</p>
                                            </div>
                                            <hr/>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">相册编号</label>
                                                <div class="col-sm-4">
                                                    <input type="text" class="form-control" placeholder="相册编号"
                                                           name="xcseq" value="<%=info.getXcseq() %>">
                                                </div>
                                                <p class="col-sm-6 help-block"></p>
                                            </div>
                                            <hr/>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">相册说明</label>
                                                <div class="col-sm-4">
                                                    <textarea class="form-control" style="height: 100px;" name="remark"
                                                              maxlength="500"
                                                              placeholder="相册说明"><%=info.getRemark() %></textarea>
                                                </div>
                                                <p class="col-sm-6 help-block"></p>
                                            </div>
                                            <hr/>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <button type="button" onclick="save();" class="btn btn-primary">提交
                                                    </button>
                                                    &nbsp;
                                                    <button type="button" onclick="back();" class="btn btn-warning">
                                                        返回列表
                                                    </button>
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

        /* $('#sbmc').datepicker({
            language:'zh-CN',
            format:'yyyy-mm-dd',
            zIndexOffset:906  //控制z-index
        }); */
        var logopath = "<%=info.getPic() %>";
        var uploader;
        $(document).ready(function () {
            uploader = WebUploader.create({
                // 选完文件后，是否自动上传。
                auto: true,
                // swf文件路径
                swf: "<%=contextPath%>/bgui/webuploader/Uploader.swf",
                // 文件接收服务端。
                server: '<%=contextPath%>/xcgl/uploadPic.do?xcid=<%=info.getId() %>&&pic=' + logopath,
                // 选择文件的按钮。可选。
                // 内部根据当前运行是创建，可能是input元素，也可能是flash.
                pick: '#picker',
                fileSingleSizeLimit: 10 * 1024 * 1024,   //设定单个文件大小
                // 只允许选择图片文件。
                accept: {
                    title: 'Images',
                    extensions: 'gif,jpg,jpeg,bmp,png',
                    mimeTypes: 'image/*'
                }
            });

            /**
             * 验证文件格式以及文件大小
             */
            uploader.on("error", function (type) {
                if (type == "Q_TYPE_DENIED") {
                    alert("请上传图片文件！");
                } else if (type == "F_EXCEED_SIZE") {
                    alert("文件大小不能超过10M！");
                }
            });

            uploader.on("uploadStart", function (file) {
                $("#ttt").html("上传中...");
            });

            uploader.on("uploadComplete", function (file) {
                // $("#ttt").html("上传成功");
            });

            uploader.on('uploadSuccess', function (file, data) {
                if (data.result == 1) {
                    //
                    $("img[id='lpath']").attr("src", "<%=contextPath%>/awt/" + data.message);

                    logopath = data.message;

                    $("#pic").val(data.message);

                    $("#ttt").html("上传成功！");
                } else {
                    $("#ttt").html("操作失败！" + data.message);
                }
            });

            uploader.on('uploadError', function (file) {
                $("#ttt").html("操作失败！");
            });
        })

        function save() {
            var xcmc = $("#xcmc").val();
            if (xcmc == "") {
                alert("名称为空，请检查！");
            } else {
                var formUrl = "<%=contextPath%>/xcgl/update.do";
                var str = $("#form1").serialize();
                $.ajax({
                    type: "post", //请求方式
                    url: formUrl, //发送请求地址
                    data: str,
                    //请求成功后的回调函数有两个参数
                    success: function (data) {
                        if (data == 1) {
                            alert("操作成功！");
                            loadURL("/page/xcgl_list.jsp");
                        } else {
                            alert("操作失败！");
                        }
                    }
                });
            }
        }

        function back() {
            loadURL("/page/xcgl_list.jsp");
        }

    </script>
    </body>
</html>
