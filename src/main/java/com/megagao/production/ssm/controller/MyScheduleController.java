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
import com.megagao.production.ssm.domain.MySchedule;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.service.MyScheduleService;

@Controller
@RequestMapping("/mySchedule")
public class MyScheduleController extends CommonController{
	@Autowired
	private MyScheduleService myScheduleService;
	
	@RequestMapping("/find")
	public String find() throws Exception{
		return "mySchedule_list";                                                                                                                                                                                                                                                                                                 
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getList(Integer page, Integer rows) throws Exception{
		EUDataGridResult result = myScheduleService.getList(page, rows);
		return result;
	}
	
	@RequestMapping("/add")
	public String add() throws Exception{
		return "mySchedule_add";
	}
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	@ResponseBody
	private CustomResult insert(@Valid MySchedule mySchedule, BindingResult bindingResult) throws Exception {
		CustomResult result;
		System.out.println(mySchedule.getId());
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			System.out.println(fieldError.getDefaultMessage());
			return CustomResult.build(100, fieldError.getDefaultMessage());
		}
		if(myScheduleService.get(mySchedule.getId()) != null){
			result = new CustomResult(0, "该日程编号已经存在，请更换日程编号！", null);
		}else{
			log("添加日程：{"+mySchedule+"}");
			result = myScheduleService.insert(mySchedule);
		}
		return result;
	}
	
	
	@RequestMapping("/edit")
	public String edit() throws Exception{
		return "mySchedule_edit";
	}
	
	
	@RequestMapping(value="/update_all")
	@ResponseBody
	private CustomResult updateAll(@Valid MySchedule mySchedule, BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			return CustomResult.build(100, fieldError.getDefaultMessage());
		}
		log("修改日程：{"+mySchedule+"}");
		return myScheduleService.updateAll(mySchedule);
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	private CustomResult delete(String id) throws Exception {
		CustomResult result = myScheduleService.delete(id);
		return result;
	}
	
	@RequestMapping(value="/delete_batch")
	@ResponseBody
	private CustomResult deleteBatch(String[] ids) throws Exception {
		CustomResult result = myScheduleService.deleteBatch(ids);
		String str = null;
		for (String id : ids) {
			str=id+",";
		}
		log("删除日程：{" + str +"}");
		return result;
	}
	
	
	
	//根据订单id查找
	@RequestMapping("/search_mySchedule_by_title")
	@ResponseBody
	public EUDataGridResult searchMyScheduleByTitle(Integer page, Integer rows, String searchValue) throws Exception{
		searchValue = new String(searchValue.getBytes("iso8859-1"),"utf-8");
		EUDataGridResult result = myScheduleService.searchMyScheduleByTitle(page, rows, searchValue);
		log("查询日程：{日程标题："+searchValue+"}");
		return result;
	}
	//根据订单id查找
	@RequestMapping("/search_mySchedule_by_type")
	@ResponseBody
	public EUDataGridResult searchMyScheduleByType(Integer page, Integer rows, String searchValue) throws Exception{
		searchValue = new String(searchValue.getBytes("iso8859-1"),"utf-8");
		EUDataGridResult result = myScheduleService.searchMyScheduleByType(page, rows, searchValue);
		log("查询日程：{日程类型："+searchValue+"}");
		return result;
	}
	//根据订单id查找
	@RequestMapping("/search_mySchedule_by_person")
	@ResponseBody
	public EUDataGridResult searchMyScheduleByPerson(Integer page, Integer rows, String searchValue) throws Exception{
		searchValue = new String(searchValue.getBytes("iso8859-1"),"utf-8");
		EUDataGridResult result = myScheduleService.searchMyScheduleByPerson(page, rows, searchValue);
		log("查询日程：{日程发布人："+searchValue+"}");
		return result;
	}
	
	//根据订单id查找
		@RequestMapping("/search_mySchedule_by_startTime")
		@ResponseBody
		public EUDataGridResult searchMyScheduleByStartTime(Integer page, Integer rows, String searchValue) throws Exception{
			searchValue = new String(searchValue.getBytes("iso8859-1"),"utf-8");
			EUDataGridResult result = myScheduleService.searchMyScheduleByStartTime(page, rows, searchValue);
			log("查询日程：{日程开始时间："+searchValue+"}");
			return result;
		}
		
	
	
	
	
}
