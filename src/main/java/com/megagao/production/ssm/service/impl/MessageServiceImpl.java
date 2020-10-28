package com.megagao.production.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.megagao.production.ssm.domain.Message;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.mapper.MessageMapper;
import com.megagao.production.ssm.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {
	@Autowired
	private MessageMapper messageMapper;

	@Override
	public List<Message> find() {

		return messageMapper.find();
	}

	@Override
	public EUDataGridResult getList(int page, int rows) throws Exception {

		// 分页处理
		PageHelper.startPage(page, rows);
		List<Message> list = messageMapper.find();
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<Message> pageInfo = new PageInfo<Message>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public Message get(String id) throws Exception {
		// TODO Auto-generated method stub
		return messageMapper.loadMessageById(id);
	}
	
	@Override
	public CustomResult insert(Message message) throws Exception {
		int i = messageMapper.insert(message);
		if (i > 0) {
			return CustomResult.ok();
		} else {
			return CustomResult.build(101, "新增消息失败");
		}
	}

	@Override
	public EUDataGridResult searchMessageByTitle(Integer page, Integer rows,
			String searchValue) throws Exception {
		// 分页处理
				PageHelper.startPage(page, rows);
				List<Message> list = messageMapper.searchMessageByTitle(searchValue);
				// 创建一个返回值对象
				EUDataGridResult result = new EUDataGridResult();
				result.setRows(list);
				// 取记录总条数
				PageInfo<Message> pageInfo = new PageInfo<Message>(list);
				result.setTotal(pageInfo.getTotal());
				return result;
	}

	@Override
	public EUDataGridResult searchMessageByType(Integer page, Integer rows,
			String searchValue) throws Exception {
		// 分页处理
		PageHelper.startPage(page, rows);
		List<Message> list = messageMapper.searchMessageByType(searchValue);
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<Message> pageInfo = new PageInfo<Message>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public EUDataGridResult searchMessageByPerson(Integer page, Integer rows,
			String searchValue) throws Exception {
		// 分页处理
		PageHelper.startPage(page, rows);
		List<Message> list = messageMapper.searchMessageByPerson(searchValue);
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<Message> pageInfo = new PageInfo<Message>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public EUDataGridResult searchMessageByTime(Integer page, Integer rows,
			String searchValue) throws Exception {
		// 分页处理
		PageHelper.startPage(page, rows);
		List<Message> list = messageMapper.searchMessageByTime(searchValue);
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<Message> pageInfo = new PageInfo<Message>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public CustomResult updateAll(Message message) {
		int i = messageMapper.update(message);
		if(i>0){
			return CustomResult.ok();
		}else{
			return CustomResult.build(101, "编辑消息失败");
		}
	}

	@Override
	public CustomResult delete(String id) {
		int i = messageMapper.deleteByPrimaryKey(id);
		if(i>0){
			return CustomResult.ok();
		}else{
			return null;
		}
	}

	@Override
	public CustomResult deleteBatch(String[] ids) {
		int i = messageMapper.deleteBatch(ids);
		if(i>0){
			return CustomResult.ok();
		}else{
			return null;
		}
	}





}
