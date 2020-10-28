package com.megagao.production.ssm.service;

import java.util.List;

import com.megagao.production.ssm.domain.Branch;
import com.megagao.production.ssm.domain.Message;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;

public interface MessageService {
	
	List<Message> find() ;

	EUDataGridResult getList(int page, int rows) throws Exception;
	
	Message get(String id)throws Exception;
	
	EUDataGridResult searchMessageByTitle(Integer page, Integer rows,
			String searchValue) throws Exception;

	EUDataGridResult searchMessageByType(Integer page, Integer rows,
			String searchValue) throws Exception;

	EUDataGridResult searchMessageByPerson(Integer page, Integer rows,
			String searchValue) throws Exception;
	
	EUDataGridResult searchMessageByTime(Integer page, Integer rows,
			String searchValue) throws Exception;


	CustomResult insert(Message message)throws Exception;
	
	CustomResult updateAll(Message message);

	CustomResult delete(String id);

	CustomResult deleteBatch(String[] ids);
	
	

	
}
