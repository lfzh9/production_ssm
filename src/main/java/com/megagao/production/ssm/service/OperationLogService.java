package com.megagao.production.ssm.service;

import java.util.List;

import com.megagao.production.ssm.domain.OperationLog;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;

public interface OperationLogService {
	
	List<OperationLog> find() ;

	EUDataGridResult getList(int page, int rows) throws Exception;

	EUDataGridResult searchOperationLogById(Integer page, Integer rows,
			int searchValue) throws Exception;

	EUDataGridResult searchOperationLogByDate(Integer page, Integer rows,
			String searchValue) throws Exception;

	EUDataGridResult searchOperationLogByName(Integer page, Integer rows,
			String searchValue) throws Exception;


	CustomResult insert(OperationLog operationLog)throws Exception;



	CustomResult delete(int id);

	CustomResult deleteBatch(int[] ids);
}
