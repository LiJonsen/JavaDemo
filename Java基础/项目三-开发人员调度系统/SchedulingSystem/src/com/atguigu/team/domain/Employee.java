package com.atguigu.team.domain;
/*
 * 员工父类 
 */
public class Employee {
	private int id;//员工ID
	private int age;//年龄
	private String name;//姓名
	private double salary;//薪资
	public Employee() {
		super();
	}
	
	public Employee(int id, int age, String name, double salary) {
		super();
		this.id = id;
		this.age = age;
		this.name = name;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	/**
	 * @Description 獲取員工詳情信息
	 * @return 詳情信息
	 */
	public String getDescription() {
		return id + "\t" + name + "\t" + age + "\t" + salary;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getDescription();
	}
	
}
