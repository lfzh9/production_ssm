package com.megagao.production.ssm.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.megagao.production.ssm.common.CommonController;
import com.megagao.production.ssm.domain.Message;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.service.MessageService;

@Controller
@RequestMapping("/message")
public class MessageController extends CommonController{
	@Autowired
	private MessageService messageService;
	
	@RequestMapping("/find")
	public String find() throws Exception{
		return "message_list";                                                                                                                                                                                                                                                                                                 
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getList(Integer page, Integer rows) throws Exception{
		EUDataGridResult result = messageService.getList(page, rows);
		return result;
	}
	 @RequestMapping({"/getMessage"})
	  @ResponseBody
	  public List<Message> getMessage() {
	    return this.messageService.find();
	  }
	 
	@RequestMapping("/add")
	public String add() throws Exception{
		return "message_add";
	}
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	@ResponseBody
	private CustomResult insert(@Valid Message message, BindingResult bindingResult) throws Exception {
		CustomResult result;
		System.out.println(message.getId());
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			System.out.println(fieldError.getDefaultMessage());
			return CustomResult.build(100, fieldError.getDefaultMessage());
		}
		if(messageService.get(message.getId()) != null){
			result = new CustomResult(0, "该消息编号已经存在，请更换消息编号！", null);
		}else{
			log("添加消息：{"+message+"}");
			result = messageService.insert(message);
		}
		return result;
	}
	
	
	@RequestMapping("/edit")
	public String edit() throws Exception{
		return "message_edit";
	}
	
	
	@RequestMapping(value="/update_all")
	@ResponseBody
	private CustomResult updateAll(@Valid Message message, BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			return CustomResult.build(100, fieldError.getDefaultMessage());
		}
		log("修改消息：{"+message+"}");
		return messageService.updateAll(message);
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	private CustomResult delete(String id) throws Exception {
		CustomResult result = messageService.delete(id);
		return result;
	}
	
	@RequestMapping(value="/delete_batch")
	@ResponseBody
	private CustomResult deleteBatch(String[] ids) throws Exception {
		CustomResult result = messageService.deleteBatch(ids);
		String str = null;
		for (String id : ids) {
			str=id+",";
		}
		log("删除消息：{" + str +"}");
		return result;
	}
	
	
	
	//根据订单id查找
	@RequestMapping("/search_message_by_title")
	@ResponseBody
	public EUDataGridResult searchMessageByTitle(Integer page, Integer rows, String searchValue) throws Exception{
		EUDataGridResult result = messageService.searchMessageByTitle(page, rows, searchValue);
		log("查询消息：{标题：" + searchValue +"}");
		return result;
	}
	//根据订单id查找
	@RequestMapping("/search_message_by_type")
	@ResponseBody
	public EUDataGridResult searchMessageByType(Integer page, Integer rows, String searchValue) throws Exception{
		EUDataGridResult result = messageService.searchMessageByType(page, rows, searchValue);
		log("查询消息：{类型：" + searchValue +"}");
		return result;
	}
	//根据订单id查找
	@RequestMapping("/search_message_by_person")
	@ResponseBody
	public EUDataGridResult searchMessageByPerson(Integer page, Integer rows, String searchValue) throws Exception{
		EUDataGridResult result = messageService.searchMessageByPerson(page, rows, searchValue);
		log("查询消息：{发布人：" + searchValue +"}");
		return result;
	}
	//根据订单id查找
		@RequestMapping("/search_message_by_time")
		@ResponseBody
		public EUDataGridResult searchMessageByTime(Integer page, Integer rows, String searchValue) throws Exception{
			EUDataGridResult result = messageService.searchMessageByTime(page, rows, searchValue);
			log("查询消息：{时间：" + searchValue +"}");
			return result;
		}
	
}
