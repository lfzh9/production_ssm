<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>

<table class="easyui-datagrid" id="operationLogList" title="操作日志" data-options="singleSelect:false,collapsible:true,
	pagination:true,rownumbers:true,url:'operationLog/list',method:'get',pageSize:10,fitColumns:true,toolbar:toolbar_operationLog">
    <thead>
        <tr>
			<th data-options="field:'ck',checkbox:true"></th>
			<th data-options="field:'id',align:'center',width:100">编号</th>
			<th data-options="field:'date',align:'center',width:100">操作时间</th>
			<th data-options="field:'desc',align:'center',width:200">操作描述</th>
			<th data-options="field:'name',align:'center',width:100">操作用户</th>
			</tr>
    </thead>
</table> 

<div  id="toolbar_operationLog" style=" height: 22px; padding: 3px 11px; background: #fafafa;">  
	
	<c:forEach items="${sessionScope.sysPermissionList}" var="per" >
		<c:if test="${per=='operationLog:delete' }" >
		    <div style="float: left;">  
		        <a href="#" class="easyui-linkbutton" plain="true" icon="icon-cancel" onclick="operationLog_delete()">删除</a>  
		    </div>  
		</c:if>
	</c:forEach>
	
	<div class="datagrid-btn-separator"></div>  
	
	<div style="float: left;">  
		<a href="#" class="easyui-linkbutton" plain="true" icon="icon-reload" onclick="operationLog_reload()">刷新</a>  
	</div>  
	
    <div id="search_operationLog" style="float: right;">
        <input id="search_text_operationLog" class="easyui-searchbox"  
            data-options="searcher:doSearch_operationLog,prompt:'请输入...',menu:'#menu_operationLog'"  
            style="width:250px;vertical-align: middle;">
        </input>
        <div id="menu_operationLog" style="width:120px"> 
			<div data-options="name:'id'">编号</div> 
			<div data-options="name:'date'">操作时间</div>
			<div data-options="name:'name'">操作用户</div>
		</div>     
    </div>  
</div>  


<script>
function doSearch_operationLog(value,name){ //用户输入用户名,点击搜素,触发此函数  
	if(value == null || value == ''){
		
		$("#operationLogList").datagrid({
	        title:'操作日志', singleSelect:false, collapsible:true, pagination:true, rownumbers:true, method:'get',
			nowrap:true, toolbar:"toolbar_operationLog", url:'operationLog/list', method:'get', loadMsg:'数据加载中......',
			fitColumns:true,//允许表格自动缩放,以适应父容器
	        columns : [ [ 
				{field : 'ck', checkbox:true },
				{field : 'id', width : 100, align:'center', title : '编号'},
				{field : 'date', width : 100, align : 'center', title : '操作时间'},
				{field : 'desc', width : 100, align : 'center', title : '操作描述'},
				{field : 'name', width : 100, align : 'center', title : '操作用户'}
	        ] ],  
	    });
	}else{
		$("#operationLogList").datagrid({  
	        title:'操作日志', singleSelect:false, collapsible:true, pagination:true, rownumbers:true, method:'get',
			nowrap:true, toolbar:"toolbar_operationLog", url:'operationLog/search_operationLog_by_'+name+'?searchValue='+value,
			loadMsg:'数据加载中......', fitColumns:true,//允许表格自动缩放,以适应父容器
	        columns : [ [ 
		            {field : 'ck', checkbox:true },
					{field : 'id', width : 100, align:'center', title : '编号'},
					{field : 'date', width : 100, align : 'center', title : '操作时间'},
					{field : 'desc', width : 100, align : 'center', title : '操作描述'},
					{field : 'name', width : 100, align : 'center', title : '操作用户'}
	        ] ],  
	    });
	}
}
	

	//根据index拿到该行值
	function onoperationLogClickRow(index) {
		var rows = $('#operationLogList').datagrid('getRows');
		return rows[index];
		
	}
	
	
    function getoperationLogSelectionsIds(){
    	var operationLogList = $("#operationLogList");
    	var sels = operationLogList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(","); 
    	
    	return ids;
    }
    
    function operationLog_delete(){
    	$.get("operationLog/delete_judge",'',function(data){
      		if(data.msg != null){
      			$.messager.alert('提示', data.msg);
      		}else{
      			var ids = getoperationLogSelectionsIds();
      			alert(ids);
              	if(ids.length == 0){
              		$.messager.alert('提示','未选中日志!');
              		return ;
              	}
              	$.messager.confirm('确认','确定删除ID为 '+ids+' 的日志吗？',function(r){
              	    if (r){
              	    	var params = {"ids":ids};
                      	$.post("operationLog/delete_batch",params, function(data){
                  			if(data.status == 200){
                  				$.messager.alert('提示','删除日志成功!',undefined,function(){
                  					$("#operationLogList").datagrid("reload");
                  				});
                  			}
                  		});
              	    }
              	});
      		}
      	});
    }
    
    function operationLog_reload(){
    	$("#operationLogList").datagrid("reload");
    }
</script>