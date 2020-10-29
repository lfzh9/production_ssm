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
import com.megagao.production.ssm.domain.Branch;
import com.megagao.production.ssm.domain.Dept;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.service.DeptService;

@Controller
@RequestMapping("/dept")
public class DeptController extends CommonController{
	@Autowired
	private DeptService deptService;
	
	@RequestMapping("/find")
	public String find() throws Exception{
		return "dept_list";                                                                                                                                                                                                                                                                                                 
	}
	@RequestMapping("/get_data")
	@ResponseBody
	public List<Dept> getData() throws Exception{
		return deptService.find();
	}
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getList(Integer page, Integer rows) throws Exception{
	
		EUDataGridResult result = deptService.getList(page, rows);
		return result;
	}
	
	@RequestMapping("/add")
	public String add() throws Exception{
		return "dept_add";
	}
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	@ResponseBody
	private CustomResult insert(@Valid Dept dept, BindingResult bindingResult) throws Exception {
		
		CustomResult result;
		
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			
			return CustomResult.build(100, fieldError.getDefaultMessage());
		}
		if(deptService.get(dept.getDept_id()) != null){
			result = new CustomResult(0, "该部门编号已经存在，请更换部门编号！", null);
		}else{
			log("部门：添加数据:{"+dept+"}");
			result = deptService.insert(dept);
		}
		return result;
	}
	
	@RequestMapping("/edit")
	public String edit() throws Exception{
		return "dept_edit";
	}
	
	
	@RequestMapping(value="/update_all")
	@ResponseBody
	private CustomResult updateAll(@Valid Dept dept, BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			return CustomResult.build(100, fieldError.getDefaultMessage());
		}
		log("部门：修改数据:{"+dept+"}");
		return deptService.updateAll(dept);
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	private CustomResult delete(String dept_id) throws Exception {
		CustomResult result = deptService.delete(dept_id);
		return result;
	}
	
	@RequestMapping(value="/delete_dept")
	@ResponseBody
	private CustomResult deleteBatch(String[] ids) throws Exception {
		CustomResult result = deptService.deleteBatch(ids);
		log("部门：删除数据:{"+ids+"}");
		return result;
	}
	
	
	
	//根据订单id查找
	@RequestMapping("/search_dept_by_id")
	@ResponseBody
	public EUDataGridResult searchBranchById(Integer page, Integer rows, String searchValue) throws Exception{
		EUDataGridResult result = deptService.searchDeptById(page, rows, searchValue);
		log("部门：查询数据:{部门编号"+searchValue+"}");
		return result;
	}
	//根据订单id查找
	@RequestMapping("/search_dept_by_name")
	@ResponseBody
	public EUDataGridResult searchDeptByName(Integer page, Integer rows, String searchValue) throws Exception{
		
		EUDataGridResult result = deptService.searchDeptByName(page, rows, searchValue);
		log("部门：查询数据:{部门名称："+searchValue+"}");
		return result;
	}

	
}