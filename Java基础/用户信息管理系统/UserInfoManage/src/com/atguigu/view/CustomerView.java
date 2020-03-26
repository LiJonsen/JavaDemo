package com.atguigu.view;

import com.atguigu.contorller.CustomerList;
import com.atguigu.model.bean.Customer;
import com.atguigu.util.CMUtility;

/**
 * @Description CustomerView为视图模块，负责菜单显示和处理用户操作
 * @author Josen
 * @date 2020年3月25日
 */
public class CustomerView {
	// 创建用户列表对象
	private static int listTotal = 10; //用户最大存储量
	private CustomerList customerList;
	
	public static void main(String[] args) {
		
		CustomerView customerView = new CustomerView();
		// 初始化用户最大存储量
		customerView.customerList = new CustomerList(listTotal);
		// 新增一条默认用户记录
		Customer cust = new Customer("黄猪精",'女',"18329182931","hdj@gmail.com",23);
		customerView.customerList.addCustomer(cust);
		
		customerView.enterMainMenu();
	}
	// 主菜单逻辑
	public void enterMainMenu() {
		boolean flag = true;
		while(flag) {
			printInitContent();
			char result = CMUtility.readMenuSelection();
			switch(result) {
				case '1':
					addNewCustomer();
				break;
				case '2':
					modifyCustomer();
				break;
				case '3':
					deleteCustomer();
				break;
				case '4':
					listAllCustomer();
				break;
				case '5':
					System.out.print("确认是否退出(Y/N)：");
					char isExit = CMUtility.readConfirmSelection();
					if(isExit == 'Y') {
						flag = false;
					}
			}
		}
	}
	// 主菜单默认显示内容
	private void printInitContent() {
		System.out.println("----------------用户信息管理系统----------------\n");
		System.out.println("\t\t1. 添加用户");
		System.out.println("\t\t2. 修改用户");
		System.out.println("\t\t3. 删除用户");
		System.out.println("\t\t4. 用户列表");
		System.out.println("\t\t5. 退出");
	}
	// 添加用户操作
	public void addNewCustomer() {
		System.out.println("--------------添加用户--------------");
		Customer cust = createCustomer();
		boolean isSuccess = customerList.addCustomer(cust);
		if(isSuccess) {			
			System.out.println("--------------添加用户成功--------------");
		}else {
			System.out.println("----------用户目录已满，添加失败----------");
		}
	}
	// 删除用户操作
	public void deleteCustomer() {
		System.out.println("------------------删除用户信息------------------");
		int number;
		Customer cust;
		for(;;) {
			System.out.print("请输入要删除的用户编号(-1返回主菜单)：");
			number = CMUtility.readInt();
			// 返回主菜单
			if(number == -1) {
				
				return;
			}
			cust = customerList.getCustomer(number-1);
			if(cust == null) {
				System.out.println("--------------用户编号"+number+"不存在--------------");
				continue;
			}
			System.out.print("确认是否删除编号"+number+"用户(Y/N)：");
			char status = CMUtility.readConfirmSelection(); //确认是否删除
			if(status=='Y') {					
				boolean deleteSuccess = customerList.deleteCustomer(number-1);
				if(deleteSuccess) {		
					System.out.println("--------------删除用户编号"+number+"完成--------------");
					break;
				}else System.out.println("--------------用户编号"+number+"删除失败--------------");
			}else break;
			
		}
		
	}
	// 修改用户操作
	public void modifyCustomer() {
		
		System.out.println("------------------修改用户信息------------------");
		int number;
		Customer cust;
		for(;;) {	
			System.out.print("请输入要修改用户的编号(-1返回主菜单)：");
			number = CMUtility.readInt();
			// 返回主菜单
			if(number == -1) {
				return;
			}
			// 查询指定index用户
			cust = customerList.getCustomer(number-1);
			
			if(cust == null) {
				System.out.println("编号不存在，请重新输入");
			}else break;
		}
		
		// 修改用户信息操作
		Customer new_cust = createCustomer(cust);
		boolean modifySuccess = customerList.replaceCustomer(number-1,new_cust);
		if(modifySuccess) {
			System.out.println("--------------修改用户编号"+number+"完成--------------");
		}else {
			System.out.println("--------------修改用户编号"+number+"失败--------------");
		}
	}
	// 打印用户列表数据
	public void listAllCustomer() {
		System.out.println("------------------用户列表------------------");
		
		int total = customerList.getTotal();
		if(total<=0) {
			System.out.println("没有用户记录！");
		}else {
			System.out.println("编号\t姓名\t性别\t年龄\t电话\t\t邮箱");
			Customer[] cust = customerList.getAllCustomer();
			for(int i=0;i<total;i++) {
				System.out.println((i+1)+"\t"+cust[i].getName()
						+"\t"+cust[i].getGender()+"\t"+cust[i].getAge()
						+"\t"+cust[i].getPhone()+"\t"+cust[i].getEmail());
			}
		}
		System.out.println("------------------用户列表完成------------------\n");
	}
	/*
	 * 创建一个Customer用户，并返回该对象 
	 */
	private Customer createCustomer() {
		System.out.print("姓名：");
		String name = CMUtility.readString(10);
		System.out.print("性别：");
		char gender = CMUtility.readChar();
		System.out.print("年龄：");
		int age = CMUtility.readInt();
		System.out.print("手机：");
		String phone = CMUtility.readString(13);
		System.out.print("邮箱：");
		String email = CMUtility.readString(20);
		return new Customer(name,gender,phone,email,age);
	}
	/*
	 * 创建一个Customer用户，并返回该对象 
	 * old_c!=null 若用户不输入修改内容，则默认=old_c原有值
	 */
	private Customer createCustomer(Customer old_c) {
		System.out.print("姓名：");
		String name = CMUtility.readString(10,old_c.getName());
		System.out.print("性别：");
		char gender = CMUtility.readChar(old_c.getGender());
		System.out.print("年龄：");
		int age = CMUtility.readInt(old_c.getAge());
		System.out.print("手机：");
		String phone = CMUtility.readString(13,old_c.getPhone());
		System.out.print("邮箱：");
		String email = CMUtility.readString(20,old_c.getEmail());
		
		return new Customer(name,gender,phone,email,age);
	}
	
}
