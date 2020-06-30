package com.sparseArray;
import java.io.*;

/**
 * @ClassName SparseArrayPlus
 * @Description 稀疏数组实现类（添加将数据持久化到磁盘功能）
 * 功能：
 * 1. 稀疏 -> 原始
 * 2. 原始 -> 稀疏
 * 3. 稀疏 -> 持久化（保存到磁盘）
 * 4. 持久化 -> 稀疏
 * @Author Josen
 * @Date 2020/6/30 17:36
 * @Version 1.0
 **/
public class SparseArrayPlus {
    // 默认8*8
    private int row = 8;
    private int col = 8;
    // 原始二维数组
    private int[][] arr;
    // 稀疏数组
    private int[][] sparse_arr;
    // 白棋
    public static final int WHITE_CHESS = 1;
    // 黑棋
    public static final int BLACK_CHESS = 2;

    public SparseArrayPlus() {
        initArray();
    }
    public SparseArrayPlus(int row, int col) {
        this.row = row;
        this.col = col;
        initArray();
    }

    /**
     * saveToDisk
     * 将稀疏数组序列化到磁盘文件chess.dat
     */
    public void saveToDisk() {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("chess.dat");
            oos = new ObjectOutputStream(fos);

            oos.writeObject(sparse_arr);

            System.out.println("保存成功...");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            closeOutputResource(fos);
            closeOutputResource(oos);
        }
    }



    /**
     * getArrayByDisk
     * 将磁盘文件chess.dat稀疏数组反序列化
     * @return
     */
    public int[][] getArrayByDisk(){
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("chess.dat");
            ois = new ObjectInputStream(fis);

            int[][] sparse = (int[][])ois.readObject();
            System.out.println("**************将磁盘文件chess.dat稀疏数组反序列化****************");
            printArray(sparse);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            closeInputResource(fis);
            closeInputResource(ois);
        }

        return null;
    }

    /**
     * initArray
     * 初始化二维数组 & 稀疏数组
     */
    private void initArray(){
        this.arr = new int[row][col];
        // 初始化稀疏数组结构
        transferArrayToSparse();
    }
    /**
     * addChess-添加棋子
     * @param val 棋子类型
     * @param x 对应row
     * @param y 对应col
     */
    public void addChess(int val,int x,int y){
        if(x>row || y>col){
            System.out.println("x/y值下标越界");
            return;
        }
        arr[x][y] = val;
        // 更新稀疏数组
        transferArrayToSparse();
    }
    /**
     * transferArrayToSparse
     * 初始化&更新sparse_arr
     * 将原始二维数组转成稀疏数组
     */
    public void transferArrayToSparse(){
        int sum = 0;
        // 获取有效值数量
        for(int[] rows : arr){
            for(int val : rows){
                if(val>0){
                    sum++;
                }
            }
        }
        sparse_arr = new int[sum+1][3];
        sparse_arr[0][0] = row;
        sparse_arr[0][1] = col;
        sparse_arr[0][2] = sum;

        // 没有有效值
        if(sum==0)
            return;

        // 记录稀疏数组下标
        int count = 0;

        // 将有效值填入稀疏数组
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(arr[i][j]>0){
                    count++;
                    // row
                    sparse_arr[count][0] = i;
                    // col
                    sparse_arr[count][1] = j;
                    // val
                    sparse_arr[count][2] = arr[i][j];
                }
            }
        }

    }
    /**
     * updateArray
     * 更新原始二维数组数据
     * 将稀疏数组转成原始二维数组
     */
    public void updateArray(){
        int sum = sparse_arr[0][2];// 获取有效数量
        for(int i=1;i<=sum;i++){
            int val = sparse_arr[i][2];
            int x = sparse_arr[i][0];
            int y = sparse_arr[i][1];
            // 修改指定下标的值
            arr[x][y] = val;
        }
    }

    /**
     * printArray
     * 遍历打印二维数组信息
     */
    public void printArray(int[][] arr){
        for(int[] rows : arr){
            for(int item : rows){
                System.out.printf("%d\t",item);
            }
            System.out.println();
        }
    }
    /**
     * 释放输出流资源
     * @param resource
     */
    private void closeOutputResource(OutputStream resource){
        if(resource!=null){
            try {
                resource.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 释放输入流资源
     * @param resource
     */
    private void closeInputResource(InputStream resource){
        if(resource!=null){
            try {
                resource.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }

    public int[][] getArr() {
        return arr;
    }
    public int[][] getSparse_arr() {
        return sparse_arr;
    }

}
