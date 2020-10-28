package com.megagao.production.ssm.mapper;

import java.util.List;

import com.megagao.production.ssm.domain.Mynote;


public interface MynoteMapper {
	public List<Mynote> find();
	public Mynote loadMynoteById(String id);
	public List<Mynote> searchMynoteByTitle(String title);
	public List<Mynote> searchMynoteByPerson(String person);
	public List<Mynote> searchMynoteByTime(String time);
	public int update(Mynote mynote);
	public int deleteByPrimaryKey(String id);
	public int deleteBatch(String[] ids);
	public int insert(Mynote mynote)throws Exception;
}
