package com.atguigu.model.bean;
/**
 * @Description Customer为实体对象，用来封装用户信息
 * @author Josen
 * @date 2020年3月25日
 */
public class Customer {
	private String name; //名字
	private char gender; //性别
	private String phone; //手机
	private String email; //邮箱
	private int age; //年龄
	// 构造器
	public Customer() {
		
	}
	public Customer(String name, char gender, String phone, String email, int age) {
	
		this.name = name;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.age = age;
	}
	
	// get、set方法
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
