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

import com.megagao.production.ssm.domain.Branch;
import com.megagao.production.ssm.domain.Staff;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.service.StaffService;

@Controller
@RequestMapping("/staff")
public class StaffController {
	
	@Autowired
	private StaffService staffService;
	
	@RequestMapping("/find")
	public String find() throws Exception{
		return "staff_list";                                                                                                                                                                                                                                                                                                 
	}
	@RequestMapping("/get_data")
	@ResponseBody
	public List<Staff> getData() throws Exception{
		return staffService.find();
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getList(Integer page, Integer rows) throws Exception{
		EUDataGridResult result = staffService.getList(page, rows);
		return result;
	}
	
	@RequestMapping("/add")
	public String add() throws Exception{
		return "staff_add";
	}
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	@ResponseBody
	private CustomResult insert(@Valid Staff staff, BindingResult bindingResult) throws Exception {
		CustomResult result;
		System.out.println(staff.getId());
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			System.out.println(fieldError.getDefaultMessage());
			return CustomResult.build(100, fieldError.getDefaultMessage());
		}
		System.out.println("111111111111111111111111111111111111111111111");
		System.out.println("111111111111111111111111111111111111111111111");
		System.out.println("111111111111111111111111111111111111111111111");
		if(staffService.get(staff.getId()) != null){
			System.out.println("3333333333333");
			result = new CustomResult(0, "该机构编号已经存在，请更换机构编号！", null);
		}else{
			System.out.println("22222222222222222222222222222222");
			System.out.println(staff);
			result = staffService.insert(staff);
			
		}
		return result;
	}
	
	@RequestMapping("/edit")
	public String edit() throws Exception{
		return "staff_edit";
	}
	@RequestMapping(value="/update_all")
	@ResponseBody
	private CustomResult updateAll(@Valid Staff staff, BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			return CustomResult.build(100, fieldError.getDefaultMessage());
		}
		return staffService.updateAll(staff);
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	private CustomResult delete(String id) throws Exception {
		CustomResult result = staffService.delete(id);
		return result;
	}
	
	@RequestMapping(value="/delete_batch")
	@ResponseBody
	private CustomResult deleteBatch(String[] ids) throws Exception {
		CustomResult result = staffService.deleteBatch(ids);
		return result;
	}
	
	
	
	//根据订单id查找
	@RequestMapping("/search_staff_by_id")
	@ResponseBody
	public EUDataGridResult searchStaffById(Integer page, Integer rows, String searchValue) throws Exception{
		EUDataGridResult result = staffService.searchStaffById(page, rows, searchValue);
		return result;
	}
	//根据订单id查找
	@RequestMapping("/search_staff_by_name")
	@ResponseBody
	public EUDataGridResult searchStaffByName(Integer page, Integer rows, String searchValue) throws Exception{
		searchValue = new String(searchValue.getBytes("iso8859-1"),"utf-8"); 
		EUDataGridResult result = staffService.searchStaffByName(page, rows, searchValue);
		return result;
	}
	
	
}
