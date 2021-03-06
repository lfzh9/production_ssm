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
import com.megagao.production.ssm.domain.Staff;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.service.StaffService;

@Controller
@RequestMapping("/staff")
public class StaffController extends CommonController{
	
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
		if(staffService.get(staff.getId()) != null){
			result = new CustomResult(0, "该员工编号已经存在，请更换员工编号！", null);
		}else{
			log("员工：添加数据:{"+staff+"}");
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
		log("员工：修改数据:{"+staff+"}");
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
		log("员工：删除数据:{"+ids+"}");
	return result;
	}
	
	
	
	//根据订单id查找
	@RequestMapping("/search_staff_by_id")
	@ResponseBody
	public EUDataGridResult searchStaffById(Integer page, Integer rows, String searchValue) throws Exception{
		EUDataGridResult result = staffService.searchStaffById(page, rows, searchValue);
		log("员工：查询数据:{员工编号："+searchValue+"}");
		return result;
	}
	//根据订单id查找
	@RequestMapping("/search_staff_by_username")
	@ResponseBody
	public EUDataGridResult searchStaffByUsername(Integer page, Integer rows, String searchValue) throws Exception{
		 
		EUDataGridResult result = staffService.searchStaffByUsername(page, rows, searchValue);
		log("员工：查询数据:{员工名字："+searchValue+"}");
		return result;
	}
	
	
}
