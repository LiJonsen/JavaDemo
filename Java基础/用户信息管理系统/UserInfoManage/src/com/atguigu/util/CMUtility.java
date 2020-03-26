package com.atguigu.util;

import java.util.Scanner;
/**
 * @Description CMUtility为工具类，负责校验用户输入
 * @author Josen
 * @date 2020年3月25日
 */
public class CMUtility {
	private static Scanner scan = new Scanner(System.in);
	/**
	 * readMenuSelection-读取菜单选项
	 * @return 用户输入的选项 （1-5）
	 */
	public static char readMenuSelection() {
		char c;
		String str;
		while(true) {
			str = readKeyBoard(1,false);
			// charAt - 截取指定下标的字符并返回
			c = str.charAt(0);
			if(c != '1' && c!='2'&&c!='3'&&c!='4'&&c!='5') {
				System.out.println("选择错误，请重新输入！");
			}else break;
			
		}
		return c;
	}
	/**
	 * readChar-读取一个字符，并返回
	 * @return 返回用户输入的第一个字符
	 */
	public static char readChar() {
		String str = readKeyBoard(1,false);
		return str.charAt(0);
	}
	/**
	 * readChar重载方法-如果用户不输入内容，直接回车将以defaultVal作为返回值
	 * @return 返回用户输入的第一个字符或默认值
	 */
	public static char readChar(char defaultVal) {
		String str = readKeyBoard(1,true);
		return (str.length()==0)?defaultVal:str.charAt(0);
	}
	/**
	 * readInt-读取两位数字
	 * @return 返回用户输入的数字
	 */
	public static int readInt() {
		
		int num;
		while(true) {
			String str = readKeyBoard(2,false);
			try {
				num = Integer.parseInt(str);
				break;
			} catch (NumberFormatException e) {
				// TODO: handle exception
				System.out.println("数字输入错误，请重新输入！");
			}
			
		}
		return num;
	}
	/**
	 * readInt-读取两位数字或返回默认值
	 * @return 返回用户输入的数字
	 */
	public static int readInt(int defaultNum) {
		int num;
		while(true) {
			String str = readKeyBoard(2,true);
			//返回默认值
			if(str.equals("")) return defaultNum;
			try {
				num = Integer.parseInt(str);
				break;
			} catch (NumberFormatException e) {
				// TODO: handle exception
				System.out.println("数字输入错误，请重新输入！");
			}
			
		}
		return num;
	}
	/**
	 * readString-读取指定长度的字符串并返回
	 * @return 返回用户输入的字符串
	 */
	public static String readString(int limit) {
		return readKeyBoard(limit,false);
	}
	/**
	 * readString-读取指定长度的字符串或默认值并返回
	 * @return 返回用户输入的字符串或默认值
	 */
	public static String readString(int limit,String defaultStr) {
		String str = readKeyBoard(limit,true);
		return (str.equals(""))?defaultStr:str;
	}
	/**
	 * readConfirmSelection
	 * 用户确认选择操作，从键盘读取'Y'或'N'返回
	 */
	public static char readConfirmSelection() {
		char c;
		String str;
		while(true) {
			str = readKeyBoard(1,false).toUpperCase();
			c = str.charAt(0);
			if(c == 'Y' || c == 'N') {
				break;
			}else {
				System.out.println("选择错误，请重新输入！");
			}
		}
		return c;
	}
	/**
	 * readKeyBoard-校验输入内容长度
	 * @param limit 内容长度限制
	 * @param blankReturn 内容是否可以为空
	 * @return 用户输入的字符内容
	 */
	private static String readKeyBoard(int limit,boolean blankReturn) {
		String line = "";
		int length;
		while(scan.hasNextLine()) {
			line = scan.nextLine();
			length = line.length();
			// 输入为空
			if(length==0) {				
				if(blankReturn) return line;
				else continue;
			}
			// 字符数量过长/过短
			if(length<1 || length>limit) {
				System.out.println("输入长度必须大于："+limit+"，请重新输入");
				continue;
			}
			break;
		}
		
		return line;
	}
}
