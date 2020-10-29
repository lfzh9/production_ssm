package com.megagao.production.ssm.mapper;

import java.util.List;

import com.megagao.production.ssm.domain.Dept;
import com.megagao.production.ssm.domain.Employee;

public interface DeptMapper {
	public List<Dept> find();
	public List<Dept> searchDeptById(String dept_id);
	public List<Dept> searchDeptByName(String dept_name);
	
	 int insertSelective(Dept dept);
	public int insert(Dept dept)throws Exception;
	public int update(Dept dept);
	public int deleteByPrimaryKey(String dept_id);
	
	public Dept loadDeptById(String dept_id);
	public int deleteBatch(String[] ids);

	
}
