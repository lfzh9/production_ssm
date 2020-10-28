package com.megagao.production.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.megagao.production.ssm.domain.Mynote;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.mapper.MynoteMapper;
import com.megagao.production.ssm.service.MynoteService;

@Service
public class MynoteServiceImpl implements MynoteService {
	@Autowired
	private MynoteMapper mynoteMapper;

	@Override
	public List<Mynote> find() {

		return mynoteMapper.find();
	}

	@Override
	public EUDataGridResult getList(int page, int rows) throws Exception {

		// 分页处理
		PageHelper.startPage(page, rows);
		List<Mynote> list = mynoteMapper.find();
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<Mynote> pageInfo = new PageInfo<Mynote>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public Mynote get(String id) throws Exception {
		// TODO Auto-generated method stub
		return mynoteMapper.loadMynoteById(id);
	}
	
	@Override
	public CustomResult insert(Mynote Mynote) throws Exception {
		int i = mynoteMapper.insert(Mynote);
		if (i > 0) {
			return CustomResult.ok();
		} else {
			return CustomResult.build(101, "新增便签失败");
		}
	}

	@Override
	public EUDataGridResult searchMynoteByTitle(Integer page, Integer rows,
			String searchValue) throws Exception {
		// 分页处理
				PageHelper.startPage(page, rows);
				List<Mynote> list = mynoteMapper.searchMynoteByTitle(searchValue);
				// 创建一个返回值对象
				EUDataGridResult result = new EUDataGridResult();
				result.setRows(list);
				// 取记录总条数
				PageInfo<Mynote> pageInfo = new PageInfo<Mynote>(list);
				result.setTotal(pageInfo.getTotal());
				return result;
	}


	@Override
	public EUDataGridResult searchMynoteByPerson(Integer page, Integer rows,
			String searchValue) throws Exception {
		// 分页处理
		PageHelper.startPage(page, rows);
		List<Mynote> list = mynoteMapper.searchMynoteByPerson(searchValue);
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<Mynote> pageInfo = new PageInfo<Mynote>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}
	
	@Override
	public EUDataGridResult searchMynoteByTime(Integer page, Integer rows,
			String searchValue) throws Exception {
		// 分页处理
		PageHelper.startPage(page, rows);
		List<Mynote> list = mynoteMapper.searchMynoteByTime(searchValue);
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<Mynote> pageInfo = new PageInfo<Mynote>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	

	@Override
	public CustomResult updateAll(Mynote mynote) {
		int i = mynoteMapper.update(mynote);
		if(i>0){
			return CustomResult.ok();
		}else{
			return CustomResult.build(101, "编辑便签失败");
		}
	}

	@Override
	public CustomResult delete(String id) {
		int i = mynoteMapper.deleteByPrimaryKey(id);
		if(i>0){
			return CustomResult.ok();
		}else{
			return null;
		}
	}

	@Override
	public CustomResult deleteBatch(String[] ids) {
		int i = mynoteMapper.deleteBatch(ids);
		if(i>0){
			return CustomResult.ok();
		}else{
			return null;
		}
	}





}
