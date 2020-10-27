package com.megagao.production.ssm.mapper;

import java.util.List;

import com.megagao.production.ssm.domain.OperationLog;

public interface OperationLogMapper {
	public List<OperationLog> find();
	public List<OperationLog> searchOperationLogById(int id);
	public List<OperationLog> searchOperationLogByDate(String date);
	public List<OperationLog> searchOperationLogByName(String name);
	public int insert(OperationLog operationLog)throws Exception;
	public int deleteByPrimaryKey(int id);
	public int deleteBatch(int[] ids);

	
}
