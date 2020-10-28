package com.megagao.production.ssm.service;

import java.util.List;

import com.megagao.production.ssm.domain.MySchedule;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;

public interface MyScheduleService {
	
	List<MySchedule> find() ;

	EUDataGridResult getList(int page, int rows) throws Exception;
	
	MySchedule get(String id)throws Exception;
	
	EUDataGridResult searchMyScheduleByTitle(Integer page, Integer rows,
			String searchValue) throws Exception;

	EUDataGridResult searchMyScheduleByType(Integer page, Integer rows,
			String searchValue) throws Exception;

	EUDataGridResult searchMyScheduleByPerson(Integer page, Integer rows,
			String searchValue) throws Exception;
	
	EUDataGridResult searchMyScheduleByStartTime(Integer page, Integer rows,
			String searchValue) throws Exception;

	CustomResult insert(MySchedule mySchedule)throws Exception;
	
	CustomResult updateAll(MySchedule mySchedule);

	CustomResult delete(String id);

	CustomResult deleteBatch(String[] ids);
	
	

	
}
