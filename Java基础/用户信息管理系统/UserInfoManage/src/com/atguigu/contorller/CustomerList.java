package com.atguigu.contorller;

import com.atguigu.model.bean.Customer;

/**
 * @Description CustomerList为管理实体对象模块，用于添加、删除、修改、遍历用户信息
 * @author Josen
 * @date 2020年3月25日
 */
public class CustomerList {
	private Customer[] customers; //用來保存用戶對象的數組
	private int total; //用來保存用戶數量總數
	/**
	 * 構造器-設置默認用戶可存數量
	 */ 
	public CustomerList(int totalCustomer) {
		customers = new Customer[totalCustomer];
	}
	// 添加用戶
	public boolean addCustomer(Customer c) {
		// 用戶可存數量不足
		if(customers.length <= total) {
			return false;
		}
		
		customers[total++] = c;
		return true;
	}
	// 修改用戶信息
	public boolean replaceCustomer(int index, Customer c) {
		if(index<0 || index>=total) {
			return false;
		}
		customers[index] = c;
		return true;
	}
	// 刪除用戶信息
	public boolean deleteCustomer(int index) {
		if(index<0 || index>=total) {
			return false;
		}
		// 將需要刪除的元素一到最後
		for(int i=index;i<total-1;i++) {
			customers[i] = customers[i+1]; 
		}
		
		// 刪除最後一個元素
		customers[--total] = null;
		return true;
	}
	// 獲取所有用戶信息
	public Customer[] getAllCustomer() {
		Customer[] cust = new Customer[total];		
		for(int i=0;i<total;i++) {
			cust[i] = customers[i];
		}
		return cust;
	}
	// 獲取指定用戶信息
	public Customer getCustomer(int index) {
		if(index<0 || index>=total) {
			return null;
		}
		
		return customers[index];
	}
	// 獲取已有客戶的數量
	public int getTotal() {
		return total;
	}
}
