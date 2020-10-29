package com.megagao.production.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.megagao.production.ssm.domain.Dept;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.mapper.DeptMapper;
import com.megagao.production.ssm.service.DeptService;

@Service
public class DeptServiceImpl implements DeptService {
	@Autowired
	private DeptMapper deptMapper;

	@Override
	public List<Dept> find() {

		return deptMapper.find();
	}

	@Override
	public EUDataGridResult getList(int page, int rows) throws Exception {

		// 分页处理
		PageHelper.startPage(page, rows);
		List<Dept> list = deptMapper.find();
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<Dept> pageInfo = new PageInfo<Dept>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public EUDataGridResult searchDeptById(Integer page, Integer rows,
			String searchValue) throws Exception {
		// 分页处理
		PageHelper.startPage(page, rows);
		List<Dept> list = deptMapper.searchDeptById(searchValue);
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<Dept> pageInfo = new PageInfo<Dept>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public EUDataGridResult searchDeptByName(Integer page, Integer rows,
			String searchValue) throws Exception {
		// 分页处理
		PageHelper.startPage(page, rows);
		List<Dept> list =deptMapper.searchDeptByName(searchValue);
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<Dept> pageInfo = new PageInfo<Dept>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}



	@Override
	public CustomResult insert(Dept dept) throws Exception {
		int i = deptMapper.insert(dept);
		if (i > 0) {
			return CustomResult.ok();
		} else {
			return CustomResult.build(101, "新增部门失败");
		}
	}

	@Override
	public Dept get(String dept_id) throws Exception {
		// TODO Auto-generated method stub
		return deptMapper.loadDeptById(dept_id);
	}

	@Override
	public CustomResult updateAll(Dept dept) {
		int i = deptMapper.update(dept);
		if(i>0){
			return CustomResult.ok();
		}else{
			return CustomResult.build(101, "修改订单失败");
		}
	}

	@Override
	public CustomResult delete(String dept_id) {
		int i = deptMapper.deleteByPrimaryKey(dept_id);
		if(i>0){
			return CustomResult.ok();
		}else{
			return null;
		}
	}

	@Override
	public CustomResult deleteBatch(String[] ids) {
		int i = deptMapper.deleteBatch(ids);
		if(i>0){
			return CustomResult.ok();
		}else{
			return null;
		}
	}

}
