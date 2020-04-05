package com.atguigu.team.util;

public class Datas {
	public static final int EMPLOYEE = 10;
	public static final int PROGRAMMER = 11;
	public static final int DESIGNER = 12;
	public static final int ARCHITECT = 13;
	
	public static final int PC = 21;
	public static final int NOTEBOOK = 22;
	public static final int PRINTER = 23;
	/*
	 * 根據上面靜態常量判斷擁有不同福利
	 * EMPLOYEE(普通員工)：10,id,name,age,salary(薪水)
	 * PROGRAMMER(程序員)：11,id,name,age,salary
	 * DESIGNER(設計師)：12,id,name,age,salary,bonus(獎金)
	 * ARCHITECT(架構師)：13,id,name,age,salary,bonus,stock(股票)
	 */
	public static final String[][] EMPLOYEES = {
			{"10","1","馬雲","22","3000"},
			{"13","2","馬化騰","32","18000","15000","2000"},
			{"11","3","李彥宏","23","7000"},
			{"11","4","劉強東","24","7300"},
			{"12","5","雷軍","28","10000","5000"},
			{"11","6","任志強","22","6800"},
			{"12","7","柳傳志","29","10800","5200"},
			{"13","8","劉元慶","30","19800","15000","2500"},
			{"12","9","史玉柱","26","9800","5500"},
			{"11","10","丁磊","21","6600"},
			{"11","11","張朝陽","25","7100"},
			{"12","12","楊志遠","27","9600","4800"},
	};
	
/*
 * EQUIPMENTS：領用設備
 * 對應類型
 * PC = 21;
   NOTEBOOK(筆記本) = 22;
   PRINTER(打印機) = 23;
 */
	public static final String[][] EQUIPMENTS = {
			{},
			{"22","聯想T4","6000"},
			{"21","戴爾","NEC 17寸"},
			{"21","戴爾","三星 17寸"},
			{"23","佳能 2900","激光"},
			{"21","華碩","三星 17寸"},
			{"21","華碩","三星 17寸"},
			{"23","愛普生20k","針式"},
			{"22","惠普m6","5800"},
			{"21","戴爾","NEC 17寸"},
			{"21","華碩","三星 17寸"},
			{"22","惠普m6","5800"},
	};
}
