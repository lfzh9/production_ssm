package com.megagao.production.ssm.domain;



public class Message {
	private String id;
	private String title;
	private String text;
	private String type;
	private String person;
	private String time;
	public String getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Message [id=" + id + ", title=" + title + ", text=" + text
				+ ", type=" + type + ", person=" + person + ", time=" + time
				+ "]";
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
