package com.megagao.production.ssm.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OperationLog {
	private int id;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
	private Date date;
	private String desc;
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Object getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Override
	public String toString() {
		return "OperationLog [id=" + id + ", date=" + date + ", desc=" + desc
				+ ", name=" + name + "]";
	}
}
