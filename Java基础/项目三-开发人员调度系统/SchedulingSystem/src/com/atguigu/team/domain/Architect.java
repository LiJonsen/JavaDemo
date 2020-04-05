package com.atguigu.team.domain;
/**
 * 架构师类
 * @author Jonsen
 */
public class Architect extends Designer{
	private int stock; //股票
	
	public Architect() {
		super();
	}

	public Architect(int id, int age, String name, double salary, Equipment equipment, double bonus, int stock) {
		super(id, age, name, salary, equipment, bonus);
		this.stock = stock;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	/**
	 * @Description 获取开发团队中的基本信息
	 */
	public String getTeamBaseDetail() {
		return getMemberID()+"/"+getDescription()+"\t架构师\t"+getBonus()+"\t"+stock;
	}
	@Override
	public String toString() {
		return getDescription() + "\t架構師\t" + getStatus()+"\t"+getBonus()+"\t"+stock+"\t"+getEquipment().getDesciption();
	}
}
