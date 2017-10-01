package com.eeitxx.xmlbean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="users")
public class User {

	
	private String id;
	private String name;
	
	private List<Integer> phone;
	
	private int age;
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	/*
	 * <phones><phone>1</phone><phone>2</phone><phone>3</phone></phones>
	 * @XmlElementWrapper放在get方法前，包住回傳list的標籤
	 */
	@XmlElementWrapper(name="phones")
	@XmlElement()
	public List<Integer> getPhone() {
		return phone;
	}
	public void setPhone(List<Integer> phone) {
		this.phone = phone;
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
	
	
}
