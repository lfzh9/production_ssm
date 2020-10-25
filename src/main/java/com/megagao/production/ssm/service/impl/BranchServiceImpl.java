package com.megagao.production.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.megagao.production.ssm.domain.Branch;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.mapper.BranchMapper;
import com.megagao.production.ssm.service.BranchService;

@Service
public class BranchServiceImpl implements BranchService {
	@Autowired
	private BranchMapper branchMapper;

	@Override
	public List<Branch> find() {

		return branchMapper.find();
	}

	@Override
	public EUDataGridResult getList(int page, int rows) throws Exception {

		// 分页处理
		PageHelper.startPage(page, rows);
		List<Branch> list = branchMapper.find();
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<Branch> pageInfo = new PageInfo<Branch>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public EUDataGridResult searchBranchById(Integer page, Integer rows,
			String searchValue) throws Exception {
		// 分页处理
		PageHelper.startPage(page, rows);
		List<Branch> list = branchMapper.searchBranchById(searchValue);
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<Branch> pageInfo = new PageInfo<Branch>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public EUDataGridResult searchBranchByName(Integer page, Integer rows,
			String searchValue) throws Exception {
		// 分页处理
		PageHelper.startPage(page, rows);
		List<Branch> list = branchMapper.searchBranchByName(searchValue);
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<Branch> pageInfo = new PageInfo<Branch>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public EUDataGridResult searchBranchByShortName(Integer page, Integer rows,
			String searchValue) throws Exception {
		// 分页处理
		PageHelper.startPage(page, rows);
		List<Branch> list = branchMapper.searchBranchByShortName(searchValue);
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<Branch> pageInfo = new PageInfo<Branch>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public CustomResult insert(Branch branch) throws Exception {
		int i = branchMapper.insert(branch);
		if (i > 0) {
			return CustomResult.ok();
		} else {
			return CustomResult.build(101, "新增机构失败");
		}
	}

	@Override
	public Branch get(String id) throws Exception {
		// TODO Auto-generated method stub
		return branchMapper.loadBranchById(id);
	}

	@Override
	public CustomResult updateAll(Branch branch) {
		int i = branchMapper.update(branch);
		if(i>0){
			return CustomResult.ok();
		}else{
			return CustomResult.build(101, "修改订单失败");
		}
	}

	@Override
	public CustomResult delete(String id) {
		int i = branchMapper.deleteByPrimaryKey(id);
		if(i>0){
			return CustomResult.ok();
		}else{
			return null;
		}
	}

	@Override
	public CustomResult deleteBatch(String[] ids) {
		int i = branchMapper.deleteBatch(ids);
		if(i>0){
			return CustomResult.ok();
		}else{
			return null;
		}
	}

}
