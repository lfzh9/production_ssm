package com.megagao.production.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.megagao.production.ssm.domain.Staff;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.mapper.StaffMapper;
import com.megagao.production.ssm.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService {
	@Autowired
	private StaffMapper staffMapper;

	@Override
	public List<Staff> find() {

		return staffMapper.find();
	}

	@Override
	public EUDataGridResult getList(int page, int rows) throws Exception {

		// 分页处理
		PageHelper.startPage(page, rows);
		List<Staff> list = staffMapper.find();
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<Staff> pageInfo = new PageInfo<Staff>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public EUDataGridResult searchStaffById(Integer page, Integer rows,
			String searchValue) throws Exception {
		// 分页处理
		PageHelper.startPage(page, rows);
		List<Staff> list = staffMapper.searchStaffById(searchValue);
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<Staff> pageInfo = new PageInfo<Staff>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public EUDataGridResult searchStaffByUsername(Integer page, Integer rows,
			String searchValue) throws Exception {
		// 分页处理
		PageHelper.startPage(page, rows);
		List<Staff> list = staffMapper.searchStaffByUsername(searchValue);
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<Staff> pageInfo = new PageInfo<Staff>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}



	@Override
	public CustomResult insert(Staff staff) throws Exception {
		int i = staffMapper.insert(staff);
		if (i > 0) {
			return CustomResult.ok();
		} else {
			return CustomResult.build(101, "新增机构失败");
		}
	}

	@Override
	public Staff get(String id) throws Exception {
		// TODO Auto-generated method stub
		return staffMapper.loadStaffById(id);
	}

	@Override
	public CustomResult updateAll(Staff staff) {
		int i = staffMapper.update(staff);
		if(i>0){
			return CustomResult.ok();
		}else{
			return CustomResult.build(101, "修改订单失败");
		}
	}

	@Override
	public CustomResult delete(String id) {
		int i = staffMapper.deleteByPrimaryKey(id);
		if(i>0){
			return CustomResult.ok();
		}else{
			return null;
		}
	}

	@Override
	public CustomResult deleteBatch(String[] ids) {
		int i = staffMapper.deleteBatch(ids);
		if(i>0){
			return CustomResult.ok();
		}else{
			return null;
		}
	}

}
