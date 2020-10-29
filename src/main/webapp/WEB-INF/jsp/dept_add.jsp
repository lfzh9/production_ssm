<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">

<link href="css/uploadfile.css" rel="stylesheet"> 
<script src="js/jquery.uploadfile.js"></script>
<script src="js/malsup.github.iojquery.form.js"></script>

<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>

<div style="padding:10px 10px 10px 10px">
	<form id="deptAddForm" class="deptForm" method="post">
	    <table cellpadding="3" >
	 <tr>
	            <td>部门编号:</td>
	            <td>
	            	<input id="institution" class="easyui-textbox"  type="text" name="dept_id" 
    					data-options="required:true" />
	            </td>
	        </tr>
	        <tr>
	            <td>部门名称:</td>
	            <td>
	            	<input id="institution" class="easyui-textbox"  type="text" name="dept_name" 
    					data-options="required:true" />
	            </td>
	        </tr>
	        <tr>
	            <td>移动电话:</td>
	            <td>
	            	<input id="simple" class="easyui-textbox" name="dept_mtlp"  type="text"
    					/>
    			</td>  
	        </tr>
	         <tr>
	            <td>部门电话:</td>
	            <td>
	            	<input id="simple" class="easyui-textbox" name="dept_tlp"  type="text"
    					/>
    			</td>  
	        </tr>
	         <tr>
	            <td>部门负责人:</td>
	            <td>
	            	<input id="simple" class="easyui-textbox" name="dept_char"  type="text"
    					/>
    			</td>  
	        </tr>
	          <tr>
	            <td>负责机构:</td>
	            <td>
	      
	            	<input class="easyui-combobox" name="dept_nameout" 
    					data-options="valueField:'name',textField:'name',
    						url:'branch/get_data', editable:false, required:true" />
    			
    			</td>  
	        </tr>
	        
	       
	    </table>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitDeptAddForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearDeptADDForm()">重置</a>
	</div>
</div>
<script type="text/javascript">
	//提交表单
	function submitDeptAddForm(){
		//有效性验证
		if(!$('#deptAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		
		//ajax的post方式提交表单
		//$("#deptAddForm").serialize()将表单序列号为key-value形式的字符串
		$.post("dept/insert",$("#deptAddForm").serialize(), function(data){
			
			if(data.status == 200){
				$.messager.alert('提示','新增订单成功!');
				clearDeptADDForm();
				$("#deptAddWindow").window('close');
				$("#deptList").datagrid("reload");
			}else{
				$.messager.alert('提示',data.msg);
				alert(data.status);
				
			}
		});
	}
	
	function clearDeptADDForm(){
		$('#deptAddForm').form('reset');
	}
</script>

