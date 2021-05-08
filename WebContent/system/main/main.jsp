<%@page import="javax.sound.midi.MidiDevice.Info" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:directive.page import="com.simpo.tracker.web.user.entity.UserInfo"/>
<%@ include file="../../page/common/includes.jsp" %>
<%
    UserInfo user = (UserInfo) request.getSession().getAttribute("USERINFO");
    if (user == null) {
        user = new UserInfo();
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <title>相册管理系统</title>
    <link rel="stylesheet" href="<%=contextPath %>/bgui/public/css/vendor/bootstrap/dist/bootstrap.css">
    <link rel="stylesheet" href="<%=contextPath %>/bgui/public/css/vendor/Animate.css">
    <link rel="stylesheet" href="<%=contextPath %>/bgui/public/css/basic.css">
    <link rel="stylesheet" href="<%=contextPath %>/bgui/public/css/vendor/font_awesome/css/font-awesome.css">
    <!--[if lt IE 9]>
    <script src="<%=contextPath %>/bgui/public/js/vendor/html5shiv.min.js"></script>
    <script src="<%=contextPath %>/bgui/public/js/vendor/respond.min.js"></script>
    <![endif]-->
    <script>
        window.common_conf = {
            defaultHash: '/page/xcgl_list.jsp',
            baseURL: '<%=contextPath %>',
            navJSON: '<%=contextPath %>/bgui/server/nav.json'
        };
    </script>
</head>

<body>
<header id="page_header">
    <div class="logow animated fadeInLeft"><a href="#">相册管理系统</a></div>
    <div class="right_side">
    </div>
</header>
<aside id="left_panel">
    <div class="login_info">
            <span>
                <div>
                    <a href="#" data-toggle="dropdown">
                        <i class="fa fa-user"></i><span class="name"><%=user.getUsername() %></span><span
                            class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a tabindex="-1" href="#" onclick="loadURL('/page/password_form.jsp')">修改密码</a></li>
                        <li><a tabindex="-1" href="<%=contextPath%>/system/logout.do">退出</a></li>
                    </ul>
                </div>
            </span>
    </div>
    <script id="nav_tpl" type="text/html">
        <%if (user.getPermission() != null && !"".equals(user.getPermission())) { %>
        <ul>
            <%if ("admin".equals(user.getLoginname()) || user.getPermission().contains("A")) { %>
            <li>
                <a href="#"><i class="fa fa-lg fa-fw fa-file-text"></i><span>相册管理<b><i class="fa fa-plus-square-o"></i></b></a>
                <ul>
                    <%if ("admin".equals(user.getLoginname()) || user.getPermission().contains("D")) { %>
                    <li><a href="#" onclick="loadURL('/page/xcgl_list.jsp')"><i
                            class="fa fa-fw fa-file"></i><span>相册管理</span></a></li>
                    <%} %>
                </ul>
            </li>
            <%} %>
        </ul>
        <%} %>
    </script>
    <nav></nav>
    <span class="minifyBtn"><i class="fa fa-arrow-circle-left"></i></span>
</aside>
<div id="main">
    <div id="ribbon">
        <ol class="breadcrumb"></ol>
    </div>
    <div id="content"></div>
</div>
<footer id="page_footer">
    <div class="inside"><i class="fa fa-copyright"></i><a href="#"></a></div>
</footer>

<!--Common Modal -->
<div class="modal fade" id="modal_ajax_content" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content"></div>
    </div>
</div>
<div class="modal fade" id="modal_confirm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-body">

            </div>
            <div class="modal-footer" style="text-align:center;">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-danger J_confirm_btn"><i class="fa fa-refresh fa-spin"></i> 确定
                </button>
            </div>
        </div>
    </div>
</div>

<script src="<%=contextPath %>/bgui/public/js/vendor/jquery.min.js"></script>
<script src="<%=contextPath %>/bgui/public/js/vendor/bootstrap/dist/bootstrap.js"></script>
<script src="<%=contextPath %>/bgui/public/js/vendor/catpl.js"></script>
<script src="<%=contextPath %>/bgui/public/js/vendor/ie10-viewport-bug-workaround.js"></script>
<script src="<%=contextPath %>/bgui/public/js/ajaxForm.js"></script>
<script src="<%=contextPath %>/bgui/public/js/basic.js"></script>
</body>
</html>
