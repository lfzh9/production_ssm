package com.megagao.production.ssm.domain;



public class Mynote {
	private String id;
	private String title;
	private String text;
	private String person;
	private String time;
	
	@Override
	public String toString() {
		return "Mynote [id=" + id + ", title=" + title + ", text=" + text
				+ ", person=" + person + ", time=" + time + "]";
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
