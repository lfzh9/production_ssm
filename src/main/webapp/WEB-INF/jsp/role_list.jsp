<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<table class="easyui-datagrid" id="loginLogList" title="角色列表" data-options="singleSelect:false,collapsible:true,
		pagination:true,rownumbers:true,url:'loginLog/list',method:'get',pageSize:10,fitColumns:true,toolbar:toolbar_loginLog">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'loginLogId',align:'center',width:150">日志编号</th>
            <th data-options="field:'loginLogName',align:'center',width:150">登录用户</th>
            <th data-options="field:'loginLogTime',align:'center',width:150">登录时间</th>
            <th data-options="field:'loginLogIP',align:'center',width:150">IP</th>
        </tr>
    </thead>
</table>

<div  id="toolbar_loginLog" style=" height: 22px; padding: 3px 11px; background: #fafafa;">  
	
	<c:forEach items="${sessionScope.sysPermissionList}" var="per" >
		<c:if test="${per=='loginLog:delete' }" >
		    <div style="float: left;">  
		        <a href="#" class="easyui-linkbutton" plain="true" icon="icon-cancel" onclick="loginLog_delete()">删除</a>  
		    </div>  
		</c:if>
	</c:forEach>
	
	<div class="datagrid-btn-separator"></div>  
	
	<div style="float: left;">  
		<a href="#" class="easyui-linkbutton" plain="true" icon="icon-reload" onclick="loginLog_reload()">刷新</a>  
	</div>  
	
    <div id="search_loginLog" style="float: right;">
        <input id="search_text_loginLog" class="easyui-searchbox"  
            data-options="searcher:doSearch_loginLog,prompt:'请输入...',menu:'#menu_loginLog'"  
            style="width:250px;vertical-align: middle;">
        </input>
        <div id="menu_loginLog" style="width:120px"> 
			<div data-options="name:'loginLogId'">日志编号</div> 
			<div data-options="name:'loginLogName'">登录用户</div>
		</div>     
    </div>  
</div>  

<div id="loginLogEditWindow" class="easyui-window" title="编辑角色" data-options="modal:true,closed:true,resizable:true,
	iconCls:'icon-save',href:'loginLog/edit'" style="width:45%;height:60%;padding:10px;">
</div>
<div id="loginLogAddWindow" class="easyui-window" title="添加角色" data-options="modal:true,closed:true,resizable:true,
	iconCls:'icon-save',href:'loginLog/add'" style="width:45%;height:60%;padding:10px;">
</div>

<div id="permissionWindow" class="easyui-window" title="权限管理" data-options="modal:true,closed:true,resizable:true,
	iconCls:'icon-save',href:'loginLog/permission'" style="width:45%;height:60%;padding:10px;">
</div>
<script>
function doSearch_loginLog(value,name){ //用户输入用户名,点击搜素,触发此函数  
	if(value == null || value == ''){
		
		$("#loginLogList").datagrid({
	        title:'角色列表', singleSelect:false, collapsible:true, pagination:true, rownumbers:true, method:'get',
			nowrap:true, toolbar:"toolbar_loginLog", url:'loginLog/list', method:'get', loadMsg:'数据加载中......',
			fitColumns:true,//允许表格自动缩放,以适应父容器
	        columns : [ [ 
				{field : 'ck', checkbox:true },
				{field : 'loginLogId', width : 150, align:'center', title : '角色编号'},
				{field : 'loginLogName', width : 150, align : 'center', title : '角色名'},
				{field : 'permission', width : 150, align : 'center', title : '权限', formatter:formatPermission},
				{field : 'available', width : 150, title : '状态', align:'center', formatter:formatRoleStatus},
	        ] ],  
	    });
	}else{
		$("#loginLogList").datagrid({  
	        title:'角色列表', singleSelect:false, collapsible:true, pagination:true, rownumbers:true, method:'get',
			nowrap:true, toolbar:"toolbar_loginLog", url:'loginLog/search_loginLog_by_'+name+'?searchValue='+value,
			loadMsg:'数据加载中......', fitColumns:true,//允许表格自动缩放,以适应父容器
	        columns : [ [ 
				{field : 'ck', checkbox:true },
				{field : 'loginLogId', width : 150, align:'center', title : '角色编号'},
				{field : 'loginLogName', width : 150, align : 'center', title : '角色名'},
				{field : 'permission', width : 150, align : 'center', title : '权限', formatter:formatPermission},
				{field : 'available', width : 150, title : '状态', align:'center', formatter:formatRoleStatus},
	        ] ],  
	    });
	}
}


	
	

    
    function loginLog_delete(){
    	$.get("loginLog/delete_judge",'',function(data){
      		if(data.msg != null){
      			$.messager.alert('提示', data.msg);
      		}else{
      			var ids = getRoleSelectionsIds();
            	if(ids.length == 0){
            		$.messager.alert('提示','未选中角色!');
            		return ;
            	}
            	$.messager.confirm('确认','确定删除ID为 '+ids+' 的角色吗？',function(r){
            	    if (r){
            	    	var params = {"ids":ids};
                    	$.post("loginLog/delete_batch",params, function(data){
                			if(data.status == 200){
                				$.messager.alert('提示','删除角色成功!',undefined,function(){
                					$("#loginLogList").datagrid("reload");
                				});
                			}
                		});
            	    }
            	});
      		}
      	});
    }
    
    function loginLog_reload(){
    	$("#loginLogList").datagrid("reload");
    }
</script>