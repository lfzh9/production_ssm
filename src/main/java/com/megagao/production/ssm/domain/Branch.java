package com.megagao.production.ssm.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Branch {

	private String id;
	@NotNull(message="机构名不能为空")
	private String name;
	private String short_name;
	private String time;
	private Dept dept; 
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShort_name() {
		return short_name;
	}
	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}
	@Override
	public String toString() {
		return "Branch [id=" + id + ", name=" + name + ", short_name="
				+ short_name + "]";
	}
	
	
}
