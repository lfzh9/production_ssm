	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>

<table class="easyui-datagrid" id="staffList" title="机构列表" data-options="singleSelect:false,collapsible:true,
	pagination:true,rownumbers:true,url:'staff/list',method:'get',pageSize:10,fitColumns:true,toolbar:toolbar_staff">
    <thead>
        <tr>
			<th data-options="field:'ck',checkbox:true"></th>
			<th data-options="field:'id',align:'center',width:100">员工编号</th>
			<th data-options="field:'username',align:'center',width:100">姓名</th>
			<th data-options="field:'sex',align:'center',width:100" >性别</th>
			<th data-options="field:'locked',align:'center',width:100">状态</th>
			<th data-options="field:'dept_name',align:'center',width:100">部门</th>
			<th data-options="field:'role_name',align:'center',width:100">角色类型</th>
			</tr>
    </thead>
</table> 

<div  id="toolbar_staff" style=" height: 22px; padding: 3px 11px; background: #fafafa;">  
	
	<c:forEach items="${sessionScope.sysPermissionList}" var="per" >
		<c:if test="${per=='staff:add' }" >
		    <div style="float: left;">  
		        <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="staff_add()">新增</a>  
		    </div>  
		</c:if>
		<c:if test="${per=='staff:edit' }" >
		    <div style="float: left;">  
		        <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="staff_edit()">编辑</a>  
		    </div>  
		</c:if>
		<c:if test="${per=='staff:delete' }" >
		    <div style="float: left;">  
		        <a href="#" class="easyui-linkbutton" plain="true" icon="icon-cancel" onclick="staff_delete()">删除</a>  
		    </div>  
		</c:if>
	</c:forEach>
	
	<div class="datagrid-btn-separator"></div>  
	
	<div style="float: left;">  
		<a href="#" class="easyui-linkbutton" plain="true" icon="icon-reload" onclick="staff_reload()">刷新</a>  
	</div>  
	
    <div id="search_staff" style="float: right;">
        <input id="search_text_staff" class="easyui-searchbox"  
            data-options="searcher:doSearch_staff,prompt:'请输入...',menu:'#menu_staff'"  
            style="width:250px;vertical-align: middle;">
        </input>
        <div id="menu_staff" style="width:120px"> 
			<div data-options="name:'id'">员工编号</div> 
			<div data-options="name:'username'">姓名</div>
		
			
		</div>     
    </div>  
</div>  

<div id="staffEditWindow" class="easyui-window" title="编辑机构" data-options="modal:true,closed:true,resizable:true,
	iconCls:'icon-save',href:'staff/edit'" style="width:65%;height:80%;padding:10px;">
</div>
<div id="staffAddWindow" class="easyui-window" title="添加机构" data-options="modal:true,closed:true,resizable:true,
	iconCls:'icon-save',href:'staff/add'" style="width:65%;height:80%;padding:10px;">
</div>

<script>
function doSearch_staff(value,name){ //用户输入用户名,点击搜素,触发此函数  
	if(value == null || value == ''){
		
		$("#staffList").datagrid({
	        title:'机构列表', singleSelect:false, collapsible:true, pagination:true, rownumbers:true, method:'get',
			nowrap:true, toolbar:"toolbar_staff", url:'staff/list', method:'get', loadMsg:'数据加载中......',
			fitColumns:true,//允许表格自动缩放,以适应父容器
	        columns : [ [ 
				{field : 'ck', checkbox:true },
				{field : 'id', width : 100, align:'center', title : '员工编号'},
				{field : 'username', width : 100, align : 'center', title : '姓名'},
				{field : 'sex', width : 100, align : 'center', title : '性别'},
				{field : 'locked', width : 100, align : 'center', title : '状态'},
				{field : 'dept_name', width : 100, align : 'center', title : '部门'},
				{field : 'role_name', width : 100, align : 'center', title : '角色类型'}
				
	        ] ],  
	    });
	}else{
		$("#staffList").datagrid({  
	        title:'机构列表', singleSelect:false, collapsible:true, pagination:true, rownumbers:true, method:'get',
			nowrap:true, toolbar:"toolbar_staff", url:'staff/search_staff_by_'+name+'?searchValue='+value,
			loadMsg:'数据加载中......', fitColumns:true,//允许表格自动缩放,以适应父容器
	        columns : [ [ 
	             	{field : 'ck', checkbox:true },
				{field : 'id', width : 100, align:'center', title : '员工编号'},
				{field : 'username', width : 100, align : 'center', title : '姓名'},
				{field : 'sex', width : 100, align : 'center', title : '性别'},
				{field : 'locked', width : 100, align : 'center', title : '状态'},
				{field : 'dept_name', width : 100, align : 'center', title : '部门'},
				{field : 'role_name', width : 100, align : 'center', title : '角色类型'}
	        ] ],  
	    });
	}
}
	

	//根据index拿到该行值
	function onstaffClickRow(index) {
		var rows = $('#staffList').datagrid('getRows');
		return rows[index];
		
	}
	
	
    function getstaffSelectionsIds(){
    	var staffList = $("#staffList");
    	var sels = staffList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(","); 
    	
    	return ids;
    }
    
    function staff_add(){
    	$.get("staff/add_judge",'',function(data){
       		if(data.msg != null){
       			$.messager.alert('提示', data.msg);
       		}else{
       			$("#staffAddWindow").window("open");
       		}
       	});
    }
    
    function staff_edit(){
    	$.get("staff/edit_judge",'',function(data){
       		if(data.msg != null){
       			$.messager.alert('提示', data.msg);
       		}else{
       			var ids = getstaffSelectionsIds();
               	if(ids.length == 0){
               		$.messager.alert('提示','必须选择一个机构才能编辑!');
               		return ;
               	}
               	if(ids.indexOf(',') > 0){
               		$.messager.alert('提示','只能选择一个机构!');
               		return ;
               	}
               	
               	$("#staffEditWindow").window({
               		onLoad :function(){
               			//回显数据
               			var data = $("#staffList").datagrid("getSelections")[0];
               			
               			$("#staffEditWindow").form("load", data);
               		}
               	}).window("open");
       		}
       	});
    }
    
    function staff_delete(){
    	$.get("staff/delete_judge",'',function(data){
      		if(data.msg != null){
      			$.messager.alert('提示', data.msg);
      		}else{
      			var ids = getstaffSelectionsIds();
              	if(ids.length == 0){
              		$.messager.alert('提示','未选中机构!');
              		return ;
              	}
              	$.messager.confirm('确认','确定删除ID为 '+ids+' 的机构吗？',function(r){
              	    if (r){
              	    	var params = {"ids":ids};
                      	$.post("staff/delete_batch",params, function(data){
                  			if(data.status == 200){
                  				$.messager.alert('提示','删除机构成功!',undefined,function(){
                  					$("#staffList").datagrid("reload");
                  				});
                  			}
                  		});
              	    }
              	});
      		}
      	});
    }
    
    function staff_reload(){
    	$("#staffList").datagrid("reload");
    }
</script>