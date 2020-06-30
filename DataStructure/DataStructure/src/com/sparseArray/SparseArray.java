package com.sparseArray;

/**
 * @ClassName SparseArray
 * @Description 稀疏数组
 * 案例：模拟11*11的五指棋盘
 * @Author Josen
 * @Date 2020/6/28 10:38
 * @Version 1.0
 **/
public class SparseArray {
    public static void main(String[] args) {
        SparseArray sparseArray = new SparseArray();

        int[][] chess_arr = new int[11][11];
        // 0=默认 1=黑棋 2=白棋
        // 表示在第1行第2列添加一颗黑棋
        chess_arr[1][2] = 1;
        chess_arr[3][5] = 2;
        chess_arr[5][4] = 2;

        // 打印原始二维数组
        System.out.println("*************打印原始二维数组*************");
        for(int[] rows : chess_arr){
            for(int item : rows){
                System.out.printf("%d\t",item);
            }
            System.out.println();
        }

        int[][] sparse_arr = sparseArray.getSparseArray(chess_arr);
        System.out.println("*************原始二维数组 ---》 稀疏数组*************");
        for(int i=0;i<sparse_arr.length;i++){
            System.out.printf("%d\t%d\t%d\t\n", sparse_arr[i][0], sparse_arr[i][1], sparse_arr[i][2]);
        }


        int[][] chessArray2 = sparseArray.getChessArray(sparse_arr);
        // 打印稀疏数组 ---》 原始二维数组
        System.out.println("*************稀疏数组 ---》 原始二维数组*************");
        for(int[] rows : chessArray2){
            for(int item : rows){
                System.out.printf("%d\t",item);
            }
            System.out.println();
        }
    }
    /**
     * 将原始二维数组转换成稀疏数组
     * @param chess_arr 原始二维数组
     * @return
     */
    public int[][] getSparseArray(int[][] chess_arr){

        // sum=当前二维数组有效数值数量
        int sum = 0;
        for(int[] c_row : chess_arr){
            for(int data : c_row){
                if(data>0){
                    sum++;
                }
            }
        }
        /**
         * 稀疏数组结构(第一行val为有效值数量)：
         *      row  col  val
         *   0  11   11   3
         *   1  1    2    1
         *   2  3    5    2
         *   3  5    4    2
         */
        // 创建稀疏数组
        int[][] sparse_arr = new int[sum+1][3];
        sparse_arr[0][0] = chess_arr.length;
        sparse_arr[0][1] = chess_arr[0].length;
        sparse_arr[0][2] = sum; //有效值数量

        int count = 0;// 记录当前稀疏数组下标

        // 原始二维数组 ---》 稀疏数组
        for(int i=0;i<chess_arr.length;i++){
            for(int j=0;j<chess_arr.length;j++){
                if(chess_arr[i][j]>0){
                    count++;
                    sparse_arr[count][0] = i;
                    sparse_arr[count][1] = j;
                    sparse_arr[count][2] = chess_arr[i][j];
                }
            }
        }
        return sparse_arr;
    }

    /**
     * 稀疏数组转换成原始二维数组
     * @param sparse_arr
     * @return
     */
    public int[][] getChessArray(int[][] sparse_arr){
        int row = sparse_arr[0][0];
        int col = sparse_arr[0][1];
        int[][] chess_arr = new int[row][col];

        for(int i=1;i<sparse_arr.length;i++){
            /**
             * sparse_arr[i][0] = row
             * sparse_arr[i][1] = col
             * sparse_arr[i][2] = val
             */
            chess_arr[sparse_arr[i][0]][sparse_arr[i][1]] = sparse_arr[i][2];
        }
        return chess_arr;
    }
}
