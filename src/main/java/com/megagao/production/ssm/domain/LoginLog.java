package com.megagao.production.ssm.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class LoginLog {
	private int id;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
	private Date date;
	private String ip;
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
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	@Override
	public String toString() {
		return "LoginLog [id=" + id + ", date=" + date + ", ip=" + ip
				+ ", name=" + name + "]";
	}
	
	
	
	
	
	
}
