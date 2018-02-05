<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>会员列表</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> 
    <link href="${ctx!}/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx!}/assets/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="${ctx!}/assets/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">

    <link href="${ctx!}/assets/css/animate.css" rel="stylesheet">
    <link href="${ctx!}/assets/css/style.css?v=4.1.0" rel="stylesheet">


</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox ">
                    <div class="ibox-title">
                        <h5>会员管理</h5>
                    </div>
                    <div class="ibox-content">
                        <p>
                        	<@shiro.hasPermission name="crm:member:add">
                        		<button class="btn btn-success " type="button" onclick="add();"><i class="fa fa-plus"></i>&nbsp;添加</button>
                        	</@shiro.hasPermission>
                        </p>
                        <hr>
                        <!--查询窗体-->
                        <div>
	                    	<table style="width:100%">
	                    		<tr class="" style=""> 
	                    			<td style="padding: 8px;">
	                    				<label class="pull-right" >app平台：</label>
	                    			</td>
	                    			<td style="">
	                    				<div class="" style="width: 105px;">
		                                	<select id = "appType" name="memberTable" class="form-control">
		                                		<#list appPlatformList as appPlatform> 
		                                			<option value="${appPlatform.type}">${appPlatform.appName}</option>
												</#list>
		                                	</select>
		                                </div>
	                    			</td>
	                    			
	                    			<td  style="">
	                    				<div class="pull-right" style=""><button class="btn btn-primary" id="querybtn">查询</button></div>
	                    			</td>
	                    		</tr>
	                    	<table>
	                    </div>
	                    <hr>
                        <div class="row row-lg">
		                    <div class="col-sm-12">
		                        <!-- Example Card View -->
		                        <div class="example-wrap">
		                            <div class="example">
		                            	<table id="table_list" style="border-width: 0px;"></table>
		                            </div>
		                        </div>
		                        <!-- End Example Card View -->
		                    </div>
	                    </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- 全局js -->
    <script src="${ctx!}/assets/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx!}/assets/js/bootstrap.min.js?v=3.3.6"></script>
    
	<!-- Bootstrap table -->
    <script src="${ctx!}/assets/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${ctx!}/assets/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="${ctx!}/assets/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

    <!-- Peity -->
    <script src="${ctx!}/assets/js/plugins/peity/jquery.peity.min.js"></script>

    <script src="${ctx!}/assets/js/plugins/layer/layer.min.js"></script>

    <!-- 自定义js -->
    <script src="${ctx!}/assets/js/content.js?v=1.0.0"></script>

    <!-- Page-Level Scripts -->
    <script>
		$(document).ready(function () {
			//调用函数，初始化表格  
			initTable();  
  
			//当点击查询按钮的时候执行  
			$("#querybtn").bind("click", initTable);  
		});
        
		//初始化数据
		function initTable() { 
			//先销毁表格  
			$('#table_list').bootstrapTable('destroy'); 
			//初始化表格,动态从服务器加载数据  
			$("#table_list").bootstrapTable({
			    //使用get请求到服务器获取数据  
			    method: "POST",
			    //必须设置，不然request.getParameter获取不到请求参数
			    contentType: "application/x-www-form-urlencoded",
			    //获取数据的Servlet地址  
			    url: "${ctx!}/admin/member/list",
			    //表格显示条纹  
			    striped: true,
			    //启动分页  
			    pagination: true,
			    //每页显示的记录数  
			    pageSize: 10,
			    //当前第几页  
			    pageNumber: 1,
			    //记录数可选列表  
			    pageList: [5, 10, 15, 20, 25],
			    //是否启用查询  
			    search: true,
			    //是否启用详细信息视图
			    detailView:false,
			    //表示服务端请求  
			    sidePagination: "server",
			    //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder  
			    //设置为limit可以获取limit, offset, search, sort, order 
			    search: false, // 不显示 搜索框
			    classes: 'table table-bordered', // Class样式
				// showRefresh : true, // 显示刷新按钮
				silent: true, // 必须设置刷新事件
				//queryParams: queryParams, // 请求参数，这个关系到后续用到的异步刷新
				queryParams: function queryParams(params) {   //设置查询参数
					var param = {    
						pageNumber:params.pageNumber,  
						pageSize:params.pageSize,
						memberTable:$("#appType").val()
  					};    
      				return param;                     
	            },
			    queryParamsType: "undefined",
			    //json数据解析
			    responseHandler: function(res) {
			        return {
			            "rows": res.list,
			            "total": res.total
			        };
			    },
			    //数据列
			    columns: [{
			        title: "会员ID",
			        field: "id",
			        sortable: true
			    },{
			        title: "真实姓名",
			        field: "realName"
			    },{
			        title: "手机号",
			        field: "phone"
			    },{
			        title: "证件类型",
			        field: "indentityType"
			    },{
			    	title: "证件号",
			        field: "indentityNo"
			    },{
			        title: "性别",
			        field: "sex",
			        formatter: function (value, row, index) {
                        if (value == 1) {
                        	return '<span class="label label-info">男</span>';
                        }
                    	return '<span class="label label-danger">女</span>';
                    }
			    },{
			        title: "地址",
			        field: "address"
			    },{
			    	title: "用户状态",
			        field: "userStatus",
			        formatter: function (value, row, index) {
                        if (value == '00') {
                        	return '<span class="label label-info">正常</span>';
                        }
                    	return '<span class="label label-danger">冻结</span>';
                    }
			    },{
			        title: "邮箱",
			        field: "email"
			    },{
			        title: "app平台",
			        field: "appPlatform",
			        formatter: function (value, row, index) {
                        if (value == 'A') {
                        	return '<span class="label label-info">融侨宝</span>';
                        }
                        if (value == 'B') {
                        	return '<span class="label label-info">融侨财富</span>';
                        }
                        if (value == 'C') {
                        	return '<span class="label label-info">融侨普惠</span>';
                        }
                    }
			    },{
			    	title: "理财等级",
			        field: "financialLevel"
			    },{
			        title: "注册日期",
			        field: "registerDate"
			    },{
			        title: "人员编号",
			        field: "staffNo"
			    
			    },{
			        title: "操作",
			        field: "empty",
                    formatter: function (value, row, index) {
                    	var operateHtml = '<@shiro.hasPermission name="crm:member:edit"><button class="btn btn-primary btn-xs" type="button" onclick="edit(\''+row.id+"','"+row.appPlatform+'\')"><i class="fa fa-edit"></i>&nbsp;修改</button> &nbsp;</@shiro.hasPermission>';
                    	operateHtml = operateHtml + '<@shiro.hasPermission name="crm:member:deleteBatch"><button class="btn btn-danger btn-xs" type="button" onclick="del(\''+row.id+"','"+row.appPlatform+'\')"><i class="fa fa-remove"></i>&nbsp;删除</button> &nbsp;</@shiro.hasPermission>';
                        return operateHtml;
                    }
			    }]
			});
		}
		
		
		
        function add(){
        	layer.open({
        	      type: 2,
        	      title: '会员添加',
        	      shadeClose: true,
        	      shade: false,
        	      area: ['893px', '600px'],
        	      content: '${ctx!}/admin/member/addview',
        	      end: function(index){
        	      		initTable(); 
        	    	  //$('#table_list').bootstrapTable("refresh");
       	    	  }
        	    });
        }
        
        /*function edit(id,appPlatform){
	        $.ajax({  
			      type: 'POST',  
			      url: '${ctx!}/admin/member/editview',//发送请求  
			      data: {id:id,memberTable:appPlatform},  
			      dataType : "html",  
			      success: function(result) {  
			          var htmlCont = result;//返回的结果页面  
			          layer.open({
		        	      type: 1,
		        	      title: '会员修改',
		        	      shadeClose: true,
		        	      shade: false,
		        	      area: ['893px', '600px'],
		        	      content: htmlCont,
		        	      end: function(index){
		        	    	  initTable(); 
		        	    	  //$('#table_list').bootstrapTable("refresh");
		       	    	  }
					});
				}  
			}); 
        }*/
        
        function edit(id,appPlatform){
			layer.open({
				type: 2,
				title: '会员修改',
				shadeClose: true,
				shade: false,
				area: ['893px', '600px'],
				content: '${ctx!}/admin/member/editview/'+appPlatform+'/'+id,//发送请求  
				end: function(index){
					initTable(); 
					//$('#table_list').bootstrapTable("refresh");
				}
			});
        }
        
        function del(id,appPlatform){
        	layer.confirm('确定删除吗?', {icon: 3, title:'提示'}, function(index){
        		$.ajax({
    	    		   type: "POST",
    	    		   dataType: "json",
    	    		   url: "${ctx!}/admin/member/delete/" + id,
    	    		   data: {id:id,memberTable:appPlatform},  
    	    		   success: function(msg){
	 	   	    			layer.msg(msg.message, {time: 2000},function(){
	 	   	    				initTable(); 
	 	   	    				//$('#table_list').bootstrapTable("refresh");
	 	   	    				layer.close(index);
	 	   					});
    	    		   }
    	    	});
       		});
        }
		
	  
    </script>
	<style>
	
		
		
	<style>
	
    
    

</body>

</html>
