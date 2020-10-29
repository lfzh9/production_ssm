package com.megagao.production.ssm.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Dept {
	@NotNull(message="部门编号不能为空")
	private String dept_id;
	@NotNull(message="部门名不能为空")
	private String dept_name;

	private String dept_mtlp;
	private String dept_tlp;
	private String dept_char;
	private String dept_nameout;
	
	public String getDept_id() {
		return dept_id;
	}
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getDept_mtlp() {
		return dept_mtlp;
	}
	public void setDept_mtlp(String dept_mtlp) {
		this.dept_mtlp = dept_mtlp;
	}
	public String getDept_tlp() {
		return dept_tlp;
	}
	public void setDept_tlp(String dept_tlp) {
		this.dept_tlp = dept_tlp;
	}
	public String getDept_char() {
		return dept_char;
	}
	public void setDept_char(String dept_char) {
		this.dept_char = dept_char;
	}
	public String getDept_nameout() {
		return dept_nameout;
	}
	public void setDept_nameout(String dept_nameout) {
		this.dept_nameout = dept_nameout;
	}
	@Override
	public String toString() {
		return "Dept [dept_id=" + dept_id + ", dept_name=" + dept_name
				+ ", dept_mtlp=" + dept_mtlp + ", dept_tlp=" + dept_tlp
				+ ", dept_char=" + dept_char + ", dept_nameout=" + dept_nameout
				+ "]";
	}
	
	
}
