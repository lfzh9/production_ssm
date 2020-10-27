package com.megagao.production.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.service.OperationLogService;

/**
  * created on 2016年9月6日 
  *
  * @author  megagao
  */
@Controller
public class OperationLogController {
	@Autowired
	private OperationLogService operationLogService;
	
	@RequestMapping("/operationLog/find")
	public String find() throws Exception{
		return "operationLog_list";                                                                                                                                                                                                                                                                                                 
	}
	
	@RequestMapping("/operationLog/list")
	@ResponseBody
	public EUDataGridResult getList(Integer page, Integer rows) throws Exception{
		EUDataGridResult result = operationLogService.getList(page, rows);
		return result;
	}
	
	
	
	
	@RequestMapping(value="/operationLog/delete")
	@ResponseBody
	private CustomResult delete(int id) throws Exception {
		CustomResult result = operationLogService.delete(id);
		return result;
	}
	
	@RequestMapping(value="/operationLog/delete_batch")
	@ResponseBody
	private CustomResult deleteBatch(int[] ids) throws Exception {
		CustomResult result = operationLogService.deleteBatch(ids);
		return result;
	}
	
	
	
	//根据订单id查找
	@RequestMapping("/operationLog/search_operationLog_by_id")
	@ResponseBody
	public EUDataGridResult searchOperationLogById(Integer page, Integer rows, int searchValue) throws Exception{
		EUDataGridResult result = operationLogService.searchOperationLogById(page, rows, searchValue);
		return result;
	}
	//根据订单id查找
	@RequestMapping("/operationLog/search_operationLog_by_date")
	@ResponseBody
	public EUDataGridResult searchOperationLogByDate(Integer page, Integer rows, String searchValue) throws Exception{
		EUDataGridResult result = operationLogService.searchOperationLogByDate(page, rows, searchValue);
		return result;
	}
	//根据订单id查找
	@RequestMapping("/operationLog/search_operationLog_by_name")
	@ResponseBody
	public EUDataGridResult searchOperationLogByName(Integer page, Integer rows, String searchValue) throws Exception{
		searchValue = new String(searchValue.getBytes("iso8859-1"),"utf-8"); 
		EUDataGridResult result = operationLogService.searchOperationLogByName(page, rows, searchValue);
		return result;
	}
	
}
