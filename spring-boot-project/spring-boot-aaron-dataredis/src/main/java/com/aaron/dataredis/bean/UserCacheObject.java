package com.aaron.dataredis.bean;

import java.io.Serializable;

public class UserCacheObject implements Serializable {
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private Integer id;
	private Integer gender;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}
}
