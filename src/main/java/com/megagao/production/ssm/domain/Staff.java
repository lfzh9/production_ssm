package com.megagao.production.ssm.domain;

public class Staff {
private String id;
private String username;
private String sex;
private String locked;
private String dept_name;
private String role_name;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
public String getLocked() {
	return locked;
}
public void setLocked(String locked) {
	this.locked = locked;
}
public String getDept_name() {
	return dept_name;
}
public void setDept_name(String dept_name) {
	this.dept_name = dept_name;
}
public String getRole_name() {
	return role_name;
}
public void setRole_name(String role_name) {
	this.role_name = role_name;
}
@Override
public String toString() {
	return "Staff [id=" + id + ", username=" + username + ", sex=" + sex
			+ ", locked=" + locked + ", dept_name=" + dept_name
			+ ", role_name=" + role_name + "]";
}


}
