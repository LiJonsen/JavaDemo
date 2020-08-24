package cn.touchfish.mm.entity;

import cn.touchfish.mm.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 封装正常返回结果
 */
@Data
@AllArgsConstructor
public class Result implements java.io.Serializable {
	private int code;//执行结果，true为执行成功 false为执行失败
	private String message;//返回结果信息
	private Object result;//返回数据
	public Result(int code,String message){
		this.code = code;
		this.message = message;
	}
	public Result(int code){
		this.code = code;
		this.message = Constants.SERVICE_ERROR;
	}
}
