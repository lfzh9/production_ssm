package com.megagao.production.ssm.service;

import java.util.List;

import com.megagao.production.ssm.domain.LoginLog;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;

public interface LoginLogService {
	
	List<LoginLog> find() ;

	EUDataGridResult getList(int page, int rows) throws Exception;

	EUDataGridResult searchLoginLogById(Integer page, Integer rows,
			int searchValue) throws Exception;

	EUDataGridResult searchLoginLogByDate(Integer page, Integer rows,
			String searchValue) throws Exception;

	EUDataGridResult searchLoginLogByName(Integer page, Integer rows,
			String searchValue) throws Exception;


	CustomResult insert(LoginLog loginLog)throws Exception;



	CustomResult delete(int id);

	CustomResult deleteBatch(int[] ids);
}
