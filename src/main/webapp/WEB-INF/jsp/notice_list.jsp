<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>

<table class="easyui-datagrid" id="noticeList" title="公告列表" data-options="singleSelect:false,collapsible:true,
	pagination:true,rownumbers:true,url:'notice/list',method:'get',pageSize:10,fitColumns:true,toolbar:toolbar_notice">
    <thead>
        <tr>
			<th data-options="field:'type',align:'center',width:100">消息类型</th>
			<th data-options="field:'text',align:'center',width:100">消息内容</th>
			<th data-options="field:'time',align:'center',width:100">发布时间</th>
			</tr>
    </thead>
</table> 

<div  id="toolbar_notice" style=" height: 22px; padding: 3px 11px; background: #fafafa;">  

	<div class="datagrid-btn-separator"></div>  
	
	<div style="float: left;">  
		<a href="#" class="easyui-linkbutton" plain="true" icon="icon-reload" onclick="notice_reload()">刷新</a>  
	</div>  
	
</div>  

<div id="noticeEditWindow" class="easyui-window" title="编辑公告" data-options="modal:true,closed:true,resizable:true,
	iconCls:'icon-save',href:'notice/edit'" style="width:65%;height:80%;padding:10px;">
</div>
<div id="noticeAddWindow" class="easyui-window" title="添加公告" data-options="modal:true,closed:true,resizable:true,
	iconCls:'icon-save',href:'notice/add'" style="width:65%;height:80%;padding:10px;">
</div>

<script>
function doSearch_notice(value,name){ //用户输入用户名,点击搜素,触发此函数  
	if(value == null || value == ''){
		
		$("#noticeList").datagrid({
	        title:'公告列表', singleSelect:false, collapsible:true, pagination:true, rownumbers:true, method:'get',
			nowrap:true, toolbar:"toolbar_notice", url:'notice/list', method:'get', loadMsg:'数据加载中......',
			fitColumns:true,//允许表格自动缩放,以适应父容器
	        columns : [ [ 
				{field : 'type', width : 100, align:'center', title : '消息类型'},
				{field : 'text', width : 100, align : 'center', title : '消息内容'},
				{field : 'time', width : 100, align : 'center', title : '发布时间'}
				
	        ] ],  
	    });
	}else{
		$("#noticeList").datagrid({  
	        title:'公告列表', singleSelect:false, collapsible:true, pagination:true, rownumbers:true, method:'get',
			nowrap:true, toolbar:"toolbar_notice", url:'notice/search_notice_by_'+name+'?searchValue='+value,
			loadMsg:'数据加载中......', fitColumns:true,//允许表格自动缩放,以适应父容器
	        columns : [ [ 
	             	{field : 'type', width : 100, align:'center', title : '消息类型'},
					{field : 'text', width : 100, align : 'center', title : '消息内容'},
					{field : 'time', width : 100, align : 'center', title : '发布时间'}
	        ] ],  
	    });
	}
}
	

	//根据index拿到该行值
	function onnoticeClickRow(index) {
		var rows = $('#noticeList').datagrid('getRows');
		return rows[index];
		
	}
	
	
    function getnoticeSelectionsIds(){
    	var noticeList = $("#noticeList");
    	var sels = noticeList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(","); 
    	
    	return ids;
    }
    
    function notice_reload(){
    	$("#noticeList").datagrid("reload");
    }
</script>