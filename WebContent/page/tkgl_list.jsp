<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common/includes.jsp"%>
<%
String xcid = request.getParameter("xcid");
String ftype = request.getParameter("ftype");
if(xcid == null){
	xcid = "0";
}
if(ftype == null){
	ftype = "0";
}
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

<!--lrtk start-->
<script type="text/javascript" src="<%=contextPath%>/bgui/lrtk/js/lrtk.js"></script>
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/bgui/lrtk/css/lrtk.css"></link>
<!--lrtk end-->

<div class="content_wrapper">

    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-12">
                <aside>
                    <header><i class="fa fa-fw fa-file"></i><%if("1".equals(ftype)){ %>视频缩略图<%}else{ %>相册图片<%} %>&nbsp;<button type="button" onclick="back();" class="btn btn-warning">返回列表</button></header>
                    <section>
                        <div class="container-fluid" id="listDatas">
                            
                        </div>
                    </section>
                </aside>
            </div>
        </div>
    </div>

</div>

<script>
	
    $(document).ready(function () {
    	initDatas();
    });
    
    function initDatas(){
		$.post("<%=contextPath%>/tkgl/list.do",
		{xcid:"<%=xcid %>",filetype:"<%=ftype %>"},
		function(result){
			//清空现有列表，追加查询出来的列表
			$("#listDatas").html("");
			
			if(result.totalRows > 0){
				var datas = result.rows;
				
				if(datas.length > 0){
					
					for(var i=0;i<datas.length;i++){
						
						var divStr = '';
						divStr = divStr + '<li class="listbox mr20" id="ll'+datas[i].id+'">';
						divStr = divStr + '<div class="listimg"><img src="<%=contextPath%>/awt/<%=xcid %>/'+datas[i].tkpic+'" class="attachment-thumbnail wp-post-image" />';
						divStr = divStr + '</div>';
						divStr = divStr + '<div class="listinfo">';
						divStr = divStr + '<div class="listtitle">'+datas[i].tkseq+'</div>';
						<%if("1".equals(ftype)){ %>
						divStr = divStr + '<div class="listtag"><a href="javascript:editOne('+datas[i].id+');" rel="tag">视频设置</a><a href="javascript:deleteOne('+datas[i].id+');" rel="tag">删除</a></div>';
						<%}else{ %>
						divStr = divStr + '<div class="listtag"><a href="javascript:deleteOne('+datas[i].id+');" rel="tag">删除</a></div>';
						<%} %>
						divStr = divStr + '</div>';
						divStr = divStr + '</li>';
						
						var $div=$(divStr);
						
						$("#listDatas").append($div);
					}
					
				}else{
					//行数错误
					$("#listDatas").html("暂无数据！");
					alert("数据为空");
				}
				
			}else{
				$("#listDatas").html("暂无数据！");
				alert("数据为空");
			}
			
		},"json");
	}
    
    function deleteOne(id){
    	if(confirm("确认删除该信息吗？")){
    		var formUrl="<%=contextPath%>/tkgl/delete.do";
    		$.ajax({
    			type:"post", //请求方式
    			url:formUrl, //发送请求地址
    			data:{id:id},
    			//请求成功后的回调函数有两个参数
    			success:function(data){
    				if(data == 1){
    					alert("操作成功！");
    					$("#ll"+id).hide();
    				}else{
    					alert("操作失败！");
    				}
    			}
    		});
    	}
    }
    
    function editOne(id){
    	loadURL("/tkgl/find.do?id="+id);
    }
    
    function back(){
    	loadURL("/page/xcgl_list.jsp");
    }
    
</script>
</body>
</html>