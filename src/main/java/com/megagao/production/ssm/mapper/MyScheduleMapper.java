package com.megagao.production.ssm.mapper;

import java.util.List;

import com.megagao.production.ssm.domain.MySchedule;


public interface MyScheduleMapper {
	public List<MySchedule> find();
	public MySchedule loadMyScheduleById(String id);
	public List<MySchedule> searchMyScheduleByTitle(String title);
	public List<MySchedule> searchMyScheduleByType(String type);
	public List<MySchedule> searchMyScheduleByPerson(String person);
	public List<MySchedule> searchMyScheduleByStartTime(String startTime);
	public int update(MySchedule mySchedule);
	public int deleteByPrimaryKey(String id);
	public int deleteBatch(String[] ids);
	public int insert(MySchedule mySchedule)throws Exception;
}
