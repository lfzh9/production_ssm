package com.megagao.production.ssm.mapper;

import java.util.List;

import com.megagao.production.ssm.domain.Branch;

public interface BranchMapper {
	public List<Branch> find();
	public List<Branch> searchBranchById(String id);
	public List<Branch> searchBranchByName(String name);
	public List<Branch> searchBranchByShortName(String short_name);
	public int insert(Branch branch)throws Exception;
	public int update(Branch branch);
	public int deleteByPrimaryKey(String id);
	public int deleteBatch(String[] ids);
	public Branch loadBranchById(String id);

	
}
