package com.megagao.production.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.megagao.production.ssm.domain.OperationLog;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.mapper.OperationLogMapper;
import com.megagao.production.ssm.service.OperationLogService;

@Service
public class OperationLogServiceImpl implements OperationLogService {
	@Autowired
	private OperationLogMapper operationLogMapper;

	@Override
	public List<OperationLog> find() {

		return operationLogMapper.find();
	}

	@Override
	public EUDataGridResult getList(int page, int rows) throws Exception {

		// 分页处理
		PageHelper.startPage(page, rows);
		List<OperationLog> list = operationLogMapper.find();
		
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<OperationLog> pageInfo = new PageInfo<OperationLog>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public EUDataGridResult searchOperationLogById(Integer page, Integer rows,
			int searchValue) throws Exception {
		// 分页处理
		PageHelper.startPage(page, rows);
		List<OperationLog> list = operationLogMapper.searchOperationLogById(searchValue);
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<OperationLog> pageInfo = new PageInfo<OperationLog>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public EUDataGridResult searchOperationLogByDate(Integer page, Integer rows,
			String searchValue) throws Exception {
		// 分页处理
		PageHelper.startPage(page, rows);
		
		
		List<OperationLog> list = operationLogMapper.searchOperationLogByDate(searchValue);
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<OperationLog> pageInfo = new PageInfo<OperationLog>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public EUDataGridResult searchOperationLogByName(Integer page, Integer rows,
			String searchValue) throws Exception {
		// 分页处理
		PageHelper.startPage(page, rows);
		List<OperationLog> list = operationLogMapper.searchOperationLogByName(searchValue);
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<OperationLog> pageInfo = new PageInfo<OperationLog>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public CustomResult insert(OperationLog operationLog) throws Exception {
		int i = operationLogMapper.insert(operationLog);
		if (i > 0) {
			return CustomResult.ok();
		} else {
			return CustomResult.build(101, "新增日志失败");
		}
	}


	@Override
	public CustomResult delete(int id) {
		int i = operationLogMapper.deleteByPrimaryKey(id);
		if(i>0){
			return CustomResult.ok();
		}else{
			return null;
		}
	}

	@Override
	public CustomResult deleteBatch(int[] ids) {
		int i = operationLogMapper.deleteBatch(ids);
		if(i>0){
			return CustomResult.ok();
		}else{
			return null;
		}
	}

}
