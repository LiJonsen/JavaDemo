/**
 * 打印100以内所有质数
 */
class PrimeNumber {
    public static void main(String[] args) {
        // 获取指定范围内的质数
        getPrimeNumber(100);
    }

    // 获取指定范围内的质数 - 方式一
    public static void getPrimeNumber(int acount) {
        boolean flag = true;
        // 获取当前时间至1970-01-01 00:00的时间戳（毫秒），返回long类型
        long start = System.currentTimeMillis();
        int amount = 0; // 记录外部循环次数
        int amount2 = 0; // 记录内部循环次数
        // 1. 遍历100以内数字
        for (int i = 2; i < acount; i++) {
            amount++;
            // 2.j不能被i整除的数叫做质数；否则称为合数

            // 使用根号计算10000-2之间的质数，花费：3867ms 循环次数：117501
            for (int j = 2; j < Math.sqrt(i); j++) {

                // 简单计算10000-2之间的质数，花费：10000ms 循环次数：5775222
                // for (int j = 2; j < i; j++) {
                amount2++;
                System.out.println("根号i=" + Math.sqrt(i) + "被取模数j=" + j);
                System.out.println("结果=" + (i % j));
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }
            // 3. 打印质数
            if (flag) {
                // System.out.println("质数：" + i);
            }
            flag = true;
        }
        System.out.println("外循环计算总数" + amount + "&&内循环计算总数" + amount2);

        long end = System.currentTimeMillis();
        // 打印计算所使用的时间
        System.out.println("计算时间：" + (end - start));
    }


}