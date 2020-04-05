package com.atguigu.team.util;
/**
 * @Description 自定義異常類
 * @author Jonsen
 */
public class TeamException extends Exception{
	static final long serialVersionUID = -3387516229948L;
	public TeamException(String msg) {
		super(msg);
	}
}
