package dataStructure.recursion;

/**
 * @ClassName MiGong
 * @Description 迷宫（回溯）
 * map[x][x] - 存储数字
 * 0：未走过   1：墙   2：已走过   3：标识此路不通
 * @Author Josen
 * @Create 14:27 14:27
 */
public class MiGong {
    public static void main(String[] args) {
        // 创建8*7 map
        int[][] map = new int[8][7];

        int row = map.length;
        int col = map[0].length;
        // map第一行 & 最后一行设为墙
        for(int i=0;i<col;i++){
            map[0][i] = 1;
            map[row-1][i] = 1;
        }
        // map第一列 & 最后一列设为墙
        for(int j=0;j<row;j++){
            map[j][0] = 1;
            map[j][col-1] = 1;
        }

        // map第4行 1、2设为墙
        map[3][1] = 1;
        map[3][2] = 1;

        findEnd(map,1,1);
        // 打印map
        printMap(map);
    }

    /**
     * 开始寻找终点，当map[i][j]==end时则表示找到终点
     * @param map 迷宫地图
     * @param i 所在行位置
     * @param j 所在列位置
     * @return
     */
    public static boolean findEnd(int[][] map,int i,int j){
        // 出口定义在map[6][5]
        if(map[6][5] == 2){
            return true;
        }else{
            if(map[i][j] == 0){
                map[i][j] = 2;// 标记已走过
                // 移动先后顺序：下-》右-》上-》左

                if(findEnd(map,i+1,j)){// 向下
                    return true;
                }else if(findEnd(map,i,j+1)){// 向右
                    return true;
                }else if(findEnd(map,i-1,j)){// 向上
                    return true;
                }else if(findEnd(map,i,j-1)){// 向左
                    return true;
                }else{
                    // 没找到，标记为3
                    map[i][j] = 3;
                    return false;
                }
            }else{
                // map[i][j] = 1 | 2 | 3
                return false;
            }
        }
    }

    /**
     * 打印map
     * @param map
     */
    public static void printMap(int[][] map){
        int row = map.length;
        int col = map[0].length;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                System.out.printf("%d\t",map[i][j]);
            }
            System.out.println();
        }
    }
}
