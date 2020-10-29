package com.megagao.production.ssm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.megagao.production.ssm.common.CommonController;
import com.megagao.production.ssm.domain.Mynote;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.service.MynoteService;

@Controller
@RequestMapping("/mynote")
public class MynoteController extends CommonController{
	@Autowired
	private MynoteService mynoteService;
	
	@RequestMapping("/find")
	public String find() throws Exception{
		return "mynote_list";                                                                                                                                                                                                                                                                                                 
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getList(Integer page, Integer rows) throws Exception{
		EUDataGridResult result = mynoteService.getList(page, rows);
		return result;
	}
	
	@RequestMapping("/add")
	public String add() throws Exception{
		return "mynote_add";
	}
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	@ResponseBody
	private CustomResult insert(@Valid Mynote mynote, BindingResult bindingResult) throws Exception {
		CustomResult result;
		System.out.println(mynote.getId());
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			System.out.println(fieldError.getDefaultMessage());
			return CustomResult.build(100, fieldError.getDefaultMessage());
		}
		if(mynoteService.get(mynote.getId()) != null){
			result = new CustomResult(0, "该便签编号已经存在，请更换便签编号！", null);
		}else{
			log("添加便签：{"+mynote+"}");
			result = mynoteService.insert(mynote);
		}
		return result;
	}
	
	
	@RequestMapping("/edit")
	public String edit() throws Exception{
		return "mynote_edit";
	}
	
	
	@RequestMapping(value="/update_all")
	@ResponseBody
	private CustomResult updateAll(@Valid Mynote mynote, BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			return CustomResult.build(100, fieldError.getDefaultMessage());
		}
		log("修改便签：{"+mynote+"}");
		return mynoteService.updateAll(mynote);
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	private CustomResult delete(String id) throws Exception {
		CustomResult result = mynoteService.delete(id);
		return result;
	}
	
	@RequestMapping(value="/delete_batch")
	@ResponseBody
	private CustomResult deleteBatch(String[] ids) throws Exception {
		CustomResult result = mynoteService.deleteBatch(ids);
		String str = null;
		for (String id : ids) {
			str=id+",";
		}
		log("删除便签：{" + str +"}");
		return result;
	}
	
	
	
	//根据订单id查找
	@RequestMapping("/search_mynote_by_title")
	@ResponseBody
	public EUDataGridResult searchMynoteByTitle(Integer page, Integer rows, String searchValue) throws Exception{
		searchValue = new String(searchValue.getBytes("iso8859-1"),"utf-8");
		EUDataGridResult result = mynoteService.searchMynoteByTitle(page, rows, searchValue);
	
		log("查询便签：{标题" + searchValue +"}");
		return result;
	}
	
	//根据订单id查找
	@RequestMapping("/search_mynote_by_person")
	@ResponseBody
	public EUDataGridResult searchMynoteByPerson(Integer page, Integer rows, String searchValue) throws Exception{
		searchValue = new String(searchValue.getBytes("iso8859-1"),"utf-8");
		EUDataGridResult result = mynoteService.searchMynoteByPerson(page, rows, searchValue);
		log("查询便签：{发布人" + searchValue +"}");
		return result;
	}
	
	//根据订单id查找
		@RequestMapping("/search_mynote_by_time")
		@ResponseBody
		public EUDataGridResult searchMynoteByStartTime(Integer page, Integer rows, String searchValue) throws Exception{
			searchValue = new String(searchValue.getBytes("iso8859-1"),"utf-8");
			EUDataGridResult result = mynoteService.searchMynoteByTime(page, rows, searchValue);
			log("查询便签：{" + searchValue +"}");
			return result;
		}
		
	
	
	
	
}
