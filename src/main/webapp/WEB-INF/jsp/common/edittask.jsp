<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link href="${pageContext.request.contextPath}/ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/ui/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/ui/lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/ui/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<title>任务修改</title>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="form-admin-add">
		<input type="hidden" name="id" value="${scheduleJob.id}">
		<div class="row cl" style="margin-top: 5px">
			<label class="form-label col-3"><span class="c-red">*</span>任务名称：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" value="${scheduleJob.jobName}" id="jobName" name="jobName" datatype="*2-30" nullmsg="任务名称不能为空">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl" style="margin-top: 5px">
			<label class="form-label col-3"><span class="c-red">*</span>任务组名称：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" value="${scheduleJob.jobGroup}" id="jobGroup" name="jobGroup" datatype="*2-30" nullmsg="任务组名称不能为空">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl" style="margin-top: 5px">
			<label class="form-label col-3"><span class="c-red">*</span>触发器名称：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" value="${scheduleJob.jobTrigger}" id="jobTrigger" name="jobTrigger" datatype="*2-30" nullmsg="触发器名称不能为空">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl" style="margin-top: 5px">
			<label class="form-label col-3"><span class="c-red">*</span>触发规则：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" value="${scheduleJob.cronExpression}" id="cronExpression" name="pattern" datatype="*2-16" nullmsg="触发规则不能为空">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl" style="margin-top: 5px">
			<label class="form-label col-3"><span class="c-red">*</span>类名：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" value="${scheduleJob.serviceName}" id="serviceName" name="serviceName" datatype="*2-300" nullmsg="类名不能为空">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl" style="margin-top: 5px">
			<label class="form-label col-3"><span class="c-red">*</span>方法名：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" value="${scheduleJob.serviceMethod}" id="serviceMethod" name="serviceMethod" datatype="*2-100" nullmsg="方法名不能为空">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl" style="margin-top: 5px">
			<label class="form-label col-3">状态：</label>
			<div class="formControls col-5"> <span class="select-box" style="width:150px;">
				<select class="select" name="status" size="1">
					<option value="0" <c:if test="${scheduleJob.status eq '0'}">selected="selected"</c:if>>启用</option>
					<option value="1" <c:if test="${scheduleJob.status eq '1'}">selected="selected"</c:if>>禁用</option>
				</select>
				</span> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3">备注：</label>
			<div class="formControls col-5">
				<textarea name="description" cols="" rows="" class="textarea"  dragonfly="true" onKeyUp="textarealength(this,300)">${scheduleJob.description}</textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/300</p>
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<div class="col-9 col-offset-3">
				<input class="btn btn-primary radius" type="button" id="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>
	</form>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/lib/icheck/jquery.icheck.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/lib/Validform/5.3.2/Validform.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/lib/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/H-ui.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/H-ui.admin.js"></script> 
<script type="text/javascript">
$(function(){
	$("#form-admin-add").Validform({
		tiptype:2,
		callback:function(form){
			form[0].submit();
			var index = parent.layer.getFrameIndex(window.name);
			parent.$('.btn-refresh').click();
			parent.layer.close(index);
		}
	});
	
	$("#submit").on("click", function() {
		$.ajax({ 
		    type:'post',   
		    url:'${pageContext.request.contextPath}/job/updateJob', 
		    data:$("#form-admin-add").serialize(), 
		    dataType:'json', 
		    success:function(data){ 
		    	if(data.status) {
		    		window.parent.location.reload();
		    	} else {
		    		layer.msg('修改失败!',{icon:1,time:1000});
		    	}
		    } 
		});
	});
});
</script>
</body>
</html>