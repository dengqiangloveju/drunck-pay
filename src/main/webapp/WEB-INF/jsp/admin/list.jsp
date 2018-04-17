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
<title>用户列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 基础信息 <span class="c-gray en">&gt;</span> 用户管理  </nav>
<div class="pd-20">
	<form action="${pageContext.request.contextPath}/user/list" id="queryForm" method="post">
		<div class="text-c"> 
			用户名称：
			<input type="text" class="input-text" value="${sysAdmin.userName}" style="width:250px" placeholder="输入用户名" id="userName" name="userName">
			<button type="submit" class="btn btn-success" id="query" name="query"><i class="Hui-iconfont">&#xe665;</i> 搜用户</button>
		</div>
	</form>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> 
		<span class="l">
		<a href="javascript:;" onclick="user_add('用户添加','${pageContext.request.contextPath}/user/toAdd','600','280')" class="btn btn-primary radius">
		<i class="Hui-iconfont">&#xe600;</i> 用户添加</a>
		</span>
		<span class="r">共有数据：<strong>${pager.totalnum}</strong> 条</span>
	</div>
	<table class="table table-border table-bordered table-bg">
		<thead>
			<tr>
				<th scope="col" colspan="9">用户列表</th>
			</tr>
			<tr class="text-c">
				<th>用户名称</th>
				<th>创建时间</th>
				<th>修改时间</th>
				<th>状态</th>
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pager.records}" var="user">
				<tr class="text-c">
					<td>${user.userName}</td>
					<td><fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td><fmt:formatDate value="${user.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td class="td-status">
						<c:choose>
							<c:when test="${user.enable}">
								<span class="label label-success radius">已启用</span>
							</c:when>
							<c:otherwise>
								<span class="label radius">已停用</span>
							</c:otherwise>
						</c:choose>
					</td>
					<td class="td-manage">
						<a title="编辑" href="javascript:;" onclick="user_edit('用户修改','${pageContext.request.contextPath}/user/toEdit','${user.id}','600','280')" class="ml-5" style="text-decoration:none">
							<i class="Hui-iconfont">&#xe6df;</i>
						</a> 
						<a title="删除" href="javascript:;" onclick="user_del(this,'${user.id}')" class="ml-5" style="text-decoration:none">
							<i class="Hui-iconfont">&#xe6e2;</i>
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="dataTables_info" id="DataTables_Table_0_info" role="status" aria-live="polite">弟 ${pager.pageNo} 页 ，共 ${pager.totalPage} 页</div>
	<div class="dataTables_paginate paging_simple_numbers" id="DataTables_Table_0_paginate">
	<a class="paginate_button previous disabled" href="javascript:queryPage(${pager.prePage})" aria-controls="DataTables_Table_0" data-dt-idx="0" tabindex="0" id="DataTables_Table_0_previous">上一页</a>
	<c:forEach begin="${pager.startPage}" end="${pager.endPage}" var="num">
		<c:choose>
			<c:when test="${num eq pager.pageNo}"><span><a class="paginate_button current" href="javascript:queryPage(${num})" aria-controls="DataTables_Table_0" data-dt-idx="1" tabindex="0">${num}</a></span></c:when>
			<c:otherwise><span><a class="paginate_button" href="javascript:queryPage(${num})" aria-controls="DataTables_Table_0" data-dt-idx="1" tabindex="0">${num}</a></span></c:otherwise>
		</c:choose>
	</c:forEach>
	<a class="paginate_button next disabled" href="javascript:queryPage(${pager.nextPage})" aria-controls="DataTables_Table_0" data-dt-idx="2" tabindex="0" id="DataTables_Table_0_next">下一页</a>
	</div>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/lib/jquery/1.9.1/jquery.min.js"></script>  
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/lib/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/lib/laypage/1.2/laypage.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/H-ui.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/H-ui.admin.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/common.js"></script> 
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
function user_add(title,url,w,h){
	layer_show(title,url,w,h);
}

/*管理员-删除*/
function user_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		$.ajax({ 
		    type:'post',   
		    url:'${pageContext.request.contextPath}/user/deleteUser', 
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
function user_edit(title,url,id,w,h){
	url = url + '?id='+id;
	layer_show(title,url,w,h);
}
</script>
</body>
</html>