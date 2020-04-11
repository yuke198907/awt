<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common/includes.jsp"%>
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
                    <header><i class="fa fa-fw fa-file"></i>重置密码</header>
                    <section>
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-sm-12">
                                    <form class="form-horizontal" role="form" id="form1" method="post" action="">
                                        <div class="title_bar">重置密码</div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">新密码</label>
                                            <div class="col-sm-4">
                                                <input type="text" class="form-control" placeholder="新密码" id="pwd" value="">
                                            </div>
                                            <p class="col-sm-6 help-block">必填</p>
                                        </div>
                                        <hr/>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">确认密码</label>
                                            <div class="col-sm-4">
                                                <input type="text" class="form-control" placeholder="确认密码" id="cpwd" value="">
                                            </div>
                                            <p class="col-sm-6 help-block">必填</p>
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
	var pwd = $("#pwd").val();
	var cpwd = $("#cpwd").val();
	if(pwd == "" || cpwd == ""){
		alert("角色编号或名称为空，请检查！");
	}else if(pwd == cpwd){
		var formUrl="<%=contextPath%>/system/remark.do";
		$.ajax({
			type:"post", //请求方式
			url:formUrl, //发送请求地址
			data:{"npw":pwd},
			//请求成功后的回调函数有两个参数
			success:function(data){
				if(data == 1){
					alert("操作成功！");
					window.location.href="<%=contextPath%>/system/logout.do";
				}else{
					alert("操作失败！");
				}
			}
		});
	}else{
	    alert("两次密码不一样！");
	}
}

</script>
</body>
</html>