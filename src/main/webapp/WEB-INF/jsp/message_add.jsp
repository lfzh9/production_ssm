<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">

<link href="css/uploadfile.css" rel="stylesheet"> 
<script src="js/jquery.uploadfile.js"></script>
<script src="js/malsup.github.iojquery.form.js"></script>

<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>

<div style="padding:10px 10px 10px 10px">
	<form id="messageAddForm" class="messageForm" method="post">
	    <table cellpadding="3" >
	    <tr>
	            <td>编号:</td>
	            <td>
	            	<input class="easyui-textbox" type="text" name="id" data-options="required:true"></input>
	            </td>
	        </tr>
	        <tr>
	            <td>消息标题:</td>
	            <td>
	            	<input id="institution" class="easyui-textbox"  type="text" name="title" 
    					data-options="required:true" />
	            </td>
	        </tr>
	        <tr>
	            <td>消息内容:</td>
	            <td>
	            	<input id="simple" class="easyui-textbox" name="text"  type="text"
    					/>
    			</td>  
	        </tr>
	        <tr>
	            <td>消息类型:</td>
	            <td>
	            	<input id="simple" class="easyui-textbox" name="type"  type="text"
    					/>
    			</td>  
	        </tr>
	        <tr>
	            <td>发布人:</td>
	            <td>
	            	<input id="simple" class="easyui-textbox" name="person"  type="text" value= "${login_user }"  readonly="readonly"
    					/>
    					<span style="color:red"> * 默认为登录用户</span>
    			</td>  
	        </tr>
	        <tr>
	            <td>发布时间:</td>
	            <td>
	            	
    					<input class="easyui-datetimebox" name="time" data-options="required:true,showSeconds:true"
						   value="date.format('yyyy-MM-dd hh:mm:ss')" style="width:150px" editable="fasle">
    			</td>  
	        </tr>
	        
	       
	    </table>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitmessageAddForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearmessageADDForm()">重置</a>
	</div>
</div>
<script type="text/javascript">
	//提交表单
	function submitmessageAddForm(){
		//有效性验证
		if(!$('#messageAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		
		//ajax的post方式提交表单
		//$("#messageAddForm").serialize()将表单序列号为key-value形式的字符串
		$.post("message/insert",$("#messageAddForm").serialize(), function(data){
			
			if(data.status == 200){
				$.messager.alert('提示','新增消息成功!');
				clearmessageADDForm();
				$("#messageAddWindow").window('close');
				$("#messageList").datagrid("reload");
			}else{
				$.messager.alert('提示',data.msg);
				alert(data.status);
				
			}
		});
	}
	
	function clearmessageADDForm(){
		$('#messageAddForm').form('reset');
	}
</script>

