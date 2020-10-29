<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">

<link href="css/uploadfile.css" rel="stylesheet"> 
<script src="js/jquery.uploadfile.js"></script>
<script src="js/malsup.github.iojquery.form.js"></script>

<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="pediting:10px 10px 10px 10px">
	<form id="staffEditForm" class="staffForm" method="post">
	    <table cellpediting="3" >
	    	 <tr>
	            <td>员工编号:</td>
	            <td>
	            	<input id="institution" class="easyui-textbox"  type="text" name="id" readonly="readonly"
    					data-options="required:true" />
	            </td>
	        </tr>
	        <tr>
	            <td>姓名:</td>
	            <td>
	            	<input id="institution" class="easyui-textbox"  type="text" name="username" 
    					data-options="required:true" />
	            </td>
	        </tr>
	        <tr>
	            <td>状态:</td>
	            <td>
	            	
    					<select class="easyui-combobox" name="locked" panelHeight="auto" data-options="width:160,
		            		editable:false">
						<option>启用</option>
						<option>禁用</option>
					</select>
    					
    			</td>  
	        </tr>
	         <tr>
	            <td>性别:</td>
	            <td>
	            
    					<select class="easyui-combobox" name="sex" panelHeight="auto" data-options="width:160,
		            		editable:false">
						<option>男</option>
						<option>女</option>
					</select>
    			</td>  
	        </tr>
	         <tr>
	            <td>所属部门:</td>
	            <td>
	            	<input class="easyui-combobox" name="dept_name"  id="simple"
    					data-options="valueField:'dept_name',textField:'dept_name',
    						url:'dept/get_data', editable:false, required:true" />
    			</td>  
	        </tr>
	          <tr>
	            <td>角色类型:</td>
	            <td>
	      
	           <input class="easyui-combobox" name="role_name" 
    					data-options="valueField:'roleName',textField:'roleName',
    						url:'role/get_data', editable:false, required:true" />
    			
    			</td>  
	        </tr>
	       
	    </table>
	</form>
	<div style="pediting:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitStaffEditForm()">提交</a>
	</div>
</div>
<script type="text/javascript">
	
	
	function submitStaffEditForm(){
		if(!$('#staffEditForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		
		$.post("staff/update_all",$("#staffEditForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','修改订单成功!','info',function(){
					$("#staffEditWindow").window('close');
					$("#staffList").datagrid("reload");
				});
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
	}
</script>
