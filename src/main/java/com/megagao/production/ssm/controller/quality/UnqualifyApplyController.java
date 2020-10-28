package com.megagao.production.ssm.controller.quality;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.megagao.production.ssm.common.CommonController;
import com.megagao.production.ssm.domain.UnqualifyApply;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.service.UnqualifyService;

@Controller
@RequestMapping("/unqualify")
public class UnqualifyApplyController extends CommonController{

	@Autowired
	private UnqualifyService unqualifyService;
	
	@RequestMapping("/find")
	public String find() {
		return "unqualify_list";
	}
	
	@RequestMapping("/add")
	public String add() {
		return "unqualify_add";
	}
	
	@RequestMapping("/edit")
	public String edit() {
		return "unqualify_edit";
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page, Integer rows, UnqualifyApply unqualifyApply) throws Exception{
		EUDataGridResult result = unqualifyService.getList(page, rows, unqualifyApply);
		return result;
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	@ResponseBody
	private CustomResult insert(@Valid UnqualifyApply unqualifyApply, BindingResult bindingResult) throws Exception {
		CustomResult result;
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			return CustomResult.build(100, fieldError.getDefaultMessage());
		}
		if(unqualifyService.get(unqualifyApply.getUnqualifyApplyId()) != null){
			result = new CustomResult(0, "该不合格品申请编号已经存在，请更换！", null);
		}else{
			log("不合格申请：添加数据： {"+unqualifyApply+"}");
			result = unqualifyService.insert(unqualifyApply);
		}
		return result;
	}

	@RequestMapping(value="/update_all")
	@ResponseBody
	private CustomResult updateAll(@Valid UnqualifyApply unqualifyApply, BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			return CustomResult.build(100, fieldError.getDefaultMessage());
		}
		log("不合格申请：修改数据： {"+unqualifyApply+"}");
		return unqualifyService.updateAll(unqualifyApply);
	}
	
	@RequestMapping(value="/update_note")
	@ResponseBody
	private CustomResult updateNote(@Valid UnqualifyApply unqualifyApply, BindingResult bindingResult)
			throws Exception {
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			return CustomResult.build(100, fieldError.getDefaultMessage());
		}
		return unqualifyService.updateNote(unqualifyApply);
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	private CustomResult delete(String id) throws Exception {
		CustomResult result = unqualifyService.delete(id);
		return result;
	}
	
	@RequestMapping(value="/delete_batch")
	@ResponseBody
	private CustomResult deleteBatch(String[] ids) throws Exception {
		CustomResult result = unqualifyService.deleteBatch(ids);
		log("不合格申请：删除数据： {"+ids+"}");
		return result;
	}

	//根据不合格品id查找
	@RequestMapping("/search_unqualify_by_unqualifyId")
	@ResponseBody
	public EUDataGridResult searchUnqualifyByUnqualifyId(Integer page, Integer rows, String searchValue) 
			throws Exception{
		EUDataGridResult result = unqualifyService.searchUnqualifyByUnqualifyId(page, rows, searchValue);
		log("不合格申请：查询数据： {不合格品id :"+searchValue+"}");
		return result;
	}

	//根据产品名称查找
	@RequestMapping("/search_unqualify_by_productName")
	@ResponseBody
	public EUDataGridResult searchUnqualifyByProductName(Integer page, Integer rows, String searchValue) 
			throws Exception{
		EUDataGridResult result = unqualifyService.searchUnqualifyByProductName(page, rows, searchValue);
		log("不合格申请：查询数据： {产品名称"+searchValue+"}");
		return result;
	}
	
}
