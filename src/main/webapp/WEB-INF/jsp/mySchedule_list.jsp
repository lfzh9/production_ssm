<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>

<table class="easyui-datagrid" id="myScheduleList" title="日程列表" data-options="singleSelect:false,collapsible:true,
	pagination:true,rownumbers:true,url:'mySchedule/list',method:'get',pageSize:10,fitColumns:true,toolbar:toolbar_mySchedule">
    <thead>
        <tr>
			<th data-options="field:'ck',checkbox:true"></th>
			<th data-options="field:'id',align:'center',width:100">编号</th>
			<th data-options="field:'title',align:'center',width:100">标题</th>
			<th data-options="field:'type',align:'center',width:100">会议类型</th>
			<th data-options="field:'address',align:'center',width:100">会议地址</th>
			<th data-options="field:'text',align:'center',width:100">内容</th>
			<th data-options="field:'startTime',align:'center',width:100">开始时间</th>
			<th data-options="field:'endTime',align:'center',width:100">结束时间</th>
			<th data-options="field:'person',align:'center',width:100">创建者</th>
			<th data-options="field:'time',align:'center',width:100">创建时间</th>
			</tr>
    </thead>
</table> 

<div  id="toolbar_mySchedule" style=" height: 22px; padding: 3px 11px; background: #fafafa;">  
	
	<c:forEach items="${sessionScope.sysPermissionList}" var="per" >
		<c:if test="${per=='mySchedule:add' }" >
		    <div style="float: left;">  
		        <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="mySchedule_add()">新增</a>  
		    </div>  
		</c:if>
		<c:if test="${per=='mySchedule:edit' }" >
		    <div style="float: left;">  
		        <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="mySchedule_edit()">编辑</a>  
		    </div>  
		</c:if>
		<c:if test="${per=='mySchedule:delete' }" >
		    <div style="float: left;">  
		        <a href="#" class="easyui-linkbutton" plain="true" icon="icon-cancel" onclick="mySchedule_delete()">删除</a>  
		    </div>  
		</c:if>
	</c:forEach>
	
	<div class="datagrid-btn-separator"></div>  
	
	<div style="float: left;">  
		<a href="#" class="easyui-linkbutton" plain="true" icon="icon-reload" onclick="mySchedule_reload()">刷新</a>  
	</div>  
	
    <div id="search_mySchedule" style="float: right;">
        <input id="search_text_mySchedule" class="easyui-searchbox"  
            data-options="searcher:doSearch_mySchedule,prompt:'请输入...',menu:'#menu_mySchedule'"  
            style="width:250px;vertical-align: middle;">
        </input>
        <div id="menu_mySchedule" style="width:120px"> 
			<div data-options="name:'title'">标题</div> 
			<div data-options="name:'type'">会议类型</div>
			<div data-options="name:'person'">创建者</div> 
			<div data-options="name:'startTime'">开始时间</div> 
		</div>     
    </div>  
</div>  

<div id="myScheduleEditWindow" class="easyui-window" title="编辑日程" data-options="modal:true,closed:true,resizable:true,
	iconCls:'icon-save',href:'mySchedule/edit'" style="width:65%;height:80%;padding:10px;">
</div>
<div id="myScheduleAddWindow" class="easyui-window" title="添加日程" data-options="modal:true,closed:true,resizable:true,
	iconCls:'icon-save',href:'mySchedule/add'" style="width:65%;height:80%;padding:10px;">
</div>

