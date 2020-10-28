<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>

<table class="easyui-datagrid" id="mynoteList" title="便签列表" data-options="singleSelect:false,collapsible:true,
	pagination:true,rownumbers:true,url:'mynote/list',method:'get',pageSize:10,fitColumns:true,toolbar:toolbar_mynote">
    <thead>
        <tr>
			<th data-options="field:'ck',checkbox:true"></th>
			<th data-options="field:'id',align:'center',width:100">编号</th>
			<th data-options="field:'title',align:'center',width:100">标题</th>
			<th data-options="field:'text',align:'center',width:100">内容</th>
			<th data-options="field:'person',align:'center',width:100">创建者</th>
			<th data-options="field:'time',align:'center',width:100">创建时间</th>
			</tr>
    </thead>
</table> 

<div  id="toolbar_mynote" style=" height: 22px; padding: 3px 11px; background: #fafafa;">  
	
	<c:forEach items="${sessionScope.sysPermissionList}" var="per" >
		<c:if test="${per=='mynote:add' }" >
		    <div style="float: left;">  
		        <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="mynote_add()">新增</a>  
		    </div>  
		</c:if>
		<c:if test="${per=='mynote:edit' }" >
		    <div style="float: left;">  
		        <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="mynote_edit()">编辑</a>  
		    </div>  
		</c:if>
		<c:if test="${per=='mynote:delete' }" >
		    <div style="float: left;">  
		        <a href="#" class="easyui-linkbutton" plain="true" icon="icon-cancel" onclick="mynote_delete()">删除</a>  
		    </div>  
		</c:if>
	</c:forEach>
	
	<div class="datagrid-btn-separator"></div>  
	
	<div style="float: left;">  
		<a href="#" class="easyui-linkbutton" plain="true" icon="icon-reload" onclick="mynote_reload()">刷新</a>  
	</div>  
	
    <div id="search_mynote" style="float: right;">
        <input id="search_text_mynote" class="easyui-searchbox"  
            data-options="searcher:doSearch_mynote,prompt:'请输入...',menu:'#menu_mynote'"  
            style="width:250px;vertical-align: middle;">
        </input>
        <div id="menu_mynote" style="width:120px"> 
			<div data-options="name:'title'">标题</div> 
			<div data-options="name:'person'">创建者</div> 
			<div data-options="name:'time'">创建时间</div> 
		</div>     
    </div>  
</div>  

<div id="mynoteEditWindow" class="easyui-window" title="编辑便签" data-options="modal:true,closed:true,resizable:true,
	iconCls:'icon-save',href:'mynote/edit'" style="width:65%;height:80%;padding:10px;">
</div>
<div id="mynoteAddWindow" class="easyui-window" title="添加便签" data-options="modal:true,closed:true,resizable:true,
	iconCls:'icon-save',href:'mynote/add'" style="width:65%;height:80%;padding:10px;">
</div>

<script>
function doSearch_mynote(value,name){ //用户输入用户名,点击搜素,触发此函数  
	if(value == null || value == ''){
		
		$("#mynoteList").datagrid({
	        title:'便签列表', singleSelect:false, collapsible:true, pagination:true, rownumbers:true, method:'get',
			nowrap:true, toolbar:"toolbar_mynote", url:'mynote/list', method:'get', loadMsg:'数据加载中......',
			fitColumns:true,//允许表格自动缩放,以适应父容器
	        columns : [ [ 
				{field : 'ck', checkbox:true },
				{field : 'id', width : 100, align:'center', title : '编号'},
				{field : 'title', width : 100, align : 'center', title : '标题'},
				{field : 'text', width : 100, align : 'center', title : '内容'},
				{field : 'person', width : 100, align : 'center', title : '创建者'},
				{field : 'time', width : 100, align : 'center', title : '创建时间'}
				
	        ] ],  
	    });
	}else{
		$("#mynoteList").datagrid({  
	        title:'便签列表', singleSelect:false, collapsible:true, pagination:true, rownumbers:true, method:'get',
			nowrap:true, toolbar:"toolbar_mynote", url:'mynote/search_mynote_by_'+name+'?searchValue='+value,
			loadMsg:'数据加载中......', fitColumns:true,//允许表格自动缩放,以适应父容器
	        columns : [ [ 
	             	{field : 'ck', checkbox:true },
				{field : 'id', width : 100, align:'center', title : '编号'},
				{field : 'title', width : 100, align : 'center', title : '标题'},
				{field : 'text', width : 100, align : 'center', title : '内容'},
				{field : 'person', width : 100, align : 'center', title : '创建者'},
				{field : 'time', width : 100, align : 'center', title : '创建时间'}
	        ] ],  
	    });
	}
}
	

	//根据index拿到该行值
	function onmynoteClickRow(index) {
		var rows = $('#mynoteList').datagrid('getRows');
		return rows[index];
		
	}
	
	
    function getmynoteSelectionsIds(){
    	var mynoteList = $("#mynoteList");
    	var sels = mynoteList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(","); 
    	
    	return ids;
    }
    
    function mynote_add(){
    	$.get("mynote/add_judge",'',function(data){
       		if(data.msg != null){
       			$.messager.alert('提示', data.msg);
       		}else{
       			$("#mynoteAddWindow").window("open");
       		}
       	});
    }
    
    function mynote_edit(){
    	$.get("mynote/edit_judge",'',function(data){
       		if(data.msg != null){
       			$.messager.alert('提示', data.msg);
       		}else{
       			var ids = getmynoteSelectionsIds();
               	if(ids.length == 0){
               		$.messager.alert('提示','必须选择一个日程才能编辑!');
               		return ;
               	}
               	if(ids.indexOf(',') > 0){
               		$.messager.alert('提示','只能选择一个日程!');
               		return ;
               	}
               	
               	$("#mynoteEditWindow").window({
               		onLoad :function(){
               			//回显数据
               			var data = $("#mynoteList").datagrid("getSelections")[0];
               			
               			$("#mynoteEditWindow").form("load", data);
               		}
               	}).window("open");
       		}
       	});
    }
    
    function mynote_delete(){
    	$.get("mynote/delete_judge",'',function(data){
      		if(data.msg != null){
      			$.messager.alert('提示', data.msg);
      		}else{
      			var ids = getmynoteSelectionsIds();
              	if(ids.length == 0){
              		$.messager.alert('提示','未选中日程!');
              		return ;
              	}
              	$.messager.confirm('确认','确定删除ID为 '+ids+' 的日程吗？',function(r){
              	    if (r){
              	    	var params = {"ids":ids};
                      	$.post("mynote/delete_batch",params, function(data){
                  			if(data.status == 200){
                  				$.messager.alert('提示','删除日程成功!',undefined,function(){
                  					$("#mynoteList").datagrid("reload");
                  				});
                  			}
                  		});
              	    }
              	});
      		}
      	});
    }
    
    function mynote_reload(){
    	$("#mynoteList").datagrid("reload");
    }
</script>