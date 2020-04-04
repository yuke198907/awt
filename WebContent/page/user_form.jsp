<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.page import="com.simpo.tracker.web.user.entity.UserInfo"/>
<jsp:directive.page import="java.util.List"/>
<jsp:directive.page import="com.simpo.tracker.web.role.entity.RoleInfo"/>
<%@ include file="common/includes.jsp"%>
<%
UserInfo info = (UserInfo)request.getAttribute("info");
if(info == null){
	info = new UserInfo();
}
List<RoleInfo> roles = (List<RoleInfo>)request.getSession().getAttribute("ROLES");
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
                    <header><i class="fa fa-fw fa-file"></i>编辑用户</header>
                    <section>
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-sm-12">
                                    <form class="form-horizontal" role="form" id="form1" method="post" action="">
                                        <div class="title_bar">编辑用户<input type="hidden" value="<%=info.getId() %>" name="id"/></div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">登录名</label>
                                            <div class="col-sm-4">
                                                <input type="text" class="form-control" placeholder="登录名" id="loginname" name="loginname" value="<%=info.getLoginname() %>">
                                            </div>
                                            <p class="col-sm-6 help-block">必填</p>
                                        </div>
                                        <hr/>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">用户名</label>
                                            <div class="col-sm-4">
                                                <input type="text" class="form-control" placeholder="用户名" id="username" name="username" value="<%=info.getUsername() %>">
                                            </div>
                                            <p class="col-sm-6 help-block">必填</p>
                                        </div>
                                        <hr/>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">联系方式</label>
                                            <div class="col-sm-4">
                                                <input type="text" class="form-control" placeholder="联系方式" id="phone" name="phone" value="<%=info.getPhone() %>">
                                            </div>
                                            <p class="col-sm-6 help-block"></p>
                                        </div>
                                        <hr/>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">所属角色<input type="hidden" name="roleid" value="<%=info.getRoleid() %>" id="roleid"/></label>
                                            <div class="col-sm-4">
                                            	<%if(roles != null && roles.size() > 0){ %>
                                            	<%for(int i=0;i<roles.size();i++){ %>
                                            	<div class="radio">
                                                    <label>
                                                        <input type="radio" name="roles" value="<%=roles.get(i).getId() %>" <%if(roles.get(i).getId()==info.getRoleid()){ %>checked<%} %> ><%=roles.get(i).getRolename() %>
                                                    </label>
                                                </div>
                                            	<%} %>
                                            	<%} %>
                                            </div>
                                            <p class="col-sm-6 help-block"></p>
                                        </div>
                                        <hr/>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">描述</label>
                                            <div class="col-sm-4">
                                                <textarea class="form-control" name="remark"><%=info.getRemark() %></textarea>
                                            </div>
                                            <p class="col-sm-6 help-block">简单叙述一下角色的功能，200字以内</p>
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

function save(){
	var username = $("#username").val();
	var loginname = $("#loginname").val();
	if(username == "" || loginname == ""){
		alert("登录名或用户名为空，请检查！");
	}else{
		//var str="";     
	    
		$('input[name="roles"]:checked').each(function(){  
	    	//str+=$(this).val();
	    	$("#roleid").val($(this).val());
	    });
		
	    var formUrl="<%=contextPath%>/user/update.do";
		var str = $("#form1").serialize();
		$.ajax({
			type:"post", //请求方式
			url:formUrl, //发送请求地址
			data:str,
			//请求成功后的回调函数有两个参数
			success:function(data){
				if(data == 1){
					alert("操作成功！");
					loadURL("/page/user_list.jsp");
				}else if(data == -1){
					alert("登录名重复！");
				}else{
					alert("操作失败！");
				}
			}
		});
	    
	}
}

</script>
</body>
</html>