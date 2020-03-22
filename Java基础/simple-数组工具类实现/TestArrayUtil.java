package com.atguigu.utils;

public class TestArrayUtil {
	public static void main(String[] args) {
		int[] test_arr = {2,12,52,50,30,9,5,23};
		
		ArrayUtil arrUtil = new ArrayUtil();
		
		int max = arrUtil.getMax(test_arr);
		int min = arrUtil.getMin(test_arr);
		int acount = arrUtil.getAcount(test_arr);
		int average = arrUtil.getAverage(test_arr);
		int index = arrUtil.find(test_arr,23);

		
		System.out.println("test_arr最大值："+max);
		System.out.println("test_arr最小值："+min);
		System.out.println("test_arr总和："+acount);
		System.out.println("test_arr平均值："+average);
		System.out.println("查找test_arr指定值下标："+index);
		// 打印数组所有元素
		arrUtil.printArray(test_arr);
		
		// 反转数组
		// arrUtil.reverse(test_arr);
		
		// 排序数组
		// arrUtil.sort(test_arr);
//		System.out.println("test_arr反转数组："+arrUtil.printArray(test_arr));
//		System.out.println("test_arr排序数组："+arrUtil.printArray(test_arr));
		

	}
	
}
