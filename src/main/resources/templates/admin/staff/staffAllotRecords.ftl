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
                        <h5>分配列表</h5>
                    </div>
                    <div class="ibox-content">
                    	<div class="example-wrap">
	                    <div class="example">
	                    	<table id="allot_records"></table>
	                    </div>
	                </div>
                    </div>
                </div>
            </div>
        </div>
		<input id="staffAllptRecordsfNo" name="staffNo" class="form-control" type="hidden" value="${staffNo}">
        <input id="allotRecordsTable" name="allotRecordsTable" class="form-control" type="hidden" value="${appPlatform}">
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
    <script type="text/javascript">
     $(document).ready(function () {
        	//初始化表格,动态从服务器加载数据  
			$("#allot_records").bootstrapTable({
			    //使用get请求到服务器获取数据  
			    method: "POST",
			    //必须设置，不然request.getParameter获取不到请求参数
			    contentType: "application/x-www-form-urlencoded",
			    //获取数据的Servlet地址  
			    url: "${ctx!}/admin/staff/queryAllotrecords",
			    //表格显示条纹  
			    striped: true,
			    //启动分页  
			    pagination: false,
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
			    search: false, // 不显示 搜索框
			    classes: 'table table-bordered', // Class样式
			    //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder  
			    //设置为limit可以获取limit, offset, search, sort, order  
			    queryParamsType: "undefined",
			    queryParams: function queryParams(params) {   //设置查询参数
					var param = {    
						pageNumber:params.pageNumber,  
						pageSize:params.pageSize,
						staffNo:$("#staffAllptRecordsfNo").val(),
						allotRecordsTable:$("#allotRecordsTable").val()
  					};    
      				return param;                     
	            },
			    //json数据解析
			    responseHandler: function(res) {
			        return {
			            "rows": res,
			            "total": res.size
			        };
			    },
			    //数据列
			    columns: [{
			        title: "ID",
			        field: "id",
			        sortable: true
			    },{
			      	title: "工作人员编号",
			        field: "staffNo",
			        sortable: true
			    },{
			        title: "app平台",
			        field: "appPlatform",
			        formatter: function (value, row, index) {
                        if (value == 'A') {
                        	return '<span class="label label-info">融侨宝</span>';
                        }
                        if (value == 'B') {
                        	return '<span class="label label-info">金管家</span>';
                        }
                        if (value == 'C') {
                        	return '<span class="label label-info">融侨普惠</span>';
                        }
                    }
			    },{
			        title: "会员ID",
			        field: "memberId"
			    },{
			        title: "分配时间",
			        field: "allotTime",
			        sortable: true
			    }]
			});
        });
    	
    </script>

</body>

</html>
