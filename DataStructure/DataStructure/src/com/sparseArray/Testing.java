package com.sparseArray;

import org.junit.Test;

/**
 * @ClassName Testing
 * @Description TODO
 * @Author Josen
 * @Date 2020/6/30 20:40
 * @Version 1.0
 **/
public class Testing {
    @Test
    public void test(){
        SparseArrayPlus sparseArray = new SparseArrayPlus();

        sparseArray.addChess(SparseArrayPlus.BLACK_CHESS,3,3);
        sparseArray.addChess(SparseArrayPlus.WHITE_CHESS,4,5);
        int[][] sparse_arr = sparseArray.getSparse_arr();
        int[][] arr = sparseArray.getArr();
        sparseArray.printArray(arr);
        System.out.println("******************************");
        sparseArray.printArray(sparse_arr);

//        sparseArray.saveToDisk();
//        sparseArray.getArrayByDisk();
    }
}
