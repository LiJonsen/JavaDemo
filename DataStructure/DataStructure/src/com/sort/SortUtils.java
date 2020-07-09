package dataStructure.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

/**
 * @ClassName SortUtils
 * @Description 排序工具类
 * 实现：冒泡、选择排序，二分查找&递归二分查找
 * @Author Josen
 * @Date 2020/7/4 10:58
 */
public class SortUtils {
    public static void main(String[] args) {
//        int[] nums = new int[]{2,5,15,32,4,6};
//        SortUtils.bubbleSort(nums);
//        System.out.println(Arrays.toString(nums));
//        int[] nums2 = new int[]{2,5,15,32,4,6,11};
//        SortUtils.selectSort(nums2);
//        System.out.println(Arrays.toString(nums2));
//
////        int res = SortUtils.binarySearch(nums2, 11);
//        int res = SortUtils.recursionBinarySearch(nums2, 0,nums2.length-1,11);
//        System.out.println("二分查找结果："+res);


//        int[] arr = new int[]{-9,88,0,10,3,-255};
//        quickSort2(arr,0,arr.length-1);
//        System.out.println(Arrays.toString(arr));

        testSortTime(1000);
    }
    public static void testSortTime(int len){
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * len);
        }

        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("开始时间=" + date1Str);

//        quickSort(arr, 0, arr.length-1);

//        开始时间=2020-07-06 14:41:42
//        选择排序遍历次数：499500
//        结束时间=2020-07-06 14:41:42
//        selectSort(arr);

        bubbleSort(arr);

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("结束时间=" + date2Str);
    }

    /**
     * 快速排序
     * @param arr
     * @param left
     * @param right
     */
    public static void quickSort(int[] arr,int left,int right){
        // 左下标
        int l = left;
        // 右下标
        int r = right;
        // 中间轴
        int pivot = arr[(left+right)/2];
        int temp = 0;
        // 1. while里将大于中轴值的元素移到右边，小于中轴值的元素移到左边
        while (l<r){
            // 在pivot的左边一直找，找到大于等于pivot值
            while (arr[l]<pivot){
                l+=1;
            }
            // 在pivot的右边一直找，找到小于等于pivot值
            while (arr[r]>pivot){
                r-=1;
            }
            // 交换完成
            if(l>=r){
                break;
            }

            // 两值交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            // 如果交换完后，发现这个arr[l]==pivot，则r-- ，前移
            if(arr[l] == pivot){
                r-=1;
            }
            // 如果交换完后，发现这个arr[r]==pivot，则l++ ，后移
            if(arr[r] == pivot){
                l+=1;
            }
        }

        // 第一次交换pivot左右的顺序后，r和l=pivot的下标
//        System.out.println("r="+r);
//        System.out.println("l="+l);
        // 递归排序pivot左右的顺序
        if(l == r){
            // l+1作为pivot右边的排序开头
            l += 1;
            // r-1作为pivot左边的排序结尾
            r -= 1;
        }

        if(left < r){
            quickSort(arr,left,r);
        }
        if(right < l){
            quickSort(arr,l,right);
        }
    }

    /**
     * 插入排序
     * 特点：把n个特排序的元素看成为一个有序表和一个无序表
     *       开始时有序表中只包含一个元素，无序表中包含n-1个元素
     *       排序过程中每次从无序表中取出第一个元素，把它的排序码
     *       依次与有序表元素的排序码进行比较，将它插入到有序表中
     *       的适当位置，使之成为新的有序表
     * @param list
     */
    public static void insertSort(int[] list){

    }
    /**
     * 递归实现：二分查找
     * @param list
     * @param head
     * @param last
     * @param target
     */
    public static int recursionBinarySearch(int[] list,int head,int last,int target){
        // 查找失败
        if(head>last)
            return -1;
        // 获取中间的下标和元素
        int mid = (head+last)/2;
        int mid_val = list[mid];

        if(target > mid_val){
            // 当前值小于target，往后查找（mid+1）
            return recursionBinarySearch(list,mid+1,last,target);
        }else if(mid_val > target){
            // 当前值大于target，往前查找（mid-1）
            return recursionBinarySearch(list,head,mid-1,target);
        }else{
            // 找到对应值，返回下标
            return mid;
        }

    }

    /**
     * 循环实现：二分查找（查找list里等于target的下标）
     * 特点：使用二分查找的前提，list元素必须是已排序好的
     * @param list
     * @param target
     * @return
     */
    public static int binarySearch(int[] list,int target){
        // 记录list的开头和结尾
        int head = 0;
        int last = list.length-1;
        int cur_val;
        while (true){
            // 记录list中间的下标
            int cur_index = (head+last)/2;
            // 查找失败：中间标记 > last
            if(head>last){
                return -1;
            }
            cur_val = list[cur_index];

            if(cur_val > target){
                // 当前值大于target，往前查找
                last = cur_index-1;
            }else if(cur_val < target){
                // 当前值小于target，往后查找
                head = cur_index+1;
            }else if(target == cur_val){
                // 相等返回找到的下标
                return cur_index;
            }

        }

    }

    /**
     * 冒泡排序
     * 特点：
     * 依次找到最大值，通过两值交换放到数组后面
     * @param list
     */
    public static void bubbleSort(int[] list){
        int len = list.length;
        int temp;
        int count = 0;
        for(int i=0;i<len-1;i++){
            for(int j=0;j<len-1-i;j++){
                count++;
                if(list[j]>list[j+1]){
                    temp = list[j];
                    list[j] = list[j+1];
                    list[j+1] = temp;
                }
            }
        }
        System.out.println("冒泡排序遍历次数："+count);
    }
    /**
     * 选择排序
     * 特点：从数组下标0开始，依次比较后面的元素
     *       找到最小值并放到前面；
     * @param list
     */
    public static void selectSort(int[] list){
        int len = list.length;
        int temp;
        int count = 0;
        for(int i=0;i<len-1;i++){
            for(int j=i+1;j<len;j++){
                count++;
                if(list[i]>list[j]){
                    temp = list[i];
                    list[i] = list[j];
                    list[j] = temp;
                }
            }
        }
        System.out.println("选择排序遍历次数："+count);
    }
}
