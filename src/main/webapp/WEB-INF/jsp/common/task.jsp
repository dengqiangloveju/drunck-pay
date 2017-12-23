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
<title>管理员列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 管理员管理 <span class="c-gray en">&gt;</span> 管理员列表 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20">
	<form action="${pageContext.request.contextPath}/job/list" method="post">
		<div class="text-c"> 
			任务组名称：
			<input type="text" class="input-text" value="${scheduleJob.jobGroup}" style="width:250px" placeholder="输入任务组名称" id="jobGroup" name="jobGroup">
			&emsp;任务名称：
			<input type="text" class="input-text" value="${scheduleJob.jobName}" style="width:250px" placeholder="输入任务名称" id="jobName" name="jobName">
			<button type="submit" class="btn btn-success" id="query" name="query"><i class="Hui-iconfont">&#xe665;</i> 搜用户</button>
		</div>
	</form>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> 
		<span class="l">
		<a href="javascript:;" onclick="admin_add('任务添加','${pageContext.request.contextPath}/job/toAdd','800','500')" class="btn btn-primary radius">
		<i class="Hui-iconfont">&#xe600;</i> 任务添加</a>
		</span>
		<span class="r">共有数据：<strong>${pager.totalnum}</strong> 条</span>
	</div>
	<table class="table table-border table-bordered table-bg">
		<thead>
			<tr>
				<th scope="col" colspan="9">员工列表</th>
			</tr>
			<tr class="text-c">
				<th>任务组名称</th>
				<th>任务名称</th>
				<th>触发器名称</th>
				<th>规则</th>
				<th>类名称</th>
				<th>方法名</th>
				<th>状态</th>
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pager.records}" var="job">
				<tr class="text-c">
					<td>${job.jobName}</td>
					<td>${job.jobGroup}</td>
					<td>${job.jobTrigger}</td>
					<td>${job.cronExpression}</td>
					<td>${job.serviceName}</td>
					<td>${job.serviceMethod}</td>
					<td class="td-status">
						<c:choose>
							<c:when test="${job.status eq '0'}">
								<span class="label label-success radius">已启用</span>
							</c:when>
							<c:otherwise>
								<span class="label radius">已停用</span>
							</c:otherwise>
						</c:choose>
					</td>
					<td class="td-manage">
						<c:choose>
							<c:when test="${job.status eq '0'}">
								<a style="text-decoration:none" onClick="admin_stop(this,'${job.id}')" href="javascript:void(0)" title="停用">
									<i class="Hui-iconfont">&#xe631;</i>
								</a> 
							</c:when>
							<c:otherwise>
								<a style="text-decoration:none" onClick="admin_start(this,'${job.id}')" href="javascript:void(0)" title="停用">
									<i class="Hui-iconfont">&#xe631;</i>
								</a> 
							</c:otherwise>
						</c:choose>
						<a title="编辑" href="javascript:;" onclick="admin_edit('管理员编辑','admin-add.html','1','800','500')" class="ml-5" style="text-decoration:none">
							<i class="Hui-iconfont">&#xe6df;</i>
						</a> 
						<a title="删除" href="javascript:;" onclick="admin_del(this,'1')" class="ml-5" style="text-decoration:none">
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
function admin_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*管理员-删除*/
function admin_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		
		$(obj).parents("tr").remove();
		layer.msg('已删除!',{icon:1,time:1000});
	});
}
/*管理员-编辑*/
function admin_edit(title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*管理员-停用*/
function admin_stop(obj,id){
	layer.confirm('确认要停用吗？',function(index){
		$.ajax({ 
		    type:'post',   
		    url:'${pageContext.request.contextPath}/job/pauseJob', 
		    data:'id='+id,
		    dataType:'json', 
		    success:function(data){ 
		    	if(data.status) {
		    		$(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_start(this,\''+id+'\')" href="javascript:void(0)" title="启用" style="text-decoration:none"><i class="Hui-iconfont">&#xe615;</i></a>');
		    		$(obj).parents("tr").find(".td-status").html('<span class="label radius">已停用</span>');
		    		$(obj).remove();
		    		layer.msg('已停用!',{icon: 5,time:1000});
		    	}
		    } 
		});
	});
}

/*管理员-启用*/
function admin_start(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		$.ajax({ 
		    type:'post',   
		    url:'${pageContext.request.contextPath}/job/resumeJob', 
		    data:'id='+id,
		    dataType:'json', 
		    success:function(data){ 
		    	if(data.status) {
		    		$(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_stop(this,\''+id+'\')" href="javascript:void(0)" title="停用" style="text-decoration:none"><i class="Hui-iconfont">&#xe631;</i></a>');
		    		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
		    		$(obj).remove();
		    		layer.msg('已启用!', {icon: 6,time:1000});
		    	}
		    } 
		});
	});
}
</script>
</body>
</html>