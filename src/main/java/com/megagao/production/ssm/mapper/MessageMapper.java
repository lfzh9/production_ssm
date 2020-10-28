package com.megagao.production.ssm.mapper;

import java.util.List;

import com.megagao.production.ssm.domain.Message;

public interface MessageMapper {
	public List<Message> find();
	public Message loadMessageById(String id);
	public List<Message> searchMessageByTitle(String title);
	public List<Message> searchMessageByType(String type);
	public List<Message> searchMessageByPerson(String person);
	public List<Message> searchMessageByTime(String time);
	public int update(Message message);
	public int deleteByPrimaryKey(String id);
	public int deleteBatch(String[] ids);
	public int insert(Message message)throws Exception;
}
