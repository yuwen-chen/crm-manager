<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>会员信息</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> 
    <link href="${ctx!}/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx!}/assets/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx!}/assets/css/animate.css" rel="stylesheet">
    <link href="${ctx!}/assets/css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>完整验证表单</h5>
                    </div>
                    <div class="ibox-content">
                		<form class="form-horizontal m-t" id="editform" method="post" action="${ctx!}/admin/member/edit">
                			<input id="id" name="id" class="form-control" type="hidden" value="${staff.id}" <#if staff?exists> readonly="readonly"</#if> >
                        	<div class="form-group">
	   							<label class="col-sm-3 control-label">app平台：</label>
                                <div class="col-sm-8">
	                                <#if staff?exists>
	                                	<#list appPlatformList! as appPlatform> 
	                        				<#if appPlatform.type == staff.appPlatform>
	                        					<input id="appPlatform name="appPlatform" class="form-control" type="text" value="${appPlatform.appName!}" <#if staff?exists> readonly="readonly"</#if> >
	                        					<input id="staffTable" name="staffTable" class="form-control" type="hidden" value="${staff.appPlatform}" <#if staff?exists> readonly="readonly"</#if> >
	                        				</#if>
										</#list>
	                                	<#else>
	                                	<select id= "staffTable" name="staffTable" class="form-control">
	                                		<#list appPlatformList as appPlatform> 
		                        				<option value="${appPlatform.type}">${appPlatform.appName}</option>
											</#list>
	                                	</select>
	                                </#if>
                            	</div>
                            </div>
                        	<div class="form-group">
	   							<label class="col-sm-3 control-label">工作人员编号：</label>
                                <div class="col-sm-8">
                                	<#if staff?exists>
                                		<input id="staffNo" name="staffNo" class="form-control" type="text" value="${staff.staffNo}" readonly="readonly">
                                		<#else>
	                                	<select name="staffNo" class="form-control" readonly="readonly">
	                                		<#assign c= ["G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"]> 
											<#list c as i> 
												<option value="${i}" <#if member.staffNo == "${i}">selected="selected"</#if>>${i}</option>
											</#list>
	                                	</select>
                                	</#if>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">工作人员名称：</label>
                                <div class="col-sm-8">
                                    <input id="name" name="name" class="form-control" type="text" value="${staff.name}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">手机号：</label>
                                <div class="col-sm-8">
                                    <input id="phone" name="phone" class="form-control" type="text" value="${staff.phone}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">性别：</label>
                                <div class="col-sm-8">
                                	<select name="sex" class="form-control">
                                		<option value = 1 <#if staff.sex == 1>selected="selected"</#if>>男</option>
                                		<option value = 2 <#if staff.sex == 2>selected="selected"</#if>>女</option>
                                	</select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">状态：</label>
                                <div class="col-sm-8">
                                	<select name="status" class="form-control">
                                		<option value = "00" <#if staff.status == "00">selected="selected"</#if>>正常</option>
                                		<option value = "01" <#if staff.status == "01">selected="selected"</#if>>失效</option>
                                	</select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">电子邮件：</label>
                                <div class="col-sm-8">
                                    <input id="email" name="email" class="form-control" value="${staff.email}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button class="btn btn-primary" type="submit">提交</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </div>


    <!-- 全局js -->
    <script src="${ctx!}/assets/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx!}/assets/js/bootstrap.min.js?v=3.3.6"></script>

    <!-- 自定义js -->
    <script src="${ctx!}/assets/js/content.js?v=1.0.0"></script>

    <!-- jQuery Validation plugin javascript-->
    <script src="${ctx!}/assets/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${ctx!}/assets/js/plugins/validate/messages_zh.min.js"></script>
    <script src="${ctx!}/assets/js/plugins/layer/layer.min.js"></script>
    <script src="${ctx!}/assets/js/plugins/layer/laydate/laydate.js"></script>
    <script type="text/javascript">
    $(document).ready(function () {
    	var isAdd = $("#id").val();
    	if(isAdd == ""){
			$("#staffTable").val(parent.$("#appType").val());
			//$("#appPlatform").val(parent.$("#appType").val());
			//$("#staffTable").change(function(){
				//$("#appPlatform").val($(this).val());
			//});
    	}
    	
    	
    	/**
		 * 修改会员
		 * 
		 */
    	$("#editform").validate({
    	    rules: validateForm(),
    	    messages: {},
    	    submitHandler:function(form){
    	    	$.ajax({
   	    		   type: "POST",
   	    		   dataType: "json",
   	    		   url: "${ctx!}/admin/staff/edit",
   	    		   data: $(form).serialize(),
   	    		   success: function(msg){
	   	    			layer.msg(msg.message, {time: 2000},function(){
	   						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	   						parent.$("#appType").val($("#staffTable").val());
	   						parent.layer.close(index); 
	   					});
   	    		   }
   	    		});
            } 
    	});
    	
    	/**
		 * 表单校验
		 * 
		 */
    	function validateForm(){
    		return {
    			staffNo: {
    	        required: true,
    	      },
    			name: {
    	        required: true,
    	      },
    	    	phone: {
    	        required: true,
    	        minlength: 11,
    	    	maxlength: 11
    	      },
    	      	sex: {
    	        required: true
    	      },
    	      	status: {
    	        required: true
    	      },
    	      	email: {
    	      	email:true,
    	        required: true
    	      }
    	    }
    	}
    	
    });
    </script>

</body>

</html>
