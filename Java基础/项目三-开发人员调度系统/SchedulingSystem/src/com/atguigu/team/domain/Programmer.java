package com.atguigu.team.domain;
import com.atguigu.team.service.Status;

// 程序员类
public class Programmer extends Employee{
	private int memberID; //表示开发团队当中的ID(TID)
	private Status status = Status.FREE; //员工状态
	private Equipment equipment; //设备
	
	public Programmer() {
		super();
	}
	public Programmer(int id, int age, String name, double salary, Equipment equipment) {
		super(id, age, name, salary);
		this.equipment = equipment;
	}
	public int getMemberID() {
		return memberID;
	}
	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Equipment getEquipment() {
		return equipment;
	}
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}
	/**
	 * @Description 获取开发团队中的基本信息
	 */
	public String getTeamBaseDetail() {
		return memberID+"/"+getDescription()+"\t程序员";
	}
	@Override
	public String toString() {
		return getDescription() + "\t程序員\t" + getStatus()+"\t\t\t"+equipment.getDesciption();
	}
}
