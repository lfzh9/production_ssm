package com.megagao.production.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.megagao.production.ssm.domain.Notice;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.mapper.NoticeMapper;
import com.megagao.production.ssm.service.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	private NoticeMapper noticeMapper;

	@Override
	public List<Notice> find() {

		return noticeMapper.find();
	}

	@Override
	public EUDataGridResult getList(int page, int rows) throws Exception {

		// 分页处理
		PageHelper.startPage(page, rows);
		List<Notice> list = noticeMapper.find();
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<Notice> pageInfo = new PageInfo<Notice>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public Notice get(String id) throws Exception {
		// TODO Auto-generated method stub
		return noticeMapper.loadNoticeById(id);
	}
	
	
}
