﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common/includes.jsp"%>
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
                    <header><i class="fa fa-fw fa-file"></i>系统角色管理</header>
                    <section>
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="alert alert-success" role="alert">
                                        <form class="form-inline" role="form" method="get">
                                        <div class="form-group">
                                            <label>角色名称</label>
                                            <input type="text" class="form-control" placeholder="角色名称" id="rolename" name="rolename"/>
                                        </div>
                                        <span class="gap"></span>
                                        <button type="button" class="btn btn-primary" id="on-search">查询</button>
                                        
                                        <button type="button" class="btn btn-default" id="on-add">新增</button>
                                    	</form>
                                    </div>
									<table id="example" class="table table-striped table-bordered table-hover" cellspacing="0" width="100%">
										<thead>
											<tr>
												<th>角色名称</th>
												<th>角色编号</th>
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
            	url:"<%=contextPath %>/role/list.do",  
                type:"get",  
                dataType: "json",
                data:function (d) { 
                    d.rolename= $("#rolename").val(); 
                },  
                dataSrc:function(data){                  
                	if(data.recordsTotal==null){    
                    	data.recordsTotal=0;    
                    }
                    //查询结束取消按钮不可用     
                    return data.rows;//自定义数据源，默认为data    
            	}
            }, 
            searching: false,
            "columns": [
                        { "data": "rolename","bSortable": false},
                        { "data": "roleno","bSortable": false},
                        { "data": "remark","bSortable": false},
                        { "data":  null,"bSortable": false,  
                        	"render": function (data, type,row) {  
                                var id = '"' + data.id + '"';  
                                var html = "<a href='javascript:;' class='btn btn-info btn-xs' onclick='editOne("+ id + ")'>编辑</a>";
                                html += "<a href='javascript:;' class='btn btn-warning btn-xs' onclick='deleteOne("+ id + ")'>删除</a>";
                                return html;  
                            } 
                 		}
                    ],
            "language": {
                url:'<%=contextPath %>/bgui/public/js/vendor/DataTables/Chinese.json'
            }
        });
        
        $("#on-search").click(function () {
            tbl.ajax.reload();
        });
        
        $("#on-add").click(function () {
            loadURL("/page/role_form.jsp");
        });
    });
    
    function editOne(id){
    	loadURL("/role/find.do?id="+id);
    }
    
    function deleteOne(id){
    	var formUrl="<%=contextPath%>/role/delete.do";
		$.ajax({
			type:"post", //请求方式
			url:formUrl, //发送请求地址
			data:{id:id},
			//请求成功后的回调函数有两个参数
			success:function(data){
				if(data == 1){
					alert("操作成功！");
					tbl.ajax.reload();
				}else{
					alert("操作失败！");
				}
			}
		});
    }
    
</script>
</body>
</html>