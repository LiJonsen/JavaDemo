package com.atguigu.team.junit;
import org.junit.Test;
import com.atguigu.team.domain.Employee;
import com.atguigu.team.service.NameListService;
import com.atguigu.team.util.TeamException;
/**
 * @Description 測試員工列表
 * @author Jonsen
 *
 */
public class NameListServiceTest {
	// 測試員工列表
	@Test
	public void testNameListService() {
		NameListService list = new NameListService();
		Employee[] employees = list.getAllEmployee();
		for(int i=0;i<employees.length;i++) {
			System.out.println(employees[i]);
		}
	}
	/**
	 * 測試獲取指定員工信息
	 */
	@Test
	public void testGetEmployee() {
		NameListService employees = new NameListService();
		int id = 14;
		try {
			Employee msg = employees.getEmployee(id);
			System.out.println(msg);
		}catch(TeamException e) {
			System.out.println(e.getMessage());
		}
	}
}
