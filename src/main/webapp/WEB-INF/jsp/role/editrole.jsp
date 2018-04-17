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
<link href="${pageContext.request.contextPath}/ui/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/ui/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.m{}
</style>
<title>角色修改</title>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="form-role-edit">
		<input type="hidden" name="id" value="${sysRole.id}">
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>角色名称：</label>
			<div class="formControls col-10">
				<input type="text" class="input-text" value="${sysRole.name}" placeholder="" id="name" name="name" datatype="*2-30" nullmsg="角色名称不能为空">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-2">角色权限：</label>
			<div class="formControls col-10">
				<c:if test="${!empty privilages}">
					<c:forEach items="${privilages}" var="privilage">
						<c:forEach items="${rolePrivilages}" var="rolePrivilage">
							<c:if test="${privilage.id eq rolePrivilage.privilageId}"><c:set value="checked" var="xx" scope="page"/></c:if>
						</c:forEach>
						<dl class="permission-list">
							<dt class="m">
								<label><input type="checkbox" name="pids" ${xx} value="${privilage.id}" name="user-Character-0" id="user-Character-0">${privilage.name}</label>
								<c:remove var="xx" scope="page"/>
							</dt>
							<c:if test="${!empty privilage.children}">
								<c:forEach items="${privilage.children}" var="children">
									<c:forEach items="${rolePrivilages}" var="rolePrivilage">
										<c:if test="${children.id eq rolePrivilage.privilageId}"><c:set value="checked" var="xx" scope="page"/></c:if>
									</c:forEach>
									<dd>
										<dl class="cl permission-list2">
											<dt>
												<label><input type="checkbox" name="pids" ${xx} value="${children.id}" name="user-Character-0-0" id="user-Character-0-0">${children.name}</label>
												<c:remove var="xx" scope="page"/>
											</dt>
											<c:if test="${!empty children.children}">
												<dd>
													<c:forEach items="${children.children}" var="info">
														<c:forEach items="${rolePrivilages}" var="rolePrivilage">
															<c:if test="${info.id eq rolePrivilage.privilageId}"><c:set value="checked" var="xx" scope="page"/></c:if>
														</c:forEach>
														<label><input type="checkbox" name="pids" ${xx} value="${info.id}" name="user-Character-0-0-0" id="user-Character-0-0-0">${info.name}</label>
														<c:remove var="xx" scope="page"/>
													</c:forEach>
												</dd>
											</c:if>
										</dl>
									</dd>
								</c:forEach>
							</c:if>
						</dl>
					</c:forEach>
				</c:if>
			</div>
		</div>
		<div class="row cl">
			<div class="col-10 col-offset-2">
				<button type="button" id="submit" class="btn btn-success radius" id="admin-role-save" name="admin-role-save"><i class="icon-ok"></i> 确定</button>
			</div>
		</div>
	</form>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/lib/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/lib/laypage/1.2/laypage.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/H-ui.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/H-ui.admin.js"></script> 
<script>
$(function(){
	$(".permission-list .m input:checkbox").click(function(){
		var status = $(this).prop("checked");
		if(!status) {
			$(this).closest("dl").find("dd input:checkbox").prop("checked",$(this).prop("checked"));
		}
	});
	$(".permission-list dd dl dt input:checkbox").click(function(){
		var status = $(this).prop("checked");
		if(status) {
			$(this).parent().parent().parent().parent().parent().find(".m input:checkbox").prop("checked",$(this).prop("checked"));
		} else {
			$(this).parent().parent().siblings("dd").find("input:checkbox").prop("checked",$(this).prop("checked"));
		}
	});
	
	$(".permission-list2 dd input:checkbox").click(function(){
		if($(this).prop("checked")){
			$(this).closest("dl").find("dt input:checkbox").prop("checked",true);
			$(this).parents(".permission-list").find("dt").first().find("input:checkbox").prop("checked",true);
		}
	});
	
	$("#submit").on("click", function() {
		$.ajax({ 
		    type:'post',   
		    url:'${pageContext.request.contextPath}/role/updateRole', 
		    data:$("#form-role-edit").serialize(), 
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