<script>
function doSearch_mySchedule(value,name){ //用户输入用户名,点击搜素,触发此函数  
	if(value == null || value == ''){
		
		$("#myScheduleList").datagrid({
	        title:'日程列表', singleSelect:false, collapsible:true, pagination:true, rownumbers:true, method:'get',
			nowrap:true, toolbar:"toolbar_mySchedule", url:'mySchedule/list', method:'get', loadMsg:'数据加载中......',
			fitColumns:true,//允许表格自动缩放,以适应父容器
	        columns : [ [ 
				{field : 'ck', checkbox:true },
				{field : 'id', width : 100, align:'center', title : '编号'},
				{field : 'title', width : 100, align : 'center', title : '标题'},
				{field : 'type', width : 100, align : 'center', title : '会议类型'},
				{field : 'address', width : 100, align:'center', title : '会议地址'},
				{field : 'text', width : 100, align : 'center', title : '内容'},
				{field : 'startTime', width : 100, align : 'center', title : '开始时间'},
				{field : 'endTime', width : 100, align:'center', title : '结束时间'},
				{field : 'person', width : 100, align : 'center', title : '创建者'},
				{field : 'time', width : 100, align : 'center', title : '创建时间'}
				
	        ] ],  
	    });
	}else{
		$("#myScheduleList").datagrid({  
	        title:'日程列表', singleSelect:false, collapsible:true, pagination:true, rownumbers:true, method:'get',
			nowrap:true, toolbar:"toolbar_mySchedule", url:'mySchedule/search_mySchedule_by_'+name+'?searchValue='+value,
			loadMsg:'数据加载中......', fitColumns:true,//允许表格自动缩放,以适应父容器
	        columns : [ [ 
	             	{field : 'ck', checkbox:true },
				{field : 'id', width : 100, align:'center', title : '编号'},
				{field : 'title', width : 100, align : 'center', title : '标题'},
				{field : 'type', width : 100, align : 'center', title : '会议类型'},
				{field : 'address', width : 100, align:'center', title : '会议地址'},
				{field : 'text', width : 100, align : 'center', title : '内容'},
				{field : 'startTime', width : 100, align : 'center', title : '开始时间'},
				{field : 'endTime', width : 100, align:'center', title : '结束时间'},
				{field : 'person', width : 100, align : 'center', title : '创建者'},
				{field : 'time', width : 100, align : 'center', title : '创建时间'}
	        ] ],  
	    });
	}
}
	

	//根据index拿到该行值
	function onmyScheduleClickRow(index) {
		var rows = $('#myScheduleList').datagrid('getRows');
		return rows[index];
		
	}
	
	
    function getmyScheduleSelectionsIds(){
    	var myScheduleList = $("#myScheduleList");
    	var sels = myScheduleList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(","); 
    	
    	return ids;
    }
    
    function mySchedule_add(){
    	$.get("mySchedule/add_judge",'',function(data){
       		if(data.msg != null){
       			$.messager.alert('提示', data.msg);
       		}else{
       			$("#myScheduleAddWindow").window("open");
       		}
       	});
    }
    
    function mySchedule_edit(){
    	$.get("mySchedule/edit_judge",'',function(data){
       		if(data.msg != null){
       			$.messager.alert('提示', data.msg);
       		}else{
       			var ids = getmyScheduleSelectionsIds();
               	if(ids.length == 0){
               		$.messager.alert('提示','必须选择一个日程才能编辑!');
               		return ;
               	}
               	if(ids.indexOf(',') > 0){
               		$.messager.alert('提示','只能选择一个日程!');
               		return ;
               	}
               	
               	$("#myScheduleEditWindow").window({
               		onLoad :function(){
               			//回显数据
               			var data = $("#myScheduleList").datagrid("getSelections")[0];
               			
               			$("#myScheduleEditWindow").form("load", data);
               		}
               	}).window("open");
       		}
       	});
    }
    
    function mySchedule_delete(){
    	$.get("mySchedule/delete_judge",'',function(data){
      		if(data.msg != null){
      			$.messager.alert('提示', data.msg);
      		}else{
      			var ids = getmyScheduleSelectionsIds();
              	if(ids.length == 0){
              		$.messager.alert('提示','未选中日程!');
              		return ;
              	}
              	$.messager.confirm('确认','确定删除ID为 '+ids+' 的日程吗？',function(r){
              	    if (r){
              	    	var params = {"ids":ids};
                      	$.post("mySchedule/delete_batch",params, function(data){
                  			if(data.status == 200){
                  				$.messager.alert('提示','删除日程成功!',undefined,function(){
                  					$("#myScheduleList").datagrid("reload");
                  				});
                  			}
                  		});
              	    }
              	});
      		}
      	});
    }
    
    function mySchedule_reload(){
    	$("#myScheduleList").datagrid("reload");
    }
</script>