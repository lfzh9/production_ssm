<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">

<link href="css/uploadfile.css" rel="stylesheet"> 
<script src="js/jquery.uploadfile.js"></script>
<script src="js/malsup.github.iojquery.form.js"></script>

<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="pediting:10px 10px 10px 10px">
	<form id="deptEditForm" class="deptForm" method="post">
	    <table cellpediting="3" >
	   	 <tr>
	            <td>部门编号:</td>
	            <td>
	            	<input id="institution" class="easyui-textbox"  type="text" name="dept_id"  readonly="readonly"
    					data-options="required:true" />
	            </td>
	        </tr>
	        <tr>
	            <td>部门名称:</td>
	            <td>
	            	<input id="institution" class="easyui-textbox"  type="text" name="dept_name"  readonly="readonly"
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
	<div style="pediting:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitDeptEditForm()">提交</a>
	</div>
</div>
<script type="text/javascript">
	
	
	function submitDeptEditForm(){
		if(!$('#deptEditForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		
		$.post("dept/update_all",$("#deptEditForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','修改订单成功!','info',function(){
					$("#deptEditWindow").window('close');
					$("#deptList").datagrid("reload");
				});
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
	}
</script>
