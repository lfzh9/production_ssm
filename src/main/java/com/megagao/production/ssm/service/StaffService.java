package com.megagao.production.ssm.service;

import java.util.List;

import com.megagao.production.ssm.domain.Staff;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;

public interface StaffService {
	
	List<Staff> find();

	EUDataGridResult getList(int page, int rows) throws Exception;

	EUDataGridResult searchStaffById(Integer page, Integer rows,
			String searchValue) throws Exception;

	EUDataGridResult searchStaffByName(Integer page, Integer rows,
			String searchValue) throws Exception;


	CustomResult insert(Staff staff)throws Exception;

	Staff get(String id)throws Exception;

	CustomResult updateAll(Staff staff);

	CustomResult delete(String id);

	CustomResult deleteBatch(String[] ids);
}

