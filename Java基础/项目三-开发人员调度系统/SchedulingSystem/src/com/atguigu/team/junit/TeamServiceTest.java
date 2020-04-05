package com.atguigu.team.junit;
import org.junit.Test;

import com.atguigu.team.domain.Employee;
import com.atguigu.team.domain.Programmer;
import com.atguigu.team.service.NameListService;
import com.atguigu.team.service.TeamService;
import com.atguigu.team.util.TeamException;

/**
 * @Description 測試開發團隊添加、刪除操作
 * @author Jonsen
 *
 */
public class TeamServiceTest {
	/**
	 * 測試添加開發團隊人員、刪除開發人員
	 */
	@Test
	public void testTeamService() {
		TeamService team = new TeamService();
		NameListService employees = new NameListService();
		try {
			Employee msg = employees.getEmployee(5);
			team.addTeamMember(msg);
			System.out.println("添加"+msg.getName()+"到開發團隊");
			Employee msg2 = employees.getEmployee(7);
			team.addTeamMember(msg2);
			System.out.println("添加"+msg2.getName()+"到開發團隊");
			Employee msg3 = employees.getEmployee(7);
			team.addTeamMember(msg3);
			System.out.println("添加"+msg3.getName()+"到開發團隊");
		}catch(TeamException e) {
			System.out.println(e.getMessage());
		}
		Employee[] list = employees.getAllEmployee();
		for(int i=0;i<list.length;i++) {
			System.out.println(list[i]);
		}
		
		System.out.println("*************開發團隊***************");
		Programmer[] pro_list = team.getTeam();
		for(int i=0;i<pro_list.length;i++) {
			System.out.println(pro_list[i].getMemberID()+"\t"+pro_list[i]);
		}
		
		
		System.out.println("*************測試刪除成員***************");
		try {
			team.removeTeamMember(2);
			Programmer[] test_remove = team.getTeam();
			for(int i=0;i<test_remove.length;i++) {
				System.out.println(pro_list[i].getMemberID()+"\t"+pro_list[i]);
			}
		}catch(TeamException e) {
			System.out.println(e.getMessage());
		}
		
		
		System.out.println("*************員工列表***************");
		for(int i=0;i<list.length;i++) {
			System.out.println(list[i]);
		}
		
	}

}
