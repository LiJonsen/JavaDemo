package com.leetcode;

import java.util.Arrays;

/**
 * @ClassName TwoNumsTotal
 * @Description
 * 两数之和
 * 给定一个整数数组nums和一个目标值target，在nums数组中找出
 * (和为目标值的那两个整数)，并返回它们的下标值
 * nums = [2,7,11,15]   target=9
 * num[0]+num[1]=target
 * 返回：[0,1]
 * @Author Josen
 * @Date 2020/6/26 16:14
 * @Version 1.0
 **/
public class TwoNumsTotal {
    public static void main(String[] args) {
        int[] nums = new int[]{2,7,11,11};
        int target = 18;
        int[] result = TwoNumsTotal.getResult(nums, target);
        System.out.println(Arrays.toString(result));
    }
    public static int[] getResult(int[] nums,int target){
        for(int i=1;i<nums.length;i++){
            for(int j=0;j<i;j++){
                System.out.println("i="+i+"********j="+j);
                if((target-nums[i]) == nums[j]){
                    return new int[]{j,i};
                }
            }
        }
        return null;
    }
}
