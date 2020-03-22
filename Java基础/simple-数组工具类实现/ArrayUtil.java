package com.atguigu.utils;
// 自定义数组工具类
public class ArrayUtil {

	// 1.获取数组最大值
	public int getMax(int[] arr) {
		int max = 0;
		
		if(arr.length>1) {
			for(int i=0;i<arr.length;i++) {
				if(arr[i] > max) {
					max = arr[i];
				}
			}
		}else {
			max = arr[0];
		}
		return max;
	}
	
	// 2.获取数组最小值
	public int getMin(int[] arr) {
		int min = arr.length>0?arr[0]:0;
		if(arr.length>1) {
			for(int i=0;i<arr.length;i++) {
				if(arr[i] < min) {
					min = arr[i];
				}
			}
		}
		return min;
	}
	
	// 3.获取数组总和
	public int getAcount(int[] arr) {
		int acount=0;
		if(arr.length>0) {
			for(int i=0;i<arr.length;i++) {
				acount+=arr[i];
			}
		}
		return acount;
	}
	
	// 4.获取数组平均值
	public int getAverage(int[] arr) {
		return getAcount(arr) / arr.length;
	}
	// 5.反转数组
	public void reverse(int[] arr) {
		int length = arr.length;
		int temp;
		for(int i=0;i<length/2;i++) {
			temp = arr[length-i-1]; // 从后往前
			// 最后一个元素与第一个元素开始，依次互换
			arr[length-i-1] = arr[i];  
			arr[i] = temp; 
		}
	}
	// 6.复制数组
	public int[] copy(int[] arr) {
		int[] new_arr = new int[arr.length];
		
		for(int i=0;i<arr.length;i++) {
			new_arr[i] = arr[i];
		}
		
		return new_arr;
	}
	
	// 7.排序数组
	public void sort(int[] arr) {
		int temp;
		for(int i=0;i<arr.length-1;i++) {
			for(int j=0;j<arr.length-1;j++) {
				if(arr[j]>arr[j+1]) {
					temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
	}
	// 8.遍历数组，打印数组所有元素
	public void printArray(int[] arr) {
		String str = "";
		for(int i=0;i<arr.length;i++) {
			if(i!= arr.length-1) {				
				str += arr[i]+",";
			}
		}
		
		str += arr[arr.length-1];
		
		System.out.println("打印所有数组元素："+str);
	}
	/**
	 * 9.查找数组指定元素下标
	 * arr: 要查询的数组
	 * dest: 查询条件
	 */ 
	public int find(int[] arr,int dest) {
		for(int i=0;i<arr.length;i++) {
			if(arr[i] == dest) {
				return i;
			}
		}
		return -1;
	}

}
