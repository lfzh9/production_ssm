package com.megagao.production.ssm.domain;

public class Notice {
	private String id;
	private String type;
	private String text;
	private String time;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Notice [id=" + id + ", type=" + type + ", text=" + text
				+ ", time=" + time + "]";
	}
	
}
