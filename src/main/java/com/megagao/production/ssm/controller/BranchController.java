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
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.service.BranchService;

@Controller
@RequestMapping("/branch")
public class BranchController extends CommonController{

	@Autowired
	private BranchService branchService;
	
	@RequestMapping("/find")
	public String find() throws Exception{
		return "branch_list";                                                                                                                                                                                                                                                                                                 
	}
	@RequestMapping("/get_data")
	@ResponseBody
	public List<Branch> getData() throws Exception{
		return branchService.find();
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getList(Integer page, Integer rows) throws Exception{
		EUDataGridResult result = branchService.getList(page, rows);
		return result;
	}
	
	@RequestMapping("/add")
	public String add() throws Exception{
		return "branch_add";
	}
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	@ResponseBody
	private CustomResult insert(@Valid Branch branch, BindingResult bindingResult) throws Exception {
		CustomResult result;
//		System.out.println(branch.getId());
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			System.out.println(fieldError.getDefaultMessage());
			return CustomResult.build(100, fieldError.getDefaultMessage());
		}
		if(branchService.get(branch.getId()) != null){
			result = new CustomResult(0, "该机构编号已经存在，请更换机构编号！", null);
		}else{
			log("机构：添加数据:{"+branch+"}");
			result = branchService.insert(branch);
			
		}
		return result;
	}
	
	@RequestMapping("/edit")
	public String edit() throws Exception{
		return "branch_edit";
	}
	
	
	@RequestMapping(value="/update_all")
	@ResponseBody
	private CustomResult updateAll(@Valid Branch branch, BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			return CustomResult.build(100, fieldError.getDefaultMessage());
		}
		log("机构：修改数据:{"+branch+"}");
		return branchService.updateAll(branch);
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	private CustomResult delete(String id) throws Exception {
		CustomResult result = branchService.delete(id);
		return result;
	}
	
	@RequestMapping(value="/delete_batch")
	@ResponseBody
	private CustomResult deleteBatch(String[] ids) throws Exception {
		CustomResult result = branchService.deleteBatch(ids);
		log("机构：删除数据:{"+ids+"}");
		return result;
	}
	
	
	
	//根据订单id查找
	@RequestMapping("/search_branch_by_id")
	@ResponseBody
	public EUDataGridResult searchBranchById(Integer page, Integer rows, String searchValue) throws Exception{
		EUDataGridResult result = branchService.searchBranchById(page, rows, searchValue);
		log("机构：查询数据:{机构编号："+searchValue+"}");
		return result;
	}
	//根据订单id查找
	@RequestMapping("/search_branch_by_name")
	@ResponseBody
	public EUDataGridResult searchBranchByName(Integer page, Integer rows, String searchValue) throws Exception{
		EUDataGridResult result = branchService.searchBranchByName(page, rows, searchValue);
		log("机构：查询数据:{机构名称："+searchValue+"}");
		return result;
	}
	//根据订单id查找
	@RequestMapping("/search_branch_by_short_name")
	@ResponseBody
	public EUDataGridResult searchBranchByShortName(Integer page, Integer rows, String searchValue) throws Exception{
		EUDataGridResult result = branchService.searchBranchByShortName(page, rows, searchValue);
		log("机构：查询数据:{机构简称："+searchValue+"}");
		return result;
	}
	
}
