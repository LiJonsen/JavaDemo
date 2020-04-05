package com.atguigu.team.service;
//import com.atguigu.team.domain.Employee;
//import com.atguigu.team.domain.Equipment;
//import com.atguigu.team.domain.Programmer;
import com.atguigu.team.domain.*;
import com.atguigu.team.util.TeamException;

import static com.atguigu.team.util.Datas.*;
/**
 * @Description 负责将Datas中的数据整合到employees数组中，同时提供相关操作employees数组
 * @author Jonsen
 */
public class NameListService {
	private Employee[] employees;
	public NameListService(){
		/**
		 * 初始化将Datas中的数据整合到employees数组中
		 */
		employees = new Employee[EMPLOYEES.length];
		
		for(int i=0;i<employees.length;i++) {
			int type = Integer.parseInt(EMPLOYEES[i][0]);
			int id = Integer.parseInt(EMPLOYEES[i][1]);
			String name = EMPLOYEES[i][2];
			int age = Integer.parseInt(EMPLOYEES[i][3]);
			// 薪資
			double salary = Double.parseDouble(EMPLOYEES[i][4]);
			// 設備信息
			Equipment equipment;
			// 獎金
			double bonus;
			// 股票
			int stock;
			switch(type) {
				case EMPLOYEE://普通员工
					employees[i] = new Employee(id, age, name, salary);
					break;
				case PROGRAMMER://程序员
					equipment = createEquipment(i);
					employees[i] = new Programmer(id, age, name, salary, equipment);
					break;
				case DESIGNER://设计师
					equipment = createEquipment(i);
					bonus = Double.parseDouble(EMPLOYEES[i][5]);
					employees[i] = new Designer(id, age, name, salary, equipment, bonus);
					break;
				case ARCHITECT://架构师
					equipment = createEquipment(i);
					bonus = Double.parseDouble(EMPLOYEES[i][5]);
					stock = Integer.parseInt(EMPLOYEES[i][6]);
					employees[i] = new Architect(id, age, name, salary, equipment, bonus, stock);
					break;
			}
		}
		
	}
	/**
	 * @Description 创建员工设备信息
	 * @param index
	 * @return 设备信息
	 */
	private Equipment createEquipment(int index) {
		// 設備信息類型
		int type = Integer.parseInt(EQUIPMENTS[index][0]);
		switch (type) {
			case PC:
				return new PC(EQUIPMENTS[index][1], EQUIPMENTS[index][2]);
			case NOTEBOOK:
				return new NoteBook(EQUIPMENTS[index][1], EQUIPMENTS[index][2]);
			case PRINTER:
				return new Printer(EQUIPMENTS[index][1], EQUIPMENTS[index][2]);
		}
		return null;
	}
	/**
	 * @Description 获取指定id员工对象
	 * @param id=員工ID
	 * @throws TeamException
	 * @return 员工对象
	 */
	public Employee getEmployee(int id) throws TeamException{
		for(int i=0;i<employees.length;i++) {
			if(employees[i].getId() == id) {
				return employees[i];
			}
		}
		throw new TeamException("找不到ID="+id+"的員工信息");
	}
	/**
	 * @Description 获取所有员工
	 * @return employees
	 */
	public Employee[] getAllEmployee() {
		return employees;
	}
}
