package com.megagao.production.ssm.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.megagao.production.ssm.domain.LoginLog;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.mapper.LoginLogMapper;
import com.megagao.production.ssm.service.LoginLogService;

@Service
public class LoginLogServiceImpl implements LoginLogService {
	@Autowired
	private LoginLogMapper loginLogMapper;

	@Override
	public List<LoginLog> find() {

		return loginLogMapper.find();
	}

	@Override
	public EUDataGridResult getList(int page, int rows) throws Exception {

		// 分页处理
		PageHelper.startPage(page, rows);
		List<LoginLog> list = loginLogMapper.find();
		
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<LoginLog> pageInfo = new PageInfo<LoginLog>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public EUDataGridResult searchLoginLogById(Integer page, Integer rows,
			int searchValue) throws Exception {
		// 分页处理
		PageHelper.startPage(page, rows);
		List<LoginLog> list = loginLogMapper.searchLoginLogById(searchValue);
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<LoginLog> pageInfo = new PageInfo<LoginLog>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public EUDataGridResult searchLoginLogByDate(Integer page, Integer rows,
			String searchValue) throws Exception {
		// 分页处理
		PageHelper.startPage(page, rows);
		
		
		List<LoginLog> list = loginLogMapper.searchLoginLogByDate(searchValue);
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<LoginLog> pageInfo = new PageInfo<LoginLog>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public EUDataGridResult searchLoginLogByName(Integer page, Integer rows,
			String searchValue) throws Exception {
		// 分页处理
		PageHelper.startPage(page, rows);
		List<LoginLog> list = loginLogMapper.searchLoginLogByName(searchValue);
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<LoginLog> pageInfo = new PageInfo<LoginLog>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public CustomResult insert(LoginLog loginLog) throws Exception {
		int i = loginLogMapper.insert(loginLog);
		if (i > 0) {
			return CustomResult.ok();
		} else {
			return CustomResult.build(101, "新增机构失败");
		}
	}


	@Override
	public CustomResult delete(int id) {
		int i = loginLogMapper.deleteByPrimaryKey(id);
		if(i>0){
			return CustomResult.ok();
		}else{
			return null;
		}
	}

	@Override
	public CustomResult deleteBatch(int[] ids) {
		int i = loginLogMapper.deleteBatch(ids);
		if(i>0){
			return CustomResult.ok();
		}else{
			return null;
		}
	}

}
