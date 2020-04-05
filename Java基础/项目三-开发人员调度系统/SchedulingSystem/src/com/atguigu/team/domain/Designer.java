package com.atguigu.team.domain;
/*
 * 设计师类
 */
public class Designer extends Programmer{
	private double bonus;//奖金
	
	public Designer() {
		super();
	}

	public Designer(int id, int age, String name, double salary, Equipment equipment, double bonus) {
		super(id, age, name, salary, equipment);
		this.bonus = bonus;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
	/**
	 * @Description 获取开发团队中的基本信息
	 */
	public String getTeamBaseDetail() {
		return getMemberID()+"/"+getDescription()+"\t设计师\t"+getBonus();
	}
	@Override
	public String toString() {
		return getDescription() + "\t設計師\t" + getStatus()+"\t"+bonus+"\t\t"+getEquipment().getDesciption();
	}
}
