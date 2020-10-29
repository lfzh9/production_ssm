<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">

<link href="css/uploadfile.css" rel="stylesheet"> 
<script src="js/jquery.uploadfile.js"></script>
<script src="js/malsup.github.iojquery.form.js"></script>

<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="pediting:10px 10px 10px 10px">
	<form id="mynoteEditForm" class="mynoteForm" method="post">
	    <table cellpediting="3" >
	    <tr>
	            <td>编号:</td>
	            <td>
	            	<input class="easyui-textbox" type="text" name="id" data-options="required:true" readonly="readonly"></input>
	            </td>
	        </tr>
	        <tr>
	            <td>标题:</td>
	            <td>
	            	<input id="institution" class="easyui-textbox"  type="text" name="title" 
    					data-options="required:true" />
	            </td>
	        </tr>
	       
	        <tr>
	            <td>内容:</td>
	            <td>
	            	<input id="simple" class="easyui-textbox" name="text"  type="text"
    					/>
    			</td>  
	        </tr>
	        
	        <tr>
	            <td>创建者:</td>
	            <td>
	            	<input id="simple" class="easyui-textbox" name="person"  type="text" readonly = "readonly" 
    					/>
    					<span style="color:red"> * 默认为登录用户</span>
    			</td>  
	        </tr>
	        <tr>
	            <td>创建时间:</td>
	            <td>
	            		<input class="easyui-datetimebox" name="time" data-options="required:true,showSeconds:true"
						   value="date.format('yyyy-MM-dd hh:mm:ss')" style="width:150px" editable="fasle">
    			</td>  
	        </tr>
	       
	    </table>
	</form>
	<div style="pediting:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitmynoteEditForm()">提交</a>
	</div>
</div>
<script type="text/javascript">
	
	
	function submitmynoteEditForm(){
		if(!$('#mynoteEditForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		
		$.post("mynote/update_all",$("#mynoteEditForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','修改便签成功!','info',function(){
					$("#mynoteEditWindow").window('close');
					$("#mynoteList").datagrid("reload");
				});
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
	}
</script>
