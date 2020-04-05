package com.atguigu.team.util;
import java.util.Scanner;

// 团队调度系统-校验用户输入
public class TSUtility {
	private static Scanner scan = new Scanner(System.in);
	/**
	 * readReturn-该方法提示并等待，用户按回车键后返回
	 */
	public static void readReturn() {
		System.out.println("按回车键继续...");
		readKeyBoard(100, true);
	}
	/**
	 * readMenuSelection-读取菜单选项
	 * @return 用户输入的选项 （1-4）
	 */
	public static char readMenuSelection() {
		char c;
		String str;
		while(true) {
			str = readKeyBoard(1,false);
			// charAt - 截取指定下标的字符并返回
			c = str.charAt(0);
			if(c != '1' && c!='2'&&c!='3'&&c!='4') {
				System.out.println("选择错误，请重新输入！");
			}else break;
			
		}
		return c;
	}
	
	/**
	 * readInt-读取长度不超过2的整数
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
				System.out.println("数字输入错误，请重新输入！");
			}
			
		}
		return num;
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
