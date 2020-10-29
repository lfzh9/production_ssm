package com.megagao.production.ssm.mapper;

import java.util.List;

import com.megagao.production.ssm.domain.Branch;
import com.megagao.production.ssm.domain.Staff;

public interface StaffMapper {
	public List<Staff> find();
	public List<Staff> searchStaffById(String id);
	public List<Staff> searchStaffByName(String name);
	
	public int insert(Staff staff)throws Exception;
	public int update(Staff staff);
	public int deleteByPrimaryKey(String id);
	public int deleteBatch(String[] ids);
	public Staff loadStaffById(String id);

	
}
