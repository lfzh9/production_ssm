package com.megagao.production.ssm.mapper;

import java.util.List;

import com.megagao.production.ssm.domain.Notice;


public interface NoticeMapper {
	public List<Notice> find();
	public Notice loadNoticeById(String id);
}
