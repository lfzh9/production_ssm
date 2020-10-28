package com.megagao.production.ssm.domain;


import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User;

public class MySchedule {
	private String id;
	private String title;
	private String type;
	private String address;
	private String text;
	private String startTime;
	private String endTime;
	private String person;
	private String time;
	@Override
	public String toString() {
		return "MySchedule [id=" + id + ", title=" + title + ", type=" + type
				+ ", address=" + address + ", text=" + text + ", startTime="
				+ startTime + ", endTime=" + endTime + ", person=" + person
				+ ", time=" + time + "]";
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
