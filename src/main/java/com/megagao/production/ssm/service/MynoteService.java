package com.megagao.production.ssm.service;

import java.util.List;

import com.megagao.production.ssm.domain.Mynote;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;

public interface MynoteService {
	
	List<Mynote> find() ;

	EUDataGridResult getList(int page, int rows) throws Exception;
	
	Mynote get(String id)throws Exception;
	
	EUDataGridResult searchMynoteByTitle(Integer page, Integer rows,
			String searchValue) throws Exception;

	EUDataGridResult searchMynoteByPerson(Integer page, Integer rows,
			String searchValue) throws Exception;
	
	EUDataGridResult searchMynoteByTime(Integer page, Integer rows,
			String searchValue) throws Exception;

	CustomResult insert(Mynote mynote)throws Exception;
	
	CustomResult updateAll(Mynote mynote);

	CustomResult delete(String id);

	CustomResult deleteBatch(String[] ids);
	
	

	
}
