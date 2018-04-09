<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
<title>资源添加</title>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="form-privilage-add">
		<div class="row cl" style="margin-top: 5px">
			<label class="form-label col-3">上级菜单：</label>
			<div class="formControls col-5"> <span class="select-box" style="width:200px;">
				<select class="select" name="parentId" size="1">
					<option value="1">菜单资源</option>
					<option value="0">普通资源</option>
				</select>
				</span> 
			</div>
		</div>
		<div class="row cl" style="margin-top: 5px">
			<label class="form-label col-3"><span class="c-red">*</span>资源名称：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" value="" placeholder="" id="name" name="name" datatype="*2-30" nullmsg="资源名称不能为空">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl" style="margin-top: 5px">
			<label class="form-label col-3"><span class="c-red">*</span>resKey：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" value="" placeholder="" id="resKey" name="resKey" datatype="*2-30" nullmsg="resKey不能为空">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl" style="margin-top: 5px">
			<label class="form-label col-3"><span class="c-red">*</span>资源路径：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" value="" placeholder="" id="url" name="url" datatype="*2-300" nullmsg="资源路径不能为空">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl" style="margin-top: 5px">
			<label class="form-label col-3">资源类型：</label>
			<div class="formControls col-5"> <span class="select-box" style="width:150px;">
				<select class="select" name="isMenu" size="1">
					<option value="1">菜单资源</option>
					<option value="0">普通资源</option>
				</select>
				</span> 
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-3">资源描述：</label>
			<div class="formControls col-5">
				<textarea name="description" cols="" rows="" class="textarea"  placeholder="" dragonfly="true" onKeyUp="textarealength(this,300)"></textarea>
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
	$("#form-privilage-add").Validform({
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
		    url:'${pageContext.request.contextPath}/privilage/createPrivilage', 
		    data:$("#form-privilage-add").serialize(), 
		    dataType:'json', 
		    success:function(data){ 
		    	if(data.status) {
		    		window.parent.location.reload();
		    	} else {
		    		layer.msg('添加失败!',{icon:1,time:1000});
		    	}
		    } 
		});
	});
});
</script>
</body>
</html>