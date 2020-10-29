package com.megagao.production.ssm.service;

import java.util.List;

import com.megagao.production.ssm.domain.Dept;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;

public interface DeptService {
	
	List<Dept> find() ;

	EUDataGridResult getList(int page, int rows) throws Exception;

	EUDataGridResult searchDeptById(Integer page, Integer rows,
			String searchValue) throws Exception;

	EUDataGridResult searchDeptByName(Integer page, Integer rows,
			String searchValue) throws Exception;


	CustomResult insert(Dept dept)throws Exception;

	Dept get(String dept_id)throws Exception;

	CustomResult updateAll(Dept dept);

	CustomResult delete(String dept_id);

	CustomResult deleteBatch(String[] ids);
}
