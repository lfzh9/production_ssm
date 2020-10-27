package com.megagao.production.ssm.mapper;

import java.util.List;

import com.megagao.production.ssm.domain.LoginLog;

public interface LoginLogMapper {
	public List<LoginLog> find();
	public List<LoginLog> searchLoginLogById(int id);
	public List<LoginLog> searchLoginLogByDate(String date);
	public List<LoginLog> searchLoginLogByName(String name);
	public int insert(LoginLog loginLog)throws Exception;
	public int deleteByPrimaryKey(int id);
	public int deleteBatch(int[] ids);

	
}
