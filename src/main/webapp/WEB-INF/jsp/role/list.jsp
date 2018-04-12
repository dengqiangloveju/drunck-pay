<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<LINK rel="Bookmark" href="${pageContext.request.contextPath}/favicon.ico" >
<LINK rel="Shortcut Icon" href="${pageContext.request.contextPath}/favicon.ico" />
<link href="${pageContext.request.contextPath}/ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/ui/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/ui/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/ui/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<title>角色列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 基础信息 <span class="c-gray en">&gt;</span> 角色管理 </nav>
<div class="pd-20">
	<form action="${pageContext.request.contextPath}/role/list" method="post">
		<div class="text-c"> 
			角色名称：
			<input type="text" class="input-text" value="${sysRole.name}" style="width:250px" placeholder="输入角色名称" id="name" name="name">
			<button type="submit" class="btn btn-success" id="query" name="query"><i class="Hui-iconfont">&#xe665;</i> 搜角色</button>
		</div>
	</form>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> 
		<span class="l">
		<a href="javascript:;" onclick="role_add('角色添加','${pageContext.request.contextPath}/role/toAdd','500','180')" class="btn btn-primary radius">
		<i class="Hui-iconfont">&#xe600;</i> 角色添加</a>
		</span>
		<span class="r">共有数据：<strong>${pager.totalnum}</strong> 条</span>
	</div>
	<table class="table table-border table-bordered table-bg">
		<thead>
			<tr>
				<th scope="col" colspan="9">角色列表</th>
			</tr>
			<tr class="text-c">
				<th>角色名称</th>
				<th>创建时间</th>
				<th>修改时间</th>
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pager.records}" var="role">
				<tr class="text-c">
					<td>${role.name}</td>
					<td><fmt:formatDate value="${role.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td><fmt:formatDate value="${role.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td class="td-manage">
						<a title="编辑" href="javascript:;" onclick="role_edit('角色修改','${pageContext.request.contextPath}/role/toEdit','${role.id}','500','180')" class="ml-5" style="text-decoration:none">
							<i class="Hui-iconfont">&#xe6df;</i>
						</a> 
						<a title="删除" href="javascript:;" onclick="role_del(this,'${role.id}')" class="ml-5" style="text-decoration:none">
							<i class="Hui-iconfont">&#xe6e2;</i>
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/lib/jquery/1.9.1/jquery.min.js"></script>  
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/lib/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/lib/laypage/1.2/laypage.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/H-ui.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/H-ui.admin.js"></script> 
<script type="text/javascript">
/*
	参数解释：
	title	标题
	url		请求的url
	id		需要操作的数据id
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/

/*管理员-增加*/
function role_add(title,url,w,h){
	layer_show(title,url,w,h);
}

/*管理员-删除*/
function role_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		$.ajax({ 
		    type:'post',   
		    url:'${pageContext.request.contextPath}/role/deleteRole', 
		    data:'id='+id,
		    dataType:'json', 
		    success:function(data){ 
		    	if(data.status) {
		    		layer.msg('已删除!',{icon:1,time:1000});
		    		$("#query").click();
		    	}
		    } 
		});		
	});
}

/*管理员-编辑*/
function role_edit(title,url,id,w,h){
	url = url + '?id='+id;
	layer_show(title,url,w,h);
}
</script>
</body>
</html>