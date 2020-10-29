<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">

<link href="css/uploadfile.css" rel="stylesheet"> 
<script src="js/jquery.uploadfile.js"></script>
<script src="js/malsup.github.iojquery.form.js"></script>

<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>

<div style="padding:10px 10px 10px 10px">
	<form id="staffAddForm" class="staffForm" method="post">
	    <table cellpadding="3" >
	 <tr>
	            <td>员工编号:</td>
	            <td>
	            	<input id="institution" class="easyui-textbox"  type="text" name="id" 
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
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitStaffAddForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearStaffADDForm()">重置</a>
	</div>
</div>
<script type="text/javascript">
	//提交表单
	alert(${staffs});
	function submitStaffAddForm(){
		//有效性验证
		if(!$('#staffAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		
		//ajax的post方式提交表单
		//$("#staffAddForm").serialize()将表单序列号为key-value形式的字符串
		$.post("staff/insert",$("#staffAddForm").serialize(), function(data){
			
			if(data.status == 200){
				$.messager.alert('提示','新增订单成功!');
				clearStaffADDForm();
				$("#staffAddWindow").window('close');
				$("#staffList").datagrid("reload");
			}else{
				$.messager.alert('提示',data.msg);
				alert(data.status);
				
			}
		});
	}
	
	function clearStaffADDForm(){
		$('#staffAddForm').form('reset');
	}
</script>

