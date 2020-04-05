package com.atguigu.team.service;
/**
 * @Description 提供employees員工列表添加、刪除操作
 * @author Jonsen
 */
import com.atguigu.team.domain.Architect;
import com.atguigu.team.domain.Designer;
import com.atguigu.team.domain.Employee;
import com.atguigu.team.domain.Programmer;
import com.atguigu.team.util.TeamException;

public class TeamService {
	public static int counter = 1; // memberId自增標識
	public static final int MAX_MEMBER = 5; //開發團隊最大人數
	private Programmer[] team = new Programmer[MAX_MEMBER]; //已有的開發團隊成員
	private int total; //team中已有人數
	/**
	 * @Description 獲取已有的所有開發成員
	 * @return Programmer[]
	 */
	public Programmer[] getTeam() {
		Programmer[] team = new Programmer[total];
		for(int i=0;i<total;i++) {
			team[i] = this.team[i];
		}
		return team;
	}
	/**
	 * @Description 添加開發團隊成員
	 * @return true:添加成功 | false:添加失敗
	 * @throws TeamException
	 */
	public void addTeamMember(Employee e) throws TeamException {
		// 獲取員工名字
		String name = e.getName();
		// 1.開發團隊人員已滿
		if(total >= MAX_MEMBER) {
			throw new TeamException("開發團隊人員已滿"+MAX_MEMBER+"人");
		}
		// 2.該員工不是開發人員
		if(!(e instanceof Programmer)) {
		    throw new TeamException(name+"不是程序員");
		}
		Programmer p = (Programmer)e;
		// 3.該員工已在其他開發團隊或休假中
		if("BUSY".equals(p.getStatus().getNAME())) {
		    throw new TeamException(name+"已在本開發團隊中");
		}else if("VOCATION".equals(p.getStatus().getNAME())) {
			throw new TeamException(name+"正在休假中");
		}
		// 4.開發團隊只能有1個架構師、2個設計師、3個程序員
		checkMemberType(p);
		// 添加團隊成員
	
		team[total++] = p;
		p.setStatus(Status.BUSY);
		p.setMemberID(counter++);
		
	}
	/**
	 * @Description 刪除指定memberId開發團隊成員
	 * @param memberId
	 * @return boolean
	 */
	public void removeTeamMember(int memberId) throws TeamException {
		int i=0;
		for(;i<total;i++) {
			if(team[i].getMemberID() == memberId) {
				team[i].setStatus(Status.FREE);
				break;
			}
		}
		// 未找到指定ID
		if(i == total) {
			throw new TeamException("未找到該開發成員TID");
		}
		// 將為null的team[i]移至最後
		for(int j=i+1;j<total;j++) {
			team[j-1] = team[j];
		}
		team[--total] = null;
	}
	/**
	 * @Description 校驗team只能有1個架構師、2個設計師、3個程序員
	 * @param p
	 * @throws TeamException
	 */
	private void checkMemberType(Programmer p) throws TeamException {
		int numOfArchitect=0,numOfDesigner=0,numOfProgrammer = 0;
		// 記錄team中已有的人數
		for(int i=0;i<total;i++) {
			if(team[i] instanceof Architect) {
				++numOfArchitect;
			}else if(team[i] instanceof Designer) {
				++numOfDesigner;
			}else if(team[i] instanceof Programmer) {
				++numOfProgrammer;
			}
		}
		if(p instanceof Architect) {
			if(numOfArchitect>=1) {
				throw new TeamException("團隊中已有1名架構師");
			}
		}else if(p instanceof Designer) {
			if(numOfDesigner>=2) {
				throw new TeamException("團隊中已有2名設計師");
			}
		}else if(p instanceof Programmer) {
			if(numOfProgrammer>=3) {
				throw new TeamException("團隊中已有3名程序員");
			}
		}
	}
	
}
