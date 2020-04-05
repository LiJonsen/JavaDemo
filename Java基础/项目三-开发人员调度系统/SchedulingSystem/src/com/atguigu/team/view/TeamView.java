package com.atguigu.team.view;
import com.atguigu.team.domain.Employee;
import com.atguigu.team.domain.Programmer;
import com.atguigu.team.service.NameListService;
import com.atguigu.team.service.TeamService;
import com.atguigu.team.util.TSUtility;
import com.atguigu.team.util.TeamException;

/**
 * @Description 展示開發團隊系統操作界面
 * @author Jonsen
 *
 */
public class TeamView {
	// 操作開發團隊添加、刪除
	private TeamService teamService = new TeamService();
	// 操作員工列表
	private NameListService listService = new NameListService();
	
	public static void main(String[] args) {
		TeamView teamView = new TeamView();
		teamView.showMenu();
	}
	/**
	 * showMenu
	 * @Description 顯示菜單選項
	 */
	public void showMenu() {
		boolean flag = true;
		char choose=0;
		while(flag) {
			if(choose != '1') {
				showAllEmployees();
			}
			System.out.println("1-团队列表  2-添加团队成员 3-删除团队成员  4-退出");
			choose = TSUtility.readMenuSelection();
			switch (choose) {
				case '1':// 显示开发团队成员列表
					
					getAllEmployees();
					break;
				case '2':// 添加开发团队成员
					addEmployee();
					break;
				case '3':// 删除开发团队成员
					removeEmployee();
					break;
				case '4':// 退出
					System.out.println("确认要退出吗？(Y/N)");
					char isExit = TSUtility.readConfirmSelection();
					if(isExit == 'Y') {
						flag = false;
					}
			}
		}
	}
	/**
	 * @Description 删除开发成员
	 */
	public void removeEmployee() {
		System.out.println("请输入要删除的员工TID：");
		int tid = TSUtility.readInt();
		try {
			teamService.removeTeamMember(tid);
			System.out.println("删除TID="+tid+"成功");
		}catch (TeamException e) {
			System.out.println("删除失败，原因："+e.getMessage());
		}
		TSUtility.readReturn();
	}
	/**
	 * @Description 添加开发成员
	 */
	public void addEmployee() {
		System.out.println("请输入要添加的员工ID：");
		int num = TSUtility.readInt();
		try {
			Employee emp = listService.getEmployee(num);
			teamService.addTeamMember(emp);
			System.out.println("成功添加ID="+num+"员工到开发团队中");
			
		} catch (TeamException e) {
			System.out.println("添加失败，原因："+e.getMessage());
		}
		TSUtility.readReturn();
	}
	/**
	 * @Description 获取开发团队所有成员
	 */
	public void getAllEmployees() {
		Programmer[] team = teamService.getTeam();
		System.out.println("-----------------------------开发团队成员列表-----------------------------");
		if(team == null || team.length<=0) {
			System.out.println("暂无开发成员");
		}else {
			System.out.println("TID/ID\t名字\t年龄\t薪资\t职位\t奖金\t股票");
			for(int i=0;i<team.length;i++) {
				System.out.println(team[i].getTeamBaseDetail());
			}
		}
		System.out.println("---------------------------------------------------------------------");
	}
	/**
	 * showAllEmployees
	 * @Description 顯示所有員工列表
	 */
	public void showAllEmployees() {
		Employee[] list = listService.getAllEmployee();
		System.out.println("-----------------------------开发团队调度系统-----------------------------");
		System.out.println("ID\t名字\t年龄\t薪资\t职位\t状态\t奖金\t股票\t领用设备");
		for(int i=0;i<list.length;i++) {
			System.out.println(list[i]);
		}
		System.out.println("---------------------------------------------------------------------");
	}
}
