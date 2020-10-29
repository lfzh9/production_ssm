package com.megagao.production.ssm.service;

import java.util.List;

import com.megagao.production.ssm.domain.Branch;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;

public interface BranchService {
	
	List<Branch> find();

	EUDataGridResult getList(int page, int rows) throws Exception;

	EUDataGridResult searchBranchById(Integer page, Integer rows,
			String searchValue) throws Exception;

	EUDataGridResult searchBranchByShortName(Integer page, Integer rows,
			String searchValue) throws Exception;

	EUDataGridResult searchBranchByName(Integer page, Integer rows,
			String searchValue) throws Exception;


	CustomResult insert(Branch branch)throws Exception;

	Branch get(String id)throws Exception;

	CustomResult updateAll(Branch branch);

	CustomResult delete(String id);

	CustomResult deleteBatch(String[] ids);
}
