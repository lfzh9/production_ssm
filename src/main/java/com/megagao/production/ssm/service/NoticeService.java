package com.megagao.production.ssm.service;

import java.util.List;

import com.megagao.production.ssm.domain.Notice;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;

public interface NoticeService {
	
	List<Notice> find() ;

	EUDataGridResult getList(int page, int rows) throws Exception;
	
	Notice get(String id)throws Exception;
	
	

	
}
