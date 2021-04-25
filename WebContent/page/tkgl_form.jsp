<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="common/includes.jsp" %>
<%
    String xcid = request.getParameter("xcid");
    String ftype = request.getParameter("ftype");
    if (xcid == null) {
        xcid = "0";
    }
    if (ftype == null) {
        ftype = "0";
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
    <script type="text/javascript" src="<%=contextPath%>/bgui/webuploader/demo.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/bgui/webuploader/webuploader.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/bgui/webuploader/css/demo.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/bgui/webuploader/css/style.css">
    <!-- webuploader end -->

    <div class="content_wrapper">

        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-12">
                    <aside>
                        <header><i class="fa fa-fw fa-file"></i>上传相册图片
                            <button type="button" onclick="back();" class="btn btn-warning"> 返回列表</button>
                        </header>
                        <section>
                            <div class="container-fluid">
                                <div id="uploader" class="wu-example" style="width:1000px;height:600px;">
                                    <div class="queueList">
                                        <div id="dndArea" class="placeholder">
                                            <div id="filePicker"></div>
                                            <p>单次最多可选20张</p>
                                        </div>
                                    </div>
                                    <div class="statusBar" style="display:none;">
                                        <div class="progress">
                                            <span class="text">0%</span>
                                            <span class="percentage"></span>
                                        </div>
                                        <div class="info"></div>
                                        <div class="btns">
                                            <div id="filePicker2"></div>
                                            <div class="uploadBtn">开始上传</div>
                                        </div>
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
        var BASE_URL = '<%=contextPath%>';
        var BASE_SERVER = "/tkgl/uploadPic.do?xcid=<%=xcid %>&&ftype=<%=ftype %>";

        function uploadSuccess() {
            loadURL("/page/tkgl_list.jsp?xcid=<%=xcid %>&&ftype=<%=ftype %>");
        }

        function uploadFail() {
            loadURL("/page/xcgl_list.jsp");
        }

        function back() {
            loadURL("/page/xcgl_list.jsp");
        }

    </script>
    </body>
</html>
