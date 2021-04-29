<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../page/common/includes.jsp" %>
<%
    String result = request.getParameter("result");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <title>相册管理信息系统</title>
    <link rel="stylesheet" href="<%=contextPath %>/system/login/css/reset.css"/>
    <link rel="stylesheet" href="<%=contextPath %>/system/login/css/login.css"/>
    <script type="text/javascript" src="<%=contextPath %>/system/login/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=contextPath %>/system/login/js/login.js"></script>
</head>
<body>
<div class="page">
    <div class="loginwarrp">
        <div class="logo">PHOTO管理系统</div>
        <div class="login_form">
            <form method="post" action="<%=contextPath%>/system/login.do">
                <li class="login-item">
                    <span>用户名：</span>
                    <input type="text" id="username" name="loginname" class="login_input">
                    <span id="count-msg" class="error"></span>
                </li>
                <li class="login-item">
                    <span>密　码：</span>
                    <input type="password" id="password" name="pwd" class="login_input">
                    <span id="password-msg" class="error"></span>
                </li>

                <li class="login-sub">
                    <input type="submit" value="登录"/>
                    <input type="reset" value="重置"/>
                </li>
            </form>
        </div>
        <br/>
        <div class="footer"></div>
    </div>
</div>
<script type="text/javascript">
    window.onload = function () {
        var config = {
            vx: 4,
            vy: 4,
            height: 2,
            width: 2,
            count: 100,
            color: "121, 162, 185",
            stroke: "100, 200, 180",
            dist: 6000,
            e_dist: 20000,
            max_conn: 10
        }
        CanvasParticle(config);
    }
</script>
<script type="text/javascript" src="<%=contextPath %>/system/login/js/canvas-particle.js"></script>
</body>
</html>
